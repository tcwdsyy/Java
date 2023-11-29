package syy.learning;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Threads4 {
    public static void main(String[] args) {
        Arr a1 = new Arr();
    // 继承Thread
        SimpleThread t1 = new SimpleThread(a1);

    // 实现runnable接口
        SimpleRunnable sr = new SimpleRunnable(a1);
        Thread t2 = new Thread(sr);

        t1.start();
        t2.start();

    // 线程池
        ExecutorService es = Executors.newFixedThreadPool(3);
        es.submit(new Callable<String>(){
            public String call(){
                System.out.println("The callable thread is running!");
                return "hello";
            }
        });



        Future<String> f = es.submit( () -> "hello2"); // a lambda callable function that returns a String type message "hello2"
//        String value1 = f.get();
//        System.out.println(value1);
        es.shutdown();

    }
}

// 继承Thread
class SimpleThread extends Thread {
    Arr taskData;
    public SimpleThread(Arr input) {
        taskData = input;
    }

    public void run(){
        for(int i=0; i<10; i++) {
            if (i % 5 == 0) {
                System.out.println("线程1得到了偶数！休眠了！");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            taskData.setData(1);
            System.out.println("t1:" + taskData.getData());
        }
    }
}

// 实现runnable接口
class SimpleRunnable implements Runnable {
    Arr taskData;
    public SimpleRunnable(Arr in){
        taskData=in;
    }
    public void run(){
        for(int i=0; i<10; i++){
            taskData.setData(2);
            System.out.println("t2:" + taskData.getData());
        }
    }
}

class  Arr {
    int data;
    Arr() {
        data = 0;
    }
    public synchronized void setData(int num) { // synchronized defined in front of return type
        data = num;
    }
    public int getData() {
        //synchronized((Integer data){ // need to be an object
            return data;
        //}
    }
}
