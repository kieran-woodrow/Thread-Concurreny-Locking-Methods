import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;

class FilterLock implements Lock {

    private static final Logger LOGGER = Logger.getLogger("global");

    volatile int [] level;
    volatile int [] victim;
    int l;

    public FilterLock(int levels) //FILTER LOCK CONSTRUCTOR
    { 
        l = levels;
        level = new int[levels];
        victim = new int[levels];

        for(int k = 0; k < levels; k++)
        {
            level[k] = 0;
            victim[k] = 0;
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
             
        LOGGER.fine("Thread-"+ThreadID.get()+" performing transaction on the ATM(entering CS");

        for(int u = 0; u < level.length; u++)
        {

            if(victim[u] != ThreadID.get() )
            {
                LOGGER.fine("Thread-"+ThreadID.get()+" is a victim");
            }

        }
        
    }

    public void unlock()//UNLOCK FUNCTION  
    {
        int i = ThreadID.get();
        level[i] = 0;

        LOGGER.fine("Thread-"+ThreadID.get()+" exiting");

        ///

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