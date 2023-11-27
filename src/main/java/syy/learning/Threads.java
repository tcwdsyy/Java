package syy.learning;

public class Threads {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
    }
}
class MyThread extends Thread{
    public void run(){
        for(int i=0;i<=50;i++){
            System.out.println("MyThread: " +i);
        }
    }
}
