import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;

class FilterLock implements Lock {

    private static final Logger LOGGER = Logger.getLogger("global");

    volatile int [] level;
    volatile int [] victim;
    volatile String [] tracker;
    int l;

    public FilterLock(int levels) //FILTER LOCK CONSTRUCTOR
    { 
        l = levels;
        level = new int[levels];
        victim = new int[levels];
        tracker = new String[levels];

        for(int k = 0; k < levels; k++)
        {
            level[k] = 0;
            victim[k] = 0;
            tracker[k] = "[x]";
        }
        LOGGER.fine("Lines that used the ATM:");
        for(int y = 0; y < levels; y++)
        {
            LOGGER.fine(tracker[y]);
        }


    }

    public void lock()//LOCK FUNCTION
    { 
        int i = ThreadID.get();
  
        LOGGER.fine("Thread-"+ThreadID.get()+" waiting to perform transaction");

        for (int k = 1; k < l; k++) 
        {
            level[i] = k;
            victim[k] = i;
            
            for (int t = 0; t < l; t++) 
            {
               
                while ((t != i) && ( level[t] >= k && victim[k] == i)) 
                {
                    //spin wait
                }
                
            }

            
        }
             
        LOGGER.fine("Thread-"+ThreadID.get()+" performing transaction on the ATM(entering CS)");

        for(int u = 0; u < level.length; u++)
        {
            if(victim[u] != ThreadID.get() )
            {
                LOGGER.fine("Thread-"+ThreadID.get()+" is a victim");
            }

        }

        //if()
        
    }

    public void unlock()//UNLOCK FUNCTION  
    {
       
        int i = ThreadID.get();
        
        tracker[i] = "[o]";
        LOGGER.fine("Thread-"+ThreadID.get()+" exiting");

        int tally = 0;

        LOGGER.fine("Lines that used the ATM:");
        for(int m = 0; m < tracker.length; m++)
        {
            LOGGER.fine(tracker[m]);
        }

        for(int r = 0; r < l; r++)
        {
            if( tracker[r] == "[x]" );
            tally= tally +1;
        }

        if( tally == l )
        {
            LOGGER.fine("All lines used the ATM, resetting: ");
            for(int e = 0; e < l; e++)
            {
                tracker[e] = "[x]";
                LOGGER.fine(tracker[e]);

            }
        }


        level[i] = 0;

    }


    @Override
    public Condition newCondition() {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        throw new java.lang.UnsupportedOperationException();
    }


    @Override
    public boolean tryLock() {
        throw new java.lang.UnsupportedOperationException();
    }
}