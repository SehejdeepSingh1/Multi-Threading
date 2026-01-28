import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class executorFramework {
    public static void main(String[] args) {
        long starttime=System.currentTimeMillis();
        ExecutorService executor=Executors.newFixedThreadPool(5   );
        for(int i=0;i<10;i++){
            int finali=i;
            executor.submit(()->{
                long result=factorial(finali);
                System.out.println(result);
            });
        }   
        executor.shutdown();
        try{
            executor.awaitTermination(100, TimeUnit.SECONDS);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("total time taken : "+(System.currentTimeMillis()-starttime));
    }
    public static int factorial(int n){
        if(n==1){
            return 1;
        }return n*factorial(n-1);
    }
}
