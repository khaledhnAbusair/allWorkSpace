package suncertify.db;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A loader for {@link EngineService} implementation.
 * <p>
 * 
 * Implementation for {@link EngineService} is loaded using
 * <code>java.util.ServiceLoader</code>. First implementation instance returned
 * by service loader is the only one used by this loader.
 * 
 * @author Mohammad S. Abdellatif
 * @see EngineService
 */
public class EngineServiceLoader {

	private static EngineService engineService;
	private static Lock instantLock = new ReentrantLock();

	/**
	 * Private constructor so now one instant it.
	 */
	private EngineServiceLoader() {
	}

	/**
	 * Load {@link EngineService} implementation from class path using
	 * <code>java.util.ServiceLoader</code>.
	 * <p>
	 * 
	 * If {@link EngineService} instance is not created yet, load it using
	 * <code>java.util.ServiceLoader</code>, cache it, then return it to caller.
	 * Any future call to this method will return the same cached instance.
	 * 
	 * @return single engine service instance.
	 * @throws EngineServiceException
	 *             if locating or loading for service instance is failed.
	 */
	public static EngineService locateEngineService()
			throws EngineServiceException {
		if (engineService == null) {
			instantLock.lock();
			try {
				if (engineService == null) {
					ServiceLoader<EngineService> services =
							ServiceLoader.load(EngineService.class);
					Iterator<EngineService> iterator = services.iterator();

					if (iterator.hasNext()) {
						engineService = iterator.next();
					} else {
						throw new EngineServiceException(
								"no implementations for EngineService is found");
					}
				}
			} finally {
				instantLock.unlock();
			}
		}
		return engineService;
	}
}
