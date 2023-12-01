package syy.java_learning;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPractice2 {
    static Integer num = 0;

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();

//        Thread t1 = new Thread(()->{
//            int i = 0;
//            while(i<10) {
//                lock.lock();
//                if (num % 3 == 0) {
//                    System.out.println("A");
//                    num++;
//                    i++;
//                }
//                lock.unlock();
//            }
//        });
//
//        Thread t2 = new Thread(()->{
//            int i = 0;
//            while(i<10) {
//                lock.lock();
//                if (num % 3 == 1) {
//                    System.out.println("B");
//                    num++;
//                    i++;
//                }
//                lock.unlock();
//            }
//        });
//
//        Thread t3 = new Thread(()->{
//            int i = 0;
//            while(i<10) {
//                lock.lock();
//                if (num % 3 == 2) {
//                    System.out.println("C");
//                    num++;
//                    i++;
//                }
//                lock.unlock();
//            }
//        });

//        t1.start();
//        t3.start();
//        t2.start();

        Object oj = new Object();

        Thread s1 = new Thread(() -> {
            //for (int i = 0; i < 10; i++) {
            while(true) {
                synchronized (oj) {
                    try {
                        while (num % 3 != 0) {
                            oj.wait();
                        }
                        System.out.println("A");
                        num++;
                        oj.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        oj.notifyAll();
                    }
                }
            }
            //}
        });

        Thread s2 = new Thread(() -> {
            //for (int i = 0; i < 10; i++) {
            while(true) {
                synchronized (oj) {
                    try {
                        while (num % 3 != 1) {
                            oj.wait();
                        }
                        System.out.println("B");
                        num++;
                        oj.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        oj.notifyAll();
                    }
                }
            }
            //}
        });

        Thread s3 = new Thread(() -> {
            //for (int i = 0; i < 10; i++) {
            while(true) {
                synchronized (oj) {
                    try {
                        while (num % 3 != 2) {
                            oj.wait();
                        }
                        System.out.println("C");
                        num++;
                        oj.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        oj.notifyAll();
                    }
                }
            }
            //}
        });
        s1.start();
        s2.start();
        s3.start();


    }
}



