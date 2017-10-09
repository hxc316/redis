package concor.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by xc on 2017/10/9.
 */
public class SchePool {

    static ScheduledExecutorService es = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) {
        es.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()) + "来了");
            }
        },0,2, TimeUnit.SECONDS);
    }
}
