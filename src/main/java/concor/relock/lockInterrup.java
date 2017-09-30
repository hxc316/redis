package concor.relock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xc on 2017/9/29.
 */
public class lockInterrup {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();


    static class R1 implements Runnable{
        private String name;
        private int lock;
        public R1(int lock1,String name){
            lock = lock1;
            this.name = name;
        }
        @Override
        public void run() {
            if(lock == 1){
                try {
                    lock1.lockInterruptibly();
                    System.out.println(this.name + "获得lock1的锁");
                    Thread.sleep(5000);
                    lock2.lockInterruptibly();
                    System.out.println(this.name + "获得lock2的锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.unlock();
                lock2.unlock();
            }else {
                try {
                    lock2.lockInterruptibly();
                    System.out.println(this.name + "获得lock2的锁");
                    Thread.sleep(6000);
                    lock1.lockInterruptibly();
                    System.out.println(this.name + "获得lock1的锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if(lock1.isHeldByCurrentThread()){
                        lock1.unlock();
                    }
                    try {
                        lock1.lockInterruptibly();
                        System.out.println(this.name + "再次获取lock1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(this.name + "退出");
        }
    }

    public static void main(String[] args) {
        R1 r11 = new R1(1,"aa");
        R1 r22 = new R1(2,"bb");

        Thread t1 = new Thread(r11);
        Thread t2 = new Thread(r22);
        t1.start(); // 两个线程互相等待 思索
        t2.start();
        t2.interrupt();//t2打断 t1继续执行

        R2 ra = new R2("xx");
        R2 rb = new R2("yy");

        Thread ta = new Thread(ra);
        Thread tb = new Thread(rb);
//        ta.start();
//        tb.start();
    }

    static class R2 implements Runnable{
        private String name;
        public R2(String name){
            this.name = name;
        }

        public void run() {
            try {
                lock1.lockInterruptibly();
                System.out.println(this.name + "获取到锁,休眠2秒");
                Thread.sleep(2000);
                lock1.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
