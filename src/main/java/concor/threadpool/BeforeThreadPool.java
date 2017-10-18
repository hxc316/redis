package concor.threadpool;

import java.util.concurrent.*;

/**
 * Created by xc on 2017/10/9.
 */
public class BeforeThreadPool {

    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println("正在执行: id = " + Thread.currentThread().getId() );
        }
    }

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(5,10,1000, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>()){
            // 调用线程之前执行任务
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("开始执行: id = " + t.getId());
            }
        };
        for (int i=0;i<5;i++){
            Task task = new Task();
            es.execute(task);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        es.shutdown();
    }



}
