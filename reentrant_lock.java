import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class reentrant_lock {
    private final Lock lock=new ReentrantLock();

    public void outermethod(){
        lock.lock();
        try{
            System.out.println(("Outer Method"));
            innermethod();
        }finally{
            lock.unlock();
        }
    }

    public void innermethod(){
        lock.lock();
        try{
            System.out.println("Inner Method");
        }finally{
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        reentrant_lock example=new reentrant_lock();
        example.outermethod();f
    }
}
