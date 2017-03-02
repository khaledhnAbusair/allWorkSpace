package suncertify.main;

/**
 * The main application launcher starts server or client application according
 * to arguments passed to its main method.
 * <p>
 * 
 * The {@link #main(java.lang.String[])} method launches client or server
 * application according to mode flag passed through index <code>0</code>
 * parameter, the expected string (case insensitive) values are:
 * <ul>
 * <li>"alone": running client application in alone mode which allows the user
 * to select a database file resides locally in its machine, it is the value of
 * constant {@link #ALONE_MODE}</li>
 * <li>"server": running server application with allows the user (mostly an
 * administrator) to run a network service for a database file, it is the value
 * of constant {@link #SERVER_MODE}.</li>
 * <li>"", constant {@link #CLIENT_MODE}, or passed arguments length is
 * <code>0</code>: running client application in client mode which allows the
 * user to connect to a database network service to access its data.</li>
 * </ul>
 * .
 * 
 * According to mode flags passed to this class main method, the launch process
 * is passed to classes {@link ClientMain} or {@link ServerMain}.
 * 
 * 
 * @author Mohammad S. Abdellatif
 * @see ClientMain
 * @see ServerMain
 */
public class Main {

	/**
	 * Holds the value for "alone" mode flag to run client application in alone
	 * mode.
	 */
	public static final String ALONE_MODE = "alone";
	/**
	 * Holds the value for "server" mode flag to run server application.
	 */
	public static final String SERVER_MODE = "server";
	/**
	 * Holds the value for "" mode flag to run client application in network
	 * client mode.
	 */
	public static final String CLIENT_MODE = "";

	/**
	 * Main method for running client or server application according to mode
	 * flag passed in <code>args</code>.
	 * <p>
	 * 
	 * Mode flag is read from <code>args</code> index <code>0</code> element. If
	 * no parameters is passed, it will be considered as a network client mode.
	 * 
	 * It navigates launching for client and server applications to classes
	 * {@link ClientMain} and {@link ServerMain}.
	 * 
	 * An {@link IllegalArgumentException} will be thrown in case passed mode
	 * flag does not match expected mode flags.
	 * 
	 * @param args
	 *            holds arguments passed to this method.
	 * @throws IllegalArgumentException
	 *             thrown in case passed mode flag does not match expected mode
	 *             flags.
	 */
	public static void main(String[] args) {
		String mode =
				(args.length == 0 ? CLIENT_MODE : args[0]).trim().toLowerCase();
		if (ALONE_MODE.equals(mode) || CLIENT_MODE.equals(mode)) {
			ClientMain.main(args);
		} else if (SERVER_MODE.equals(mode)) {
			ServerMain.main(args);
		} else {
			throw new IllegalArgumentException("unknown mode '" + mode
					+ "', expected modes are ('" + ALONE_MODE + "','"
					+ SERVER_MODE + "','" + CLIENT_MODE + "')");
		}

	}
}
