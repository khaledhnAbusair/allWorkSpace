package suncertify.db.server.rmi;

import java.io.File;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import suncertify.db.DBMain;
import suncertify.db.Engine;
import suncertify.db.EngineService;
import suncertify.db.EngineServiceException;
import suncertify.db.EngineServiceLoader;
import suncertify.db.Session;
import suncertify.db.server.AbstractNetworkService;
import suncertify.db.server.NetworkServiceException;

/**
 * A database network service which uses RMI registry to export engine as a
 * network service.
 * <p>
 * Database engine interface {@link Engine} and related interfaces are mirrored
 * as remote interfaces to be exposed as RMI skeletons which delegates remote
 * requests to wrapped engine, following are each remote interface with related
 * local interface:
 * <ul>
 * <li>{@link RemoteEngine} will stands off interface {@link Engine}</li>
 * <li>{@link RemoteSession} will stands off interface {@link Session}</li>
 * <li>{@link RemoteDBMain} will stands off interface {@link DBMain}</li>
 * </ul>
 * all calls for exported skeletons will be navigated to related local
 * instances. <br>
 * Once this network service is started, a {@link RemoteEngine} skeleton will be
 * exported to RMI registry. {@link RemoteSession} and {@link RemoteDBMain}
 * instances are exported automatically by RMI registry once they are returned
 * through methods calls.<br>
 * 
 * When this network service is stopped, all exported skeletons will be
 * unexported from RMI registry.<br>
 * 
 * The following sample code shows how to access a remote database engine
 * exported to host "host" on port "6666" with name "service1", create a session
 * , then read a data from database:<br>
 * 
 * <pre>
 * <code>
 * // get remote engine stub
 * RemoteEngine engine = (RemoteEngine) Naming.lookup("//host:6666/service1");
 * // create new session
 * RemoteSession session = engine.newSession();
 * // get data access then read record '0'
 * RemoteDBMain dbMain = session.getDataAccess();
 * dbMain.read(0);
 * </code>
 * </pre>
 * 
 * This network service uses {@link EngineServiceLoader} to locate an
 * implementation for {@link EngineService} to create an {@link Engine} from it
 * to be wrapped by exported skeletons.
 * 
 * @author Mohammad S. Abdellatif
 * @see EngineService
 * @see EngineService#getEngine(java.io.File)
 */
public class RMINetworkService extends AbstractNetworkService {
	
	private File databaseFile;
	private RemoteEngineSkeleton engineSkelton;
	private String serviceName;
	private int port;
	private EngineService engineService;
	private Engine engine;
	private static Map<Integer, Registry> registries =
			new HashMap<Integer, Registry>();

	/**
	 * Construct a new RMI network service for database file
	 * <code>databaseFile</code>.
	 * <p>
	 * Network service will be exported to RMI registry on port
	 * <code>port</code> with name <code>serviceName</code>.
	 * 
	 * An {@link EngineService} instance will be located by
	 * {@link EngineServiceLoader} to be used as the factory for {@link Engine}
	 * instance which will manage database file <code>databaseFile</code>,
	 * created <code>Engine</code> instance will be wrapped by remote skeletons.
	 * 
	 * @param databaseFile
	 *            database file to export a network service for.
	 * @param serviceName
	 *            the name to export service with on RMI registry.
	 * @param port
	 *            the port for RMI registry.
	 */
	public RMINetworkService(File databaseFile, String serviceName, int port)
			throws EngineServiceException {
		this(EngineServiceLoader.locateEngineService(), databaseFile,
				serviceName, port);
	}

	/**
	 * Construct a new RMI network service for database file
	 * <code>databaseFile</code>.
	 * <p>
	 * Network service will be exported to RMI registry on port
	 * <code>port</code> with name <code>serviceName</code> using engine service
	 * <code>engineService</code> to create {@link Engine} instance for passed
	 * database file to be wrapped by exported remote skeletons.
	 * 
	 * @param engineService
	 *            factory for {@link Engine} instance
	 * @param databaseFile
	 *            database file to expose a network service for.
	 * @param serviceName
	 *            the name to export service with on RMI registry.
	 * @param port
	 *            the port for RMI registry.
	 */
	public RMINetworkService(EngineService engineService, File databaseFile,
			String serviceName, int port) {
		this.databaseFile = databaseFile;
		this.serviceName = serviceName;
		this.port = port;
		this.engineService = engineService;
	}

	/**
	 * Start network service by locating RMI registry and binding engine to it.
	 * <p>
	 * Once an {@link Engine} instance is created for database file, a skeleton
	 * of type {@link RemoteEngine} will be exported to RMI registry to accept
	 * calls navigated to original local engine instance.
	 * 
	 * @throws NetworkServiceException
	 *             if exporting to RMI engine skeleton is failed.
	 */
	@Override
	public void startSpi() throws NetworkServiceException {
		boolean started = false;

		Logger.getLogger(this.getClass().getName()).log(Level.INFO,
				"start server with name{0} on port: {1}",
				new Object[] { serviceName, port });
		try {
			engine = engineService.getEngine(databaseFile);
		} catch (EngineServiceException ex) {
			throw new NetworkServiceException(ex.getMessage(), ex);
		}

		try {
			engineSkelton = new RemoteEngineSkeleton(engine);
			engineSkelton.export(port);
			getRegistry().bind(serviceName, engineSkelton);
			started = true;
		} catch (AlreadyBoundException ex) {
			throw new NetworkServiceException(ex.getMessage(), ex);
		} catch (RemoteException ex) {
			throw new NetworkServiceException(ex.getMessage(), ex);
		} finally {
			if (!started) {
				// if starting for service is failed, shutdown the engine
				engine.shutdown();
			}
		}
	}

	/**
	 * Stop network service by unbinding exported skeletons from RMI registry.
	 * 
	 * @throws NetworkServiceException
	 *             if stopping is failed.
	 */
	@Override
	public void stopSpi() throws NetworkServiceException {
		try {
			getRegistry().unbind(serviceName);
			engineSkelton.unexport();
		} catch (RemoteException ex) {
			throw new NetworkServiceException(ex.getMessage(), ex);
		} catch (NotBoundException ex) {
			throw new NetworkServiceException(ex.getMessage(), ex);
		} finally {
			// always shutdown the engine
			engine.shutdown();
			engine = null;
		}
	}

	/*
	 * 
	 */
	@Override
	public Engine getEngine() {
		return engine;
	}

	/**
	 * Creates RMI registry.
	 * 
	 * @return RMI registry.
	 * @throws RemoteException
	 *             if creation is failed.
	 */
	private Registry getRegistry() throws RemoteException {
		Registry registry = registries.get(port);
		if (registry == null) {
			registry = LocateRegistry.createRegistry(port);
			registries.put(port, registry);
		}
		return registry;
	}
}
