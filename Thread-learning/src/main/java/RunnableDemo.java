/**
 * ClassName: ThreadDemo
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author alec
 * @Create 2024/3/2 21:09
 * @Version 1.0
 */
public class RunnableDemo implements Runnable{
    private Thread t;
    private String threadName;

    RunnableDemo(String name){
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start (){
        System.out.println("Starting " + threadName);
        if(t == null){
            t = new Thread(this, threadName);
            t.start();
        }
    }
}