class sharedResource{
    private int data;
    private boolean hasdata;

    public synchronized void producedata(int value){
        while(hasdata){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data=value;
        hasdata=true;
        System.out.println("Produced : "+value);
        notify();
    }
    public synchronized int consumedata(){
        while(!hasdata){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasdata=false;
        System.out.println("Consumed : "+data);
        notify();
        return data;
    }
}

class Producer implements Runnable{
    private sharedResource resource=new sharedResource();

    public Producer(sharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run(){
        for(int i=0;i<5;i++){
            resource.producedata(i);
        }
    }
}
class Consumer implements Runnable{
    private sharedResource resource=new sharedResource();

    public Consumer(sharedResource resource) {
        this.resource = resource;
    }
    
    @Override
    public void run(){
        for(int i=0;i<5;i++){
            resource.consumedata();
        }
    }
}

public class ThreadCommunication {
    public static void main(String[] args) {
        sharedResource resource = new sharedResource();
        Thread producerThread = new Thread(new Producer(resource));
        Thread consumerThread = new Thread(new Consumer(resource));

        producerThread.start();
        consumerThread.start();
    }
}
