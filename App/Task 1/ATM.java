//Practical assignment 3
//Student Number: u19304308
//Student Name: Kieran Woodrow

import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;

class ATM {
	private static final Logger LOGGER = Logger.getLogger("global");

	float balance;
	Lock lock;
	int numThreads;
	

	public ATM(float amount, int numThreads)
	{
		balance = amount;
		this.numThreads = numThreads;
		lock = new FilterLock(numThreads);
	}

	public boolean withdraw(float amount, int threadID) {
	
		boolean isSuccess = false; // INDICATE IF WITHDRAWL FUNCTION WAS SUCCESSFULL

		lock.lock();

			try{ //THIS IS THE CRITICAL SECTION BECAUSE IT IS USING THE SHARED VARIABLE BALANCE

				if( balance > amount && balance != 0)
				{
					balance = balance - amount;
					isSuccess = true;
					LOGGER.info("Thread-"+threadID+" withdrawing R"+String.format("%.02f", amount)+" from ATM, R"+String.format("%.02f", balance)+" remaining");
				}
				
				else
				{ 
					 LOGGER.info("Thread-"+threadID+" withdrawl of R"+String.format("%.02f", amount)+" not possible, insufficient funds. R"+String.format("%.02f", balance)+" remaining.");
				}
			}
			
			finally{

				lock.unlock();
			}
			
		return isSuccess;
	}

	public void deposit(float amount, int threadID) {

		lock.lock();

		try{ //THIS IS THE CRITICAL SECTION BECAUSE IT IS USING THE SHARED VARIABLE BALANCE

			balance = balance + amount;
			LOGGER.info("Thread-"+threadID+" depositing R"+String.format("%.02f", amount)+" to ATM, R"+String.format("%.02f", balance)+" remaining");
		}
		finally{

			lock.unlock();
		}
	}

	public float getATMBalance()
	{
		return balance;
	}

}
