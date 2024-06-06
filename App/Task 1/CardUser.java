//Practical assignment 3
//Student Number: Kieran Woodrow
//Student Name: u19304308

import java.lang.Math; 

public class CardUser extends Thread {

    ATM atm;
    int numLines = TransactionOptions.lines;
    int cardUsers = TransactionOptions.cardUsers;
    int withdrawlSleepT = TransactionOptions.withdrawalSleepTime;
    int depositSleepT = TransactionOptions.depositSleepTime;

    public CardUser() //EMPTY CONSTRUCTOR
    {

    }

    public CardUser(ATM a){//CONSTRUCTOR
        atm = a;
    }

    public void setATM(ATM a) {//SETTER
        atm = a;
    }

    public ATM getATM() {//GETTER
        return atm;
    }
    
    public void run() //MAIN RUN FUNCTION
    {
        int sleepmax = 100; 
        int sleepmin = 0; 
        int rangeSleep = sleepmax - sleepmin + 1; 
        int randsleep = (int)(Math.random() * rangeSleep) + sleepmin; 

            try 
            {
                sleep(randsleep);
            } 

            catch (InterruptedException e)
            {
                e.printStackTrace();
            } //HAVE TO HAV

        for ( int i = 0; i < cardUsers ; i++ ) 
		{
        
            int max = 1; 
            int min = 0; 
            int range = max - min + 1; 
            int rand = (int)(Math.random() * range) + min; 

      //random function to get random numer from 0 t 1. 1 = dep. o = draw
            
            if(rand == 0)
            {
                float maxdraw = 1000; 
                float mindraw = 200; 
                float rangedraw = maxdraw - mindraw + 1; 
                float randdraw = (float)(Math.random() * rangedraw) + mindraw; 

                atm.withdraw(randdraw, ThreadID.get() );

                try 
                {
                    sleep(withdrawlSleepT); //SLEEP FOR A WHILE BEFORE YOU TRY NEXT WITHDRAWL
                } 

                catch (InterruptedException e)
                {
                    e.printStackTrace();
                } //HAVE TO HAVE TRY AND CATCH FOR SLEEP FUNCTION
   
            }

            if(rand == 1)
            {
                float maxdep = 500; 
                float mindep = 50; 
                float rangedep = maxdep - mindep + 1; 
                float randdep = (float)(Math.random() * rangedep) + mindep; 

                atm.deposit(randdep, ThreadID.get() );

                try 
                {
                    sleep(depositSleepT); //SLEEP FOR A WHILE BEFORE YOU TRY NEXT WITHDRAWL
                } 

                catch (InterruptedException e)
                {
                    e.printStackTrace();
                } //HAVE TO HAVE TRY AND CATCH FOR SLEEP FUNCTION


            }
			
		}

		
	}

    
}
