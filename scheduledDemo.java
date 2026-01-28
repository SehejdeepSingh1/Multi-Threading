import java.util.concurrent.Executor;
import java.util.concurrent.*;
public class scheduledDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler=Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(
            ()->System.out.println("Task executed after every 5 seconds !!"),
            5, 5, TimeUnit.SECONDS);
            scheduler.schedule(()->{
                System.out.println("Initalising shutdown");
                scheduler.shutdown();
            }, 20,TimeUnit.SECONDS );
    }
}
