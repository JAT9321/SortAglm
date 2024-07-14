package org.example.多线程;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : JAT
 * @version : 1.0
 * @email : zgt9321@qq.com
 * @since : 2024/6/2
 **/
public class ThreeThread {
    static Thread at4 = null;
    static Thread bt4 = null;
    static Thread ct4 = null;

    public static void main(String[] args) throws InterruptedException {
        // Print printA = new Print(5, 0, 1);
        // Print printB = new Print(5, 1, 2);
        // Print printC = new Print(5, 2, 0);
        // Thread a = new Thread(printA, "A");
        // Thread b = new Thread(printB, "B");
        // Thread c = new Thread(printC, "C");
        // a.start();
        // b.start();
        // c.start();

        // ReentrantLock lock = new ReentrantLock();
        // Condition a = lock.newCondition();
        // Condition b = lock.newCondition();
        // Condition c = lock.newCondition();
        // Print2 printA = new Print2(5, 0, 1, lock, a, b);
        // Print2 printB = new Print2(5, 1, 2, lock, b, c);
        // Print2 printC = new Print2(5, 2, 0, lock, c, a);
        // Thread at = new Thread(printA, "A");
        // Thread bt = new Thread(printB, "B");
        // Thread ct = new Thread(printC, "C");
        // at.start();
        // bt.start();
        // ct.start();


        // ReentrantLock lock = new ReentrantLock();
        // Condition a = lock.newCondition();
        // Condition b = lock.newCondition();
        // Condition c = lock.newCondition();
        // Print3 printA = new Print3(5, lock, a, b);
        // Print3 printB = new Print3(5, lock, b, c);
        // Print3 printC = new Print3(5, lock, c, a);
        // Thread at = new Thread(printA, "A");
        // Thread bt = new Thread(printB, "B");
        // Thread ct = new Thread(printC, "C");
        // at.start();
        // bt.start();
        // ct.start();
        // Thread.sleep(1000);
        // try {
        //     lock.lock();
        //     a.signalAll();
        // } finally {
        //     lock.unlock();
        // }


        Print4 print4 = new Print4(5);
        at4 = new Thread(() -> {
            print4.run(bt4);
        }, "A");
        bt4 = new Thread(() -> {
            print4.run(ct4);
        }, "B");
        ct4 = new Thread(() -> {
            print4.run(at4);
        }, "C");
        at4.start();
        bt4.start();
        ct4.start();
        LockSupport.unpark(at4);
    }
}

class Print implements Runnable {

    static volatile int flag = 0;
    static final Object lock = new Object();
    int count = 0;
    int id = 0;
    int next = 0;


    public Print(int count, int id, int next) {
        this.count = count;
        this.id = id;
        this.next = next;
    }

    void printX(int i) {
        System.out.println(Thread.currentThread().getName() + " " + i);
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            synchronized (lock) {
                while (flag != id) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                printX(i);
                flag = next;
                lock.notifyAll();
            }
        }
    }
}

class Print2 implements Runnable {

    int count;
    int id;

    int nextId;
    static volatile int flag = 0;
    ReentrantLock lock;
    Condition condition;
    Condition next;

    public Print2(int count, int id, int nextId, ReentrantLock lock, Condition condition, Condition next) {
        this.count = count;
        this.id = id;
        this.condition = condition;
        this.next = next;
        this.lock = lock;
        this.nextId = nextId;
    }

    void printX(int i) {
        System.out.println(Thread.currentThread().getName() + " " + i);
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            lock.lock();
            try {
                while (flag != id) {
                    condition.await();
                }
                printX(i);
                flag = nextId;
                next.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

class Print3 implements Runnable {

    int count;
    ReentrantLock lock;
    Condition condition;
    Condition nextCondition;

    public Print3(int count, ReentrantLock lock, Condition condition, Condition nextCondition) {
        this.count = count;
        this.lock = lock;
        this.condition = condition;
        this.nextCondition = nextCondition;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            lock.lock();
            try {
                condition.await();
                System.out.println(Thread.currentThread().getName() + " " + i);
                nextCondition.signalAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}

class Print4 {

    int count;

    public Print4(int count) {

        this.count = count;
    }

    void printX(int i) {
        System.out.println(Thread.currentThread().getName() + " " + i);
    }

    public void run(Thread nextThread) {
        for (int i = 0; i < count; i++) {
            LockSupport.park();
            printX(i);
            LockSupport.unpark(nextThread);
        }
    }
}