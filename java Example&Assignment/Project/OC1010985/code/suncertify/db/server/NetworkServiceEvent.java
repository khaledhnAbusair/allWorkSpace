package suncertify.db.server;

import java.util.EventObject;

/**
 * Encapsulate an event generated by an instance of {@link NetworkService}.
 * <p>
 * 
 * Enum {@link EventType} defines the types of events which are:
 * <ul>
 * <li>{@link EventType#START}: a network service event is started</li>
 * <li>{@link EventType#STOP}: a network service event is stopped</li>
 * </ul>
 * 
 * The source for this event will be the {@link NetworkService} instance
 * generated the event which can be retrieved by calling super method
 * <code>getSource()</code>.
 * 
 * @author Mohammad S. Abdellatif
 * @see NetworkServiceEventListener
 * @see NetworkService
 */
public class NetworkServiceEvent extends EventObject {

	private final EventType type;

	/**
	 * Defines the event types for a database network service.
	 */
	public static enum EventType {

		/**
		 * Defines the event for starting a database network service.
		 */
		START,
		/**
		 * Defines the event for stopping a database network service.
		 */
		STOP;
	}

	/**
	 * Construct a database network service event generated by service
	 * <code>networkService</code> of type <code>type</code>.
	 * 
	 * @param networkService
	 *            network service which generated the event.
	 * @param type
	 *            type of event.
	 */
	public NetworkServiceEvent(NetworkService networkService, EventType type) {
		super(networkService);
		this.type = type;
	}

	/**
	 * Returns the type of this event.
	 * 
	 * @return type of this event.
	 */
	public EventType getType() {
		return type;
	}
}