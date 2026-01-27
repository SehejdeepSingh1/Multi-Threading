import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class locks_java {
    static int balance=100;

    private final Lock lock =new ReentrantLock();

    public void withdraw(int amount){
        System.out.println(Thread.currentThread().getName()+" attempting to withdraw : "+ amount);
        try {
            // lock.lock();
            if(lock.tryLock(1000,TimeUnit.MILLISECONDS)){
                if(amount<=balance){
                    try {
                        System.out.println(Thread.currentThread().getName()+" proceeding with withdraw");
                        Thread.sleep(3000);
                        balance-=amount;
                        System.out.println(Thread.currentThread().getName()+" completed withdraw");
                    } catch (Exception e) {
                        // Thread.currentThread().interrupt();
                    }
                    finally{
                        lock.unlock();
                    }
                }else{
                    System.out.println("Insufficient balance");
                }
            }else{
                System.out.println("Could not acquire the lock");
            }
        } catch (Exception e) {
            // Thread.currentThread().interrupt();
        }
    }
    public static void main(String[] args) {
        locks_java sbi=new locks_java();
        Runnable task=new Runnable(){
            @Override
            public void run(){
                sbi.withdraw(50);
            }
        };
        Thread t1=new Thread(task);
        Thread t2=new Thread(task);
        t1.start();
        t2.start();
    }
}
