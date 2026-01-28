import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.*;
public class future {
    public static void main(String[] args) throws ExecutionException, InterruptedException{
        ExecutorService execeutor=Executors.newFixedThreadPool(3);
        Future<?> ffuture=execeutor.submit(()->42);
        System.out.println(ffuture.get());
        if(ffuture.isDone()){
            System.out.println("Ended !!!");
        }execeutor.shutdown();
    }    
}
