package syy.learning;

public class Threads {
    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(new Task());
        t0.start();

        for (int i = 1; i <= 10; i++) {//打印10个数字
            System.out.println(Thread.currentThread().getName() + " - " + i);//打印线程名字和数字
            if (i == 5) {//将t0加入到main线程执行流程中，等待t1线程执行结束后！main再进行竞争时间片！
                //Thread.yield();//放弃！主动放弃当前持有的时间片，回到就绪状态，进入下一次时间片的竞争！
                t0.join();//无限期等待！等待条件为调用join方法的线程执行完毕后！再进入就绪状态，竞争时间片！
            }
        }
    }
}
class Task implements Runnable {//线程实现类——Thread-0
    public void run() {//线程任务
        for (int i = 1; i <= 10; i++) {//打印10个数字
            System.out.println(Thread.currentThread().getName() + " - " + i);//打印线程名字和数字
        }
    }
}
