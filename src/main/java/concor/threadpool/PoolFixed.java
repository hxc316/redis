package concor.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xc on 2017/10/9.
 */
public class PoolFixed {

    static ExecutorService es = Executors.newFixedThreadPool(5);

    static class Task1 implements Runnable{

        String name;

        Task1(String n){
            name = n;
        }

        @Override
        public void run() {
            System.out.println("执行:" + name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            Task1 t = new Task1("n"+i);
            es.execute(t);
        }
    }
}
