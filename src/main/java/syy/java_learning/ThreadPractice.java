package syy.java_learning;

import java.util.concurrent.*;

public class ThreadPractice {

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();//Executors.newFixedThreadPool(2);
        // synchronized
//        ParkingLot pt = new ParkingLot();
//        for (int i = 0; i < 5; i++) {
//            es.submit(new EnterRun(pt));
//            es.submit(new LeaveRun(pt));
//        }

        //blockingQueue
        BlockingQueue<Boolean> bq = new ArrayBlockingQueue<Boolean>(3);
        Producer p = new Producer(bq);
        Consumer c = new Consumer(bq);
        for (int i = 0; i < 5; i++) {
            es.submit(p);
            es.submit(c);
        }

        es.shutdown();

        try {
            // 等待线程池中的所有任务执行完成，或者超时时间到达
            es.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            // 处理异常
            e.printStackTrace();
        }
        System.out.println("***当前车位：" + (3 - bq.size()));
    }
}

class ParkingLot {
    final Object lock = new Object();
    int space = 3;

    public ParkingLot() {
    }

    public int getEmptySpace() {
        return space;
    }

    public void enter() {
        synchronized (lock) {
            try {
                while (space <= 0) {
                    System.out.println("暂无车位等待中");
                    lock.wait();
                }
                space--;
                System.out.println("已停入一辆，当前车位：" + space);
                lock.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
                lock.notifyAll();
            }
        }
    }

    public void leave() {
        synchronized (lock) {
            try {
                while (space >= 3) {
                    System.out.println("车位已空");
                    lock.wait();
                }
                space++;
                System.out.println("已离开一辆，当前车位：" + space);
                lock.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
                lock.notifyAll();
            }
        }
    }
}

class EnterRun implements Runnable {
    ParkingLot pt;

    EnterRun(ParkingLot pt) {
        this.pt = pt;
    }

    public void run() {
        pt.enter();
    }
}

class LeaveRun implements Runnable {
    ParkingLot pt;

    LeaveRun(ParkingLot pt) {
        this.pt = pt;
    }

    public void run() {
        pt.leave();
    }
}


// BlockingQueue
class Producer implements Runnable {
    BlockingQueue<Boolean> bq;

    public Producer(BlockingQueue<Boolean> bq) {
        this.bq = bq;
    }

    public void run() {
        try {
            bq.put(true);
            System.out.println("已停入一辆，当前车位：" + (3 - bq.size()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    BlockingQueue<Boolean> bq;

    public Consumer(BlockingQueue<Boolean> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        try {
            bq.take();
            System.out.println("已离开一辆，当前车位：" + (3 - bq.size()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
