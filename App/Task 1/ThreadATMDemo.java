//Practical assignment 3
//Student Number: Kieran Woodrow
//Student Name: u19304308

import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.io.FileInputStream;
import java.util.logging.Level;



class ThreadATMDemo {
	private static final Logger LOGGER = Logger.getLogger("global");
	
	public static void main (String args[]) {

		// set up logger properties
		configureLogger();
		// set the types of log messages to be displayed
		// options (for this practical): OFF, INFO, FINE, FINER, FINEST, ALL
		LOGGER.setLevel(Level.FINEST);

		// examples of logging with different log levels
		// 	  (make sure to remove these lines before submitting your assignment)
		//LOGGER.fine("This is a fine message");
		//LOGGER.finer(String.format("(sample id:%d, sample name: %s)", 3, "SomeThread"));

		// set up shared Account
		Lock lock = new FilterLock(3);
		float startingBalance = 2260;
		ATM acc = new ATM(startingBalance, 4);
		Line l = new Line(3, acc);

		// specify your own option values for thread runs here
		TransactionOptions.cardUsers = 4;
		TransactionOptions.withdrawalSleepTime = 10;
		TransactionOptions.depositSleepTime = 10;
		TransactionOptions.lines = 3;

		CardUser t1 = new CardUser(acc);
		t1.start();
		
		CardUser t2 = new CardUser(acc);
		t2.start();

		CardUser t3 = new CardUser(acc);
		t3.start();

	}

	private static void configureLogger() {
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}