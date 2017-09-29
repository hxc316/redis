package countDown;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xc on 2017/9/25.
 */
public class CountDownLatchDemo {

    static CountDownLatch workMsg = new CountDownLatch(2);

    public static void main(String[] args) {
        System.out.println("准备去玩,等待name1,name2");
        Worker worker1 = new Worker("name1",2000);
        Worker worker2 = new Worker("name2",4000);
        worker1.start();
        worker2.start();
        try {
            worker1.join();
            worker2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        try {
//        /**
//             * 执行到这里的时候直接报异常
//             * Exception in thread "main" java.lang.IllegalMonitorStateException
//             */
//            workMsg.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("人来齐了..." );

    }


    static class Worker extends Thread{

        private String workerName;
        private Integer time;

        public Worker(String workerName,Integer time){
            this.workerName = workerName;
            this.time = time;
        }

        @Override
        public void run() {
            try {
                Thread.currentThread().sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(workerName+": 干活干完了,等下我");
            workMsg.countDown();
        }
    }
}
