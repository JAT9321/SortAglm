package org.example.JUC;

public class 顺序输出 {

    private static volatile int flag = 2;
    private static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                while (flag != 1) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("1");
                flag = 2;
                lock.notifyAll();
            }
        }, "线程1").start();

        new Thread(() -> {
            synchronized (lock) {
                while (flag != 2) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print("2");
                flag = 1;
                lock.notifyAll();
            }
        }, "线程2").start();
    }
}

class Print {
    public static void main(String[] args) {
        SynPrint synPrint = new SynPrint(0, 5);
        new Thread(() -> {
            synPrint.print("a", 0, 1);
        }, "线程1").start();
        new Thread(() -> {
            synPrint.print("b", 1, 0);
        }, "线程2").start();
    }
}

class SynPrint {
    private volatile int flag;
    private final int loop;

    public SynPrint(int flag, int loop) {
        this.flag = flag;
        this.loop = loop;
    }

    void print(String msg, int nowFlag, int nextFlag) {
        for (int i = 0; i < loop; i++) {
            synchronized (this) {
                while (this.flag != nowFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(msg);
                this.flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}
