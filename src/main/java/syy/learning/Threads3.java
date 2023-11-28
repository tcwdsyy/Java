package syy.learning;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Threads3 {
    public static void main(String[] args) {
        StudentT student = new StudentT();//共享资源对象
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        WriteTask writeTask = new WriteTask(student);//写线程任务
        ReadTask readTask = new ReadTask(student);//读线程任务
        //执行的两个赋值的线程任务
        Long start = System.currentTimeMillis();//开始时间（毫秒值）
        executorService.submit(writeTask);
        executorService.submit(writeTask);

        for (int i = 1; i <= 18; i++) {
            executorService.submit(readTask);
        }

        //停止线程池，但是不停止已提交的任务！等已提交任务都执行完毕！
        executorService.shutdown();

        //询问线程池，任务结束了吗？
        while (true) {
            System.out.println("任务结束了吗？");
            if (executorService.isTerminated() == true) {//证明线程池里的任务都执行完毕
                break;
            }
        }

        Long end = System.currentTimeMillis();//结束时间
        System.out.println(end - start);
    }
}

class StudentT {
    private int age;        //年龄
    //    Lock lock = new ReentrantLock();//读和写的操作下，都锁住，性能过低！
    //有两把锁
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.WriteLock readLock = reentrantReadWriteLock.writeLock();//读锁
    ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();//写锁
    //赋值——写操作
    public void setAge(int age) {
        writeLock.lock();
        try {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.age = age;
        } finally {
            writeLock.unlock();
        }
    }
    //取值——读操作
    public int getAge() {
        readLock.lock();
        try {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.age;
        } finally {
            readLock.unlock();
        }
    }
}

//写操作任务
class WriteTask implements Callable {
    StudentT student;

    public WriteTask(StudentT student) {
        this.student = student;
    }

    public StudentT call() throws Exception {
        student.setAge(100);
        return null;
    }
}

class ReadTask implements Callable {
    StudentT student;

    public ReadTask(StudentT student) {
        this.student = student;
    }

    public StudentT call() throws Exception {
        student.getAge();
        return null;
    }
}
