package suncertify.db.server;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the base implementation for database network services.
 * <p>
 * 
 * This abstract class provides base implementation for registering listeners
 * and firing events for starting and shutting down the network service.
 * Implementation providers only have to implement SPI (Service Provider
 * Interface) abstract protected methods to benefit from this base
 * implementation.
 * 
 * @author Mohammad S. Abdellatif
 * @see NetworkService
 */
public abstract class AbstractNetworkService implements NetworkService {

	protected List<NetworkServiceEventListener> serviceEventListeners =
			new ArrayList<NetworkServiceEventListener>();
	protected boolean running = false;

	/**
	 * Starts a network service by calling SPI method <code>startSpi</code> then
	 * fire a start event for registered listeners.
	 * 
	 * @throws NetworkServiceException
	 *             if <code>startSpi</code> throw it.
	 */
	@Override
	public void start() throws NetworkServiceException {
		startSpi();
		running = true;
		fireNetworkServiceStarted();
	}

	/**
	 * Stops a network service by calling SPI method <code>stopSpi</code> then
	 * fire a stop event for registered listeners.
	 * 
	 * @throws NetworkServiceException
	 *             if <code>stopSpi</code> throw it.
	 */
	@Override
	public void stop() throws NetworkServiceException {
		stopSpi();
		running = false;
		fireNetworkServiceStopped();
	}

	/*
     * 
     */
	@Override
	public boolean isRunning() {
		return running;
	}

	/**
	 * Registers a listener for network service events.
	 * 
	 * @param listener
	 *            network service events listeners.
	 */
	@Override
	public void addNetworkServiceEventListener(
			NetworkServiceEventListener listener) {
		serviceEventListeners.add(listener);
	}

	/**
	 * Removes <code>listener</code> from listeners queue so it wont receive
	 * events notification generated by this instance.
	 * 
	 * @param listener
	 *            listener to remove.
	 */
	@Override
	public void removeNetworkServiceEventListener(
			NetworkServiceEventListener listener) {
		serviceEventListeners.remove(listener);
	}

	/**
	 * Fire network service event is started by calling each listener
	 * {@link NetworkServiceEventListener#serviceStarted(suncertify.db.server.NetworkServiceEvent)}
	 * ;
	 */
	protected void fireNetworkServiceStarted() {
		NetworkServiceEvent event =
				new NetworkServiceEvent(this,
						NetworkServiceEvent.EventType.START);
		for (NetworkServiceEventListener listener : serviceEventListeners) {
			listener.serviceStarted(event);
		}
	}

	/**
	 * Fire network service event is stopped by calling each listener
	 * {@link NetworkServiceEventListener#serviceStopped(suncertify.db.server.NetworkServiceEvent)}
	 * ;
	 */
	protected void fireNetworkServiceStopped() {
		NetworkServiceEvent event =
				new NetworkServiceEvent(this,
						NetworkServiceEvent.EventType.STOP);
		for (NetworkServiceEventListener listener : serviceEventListeners) {
			listener.serviceStopped(event);
		}
	}

	/**
	 * Service provider interface method to be implemented by subclasses to
	 * perform network service start operation.
	 * 
	 * @throws NetworkServiceException
	 *             if starting for network service is failed.
	 */
	protected abstract void startSpi() throws NetworkServiceException;

	/**
	 * Service provider interface method to be implemented by subclasses to
	 * perform network service stop operation.
	 * 
	 * @throws NetworkServiceException
	 *             if stopping for network service is failed.
	 */
	protected abstract void stopSpi() throws NetworkServiceException;
}
