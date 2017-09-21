package countDown;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xc on 2017/9/21.
 */
public class Demo {

    CountDownLatch begin = new CountDownLatch(1);
    CountDownLatch end = new CountDownLatch(1101);
    private Integer mm = 0;

    @Test
    public void aa() throws InterruptedException {
        for(int i = 0; i<1000 ;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        begin.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("mm = {%d}",mm++));
                    end.countDown();
                }
            }).start();
        }
        begin.countDown();
        end.wait();
        System.out.println(String.format("end : mm = {%d}",mm++));
    }
}
