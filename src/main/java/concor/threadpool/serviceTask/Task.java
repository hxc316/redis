package concor.threadpool.serviceTask;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xc on 2017/10/18.
 */
public class Task {
    static ExecutorService es = Executors.newFixedThreadPool(5);
    private DoService doService = new DoService();

    @Test
    public void do1(){
        String[] ss = {"a","b"};
        for(String s:ss){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 多线程调用service执行任务
                        doService.service(s);
                    }catch (Exception e){
                        System.out.println("异常:"+s);
                    }
                }
            });
        }
    }
}
