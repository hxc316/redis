package concor.wait;

/**
 * Created by xc on 2017/9/29.
 */
public class AaWait {

    final static Object ob = new Object();

    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
//      如果t1先开始 那么t2一直都收不到t1的通知
        t2.start();
        t1.start();
    }

    static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (ob){
                System.out.println("T1:我换个鞋马上就好...");
                try {
                    Thread.currentThread().sleep(1000 * 3);
                    System.out.println("T1:我鞋换好了 通知T2...");
                    ob.notify();
                    System.out.println("T1: end...");
//                    ob.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class T2 extends Thread{
        @Override
        public void run() {
            synchronized (ob){
                System.out.println("T2:我已经准备好了,我等你...");
                try {
                    ob.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T2: end...");
            }
        }
    }

    static class C {

    }
}
