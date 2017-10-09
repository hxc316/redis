package concor.relock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by xc on 2017/9/30.
 */
public class CyclicBar {

    static class TC1 implements Runnable {
        private String name;
        private CyclicBarrier cyclicBarrier;

        public TC1(String name1, CyclicBarrier cyclicBarrier1) {
            name = name1;
            cyclicBarrier = cyclicBarrier1;
        }

        @Override
        public void run() {
            try {
                //执行十次 全部集合完毕
                cyclicBarrier.await();
                Thread.sleep(3000);
                System.out.println(name+": 任务完成.");
                //执行十次 全部完成任务
                cyclicBarrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

   static class TC2 implements Runnable {
        boolean flag;
        int N;

        public TC2(boolean flage1, int n) {
            flag = flage1;
            N = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("任务完成...");
            } else {
                System.out.println("集合完毕...");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        boolean flag = false;
        Thread[] ts = new Thread[N];
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10,new TC2(flag,N));
        TC1[] threads = new TC1[N];
        for (int i =0;i<N ;i++){
            System.out.println("士兵"+i+"报道");
            threads[i] = new TC1("s"+i,cyclicBarrier);
            ts[i] = new Thread(threads[i]);
            ts[i].start();
        }
    }
}