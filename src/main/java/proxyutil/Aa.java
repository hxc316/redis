package proxyutil;

import proxyutil.runImpl.Run1;

import java.lang.reflect.Proxy;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xc on 2017/9/25.
 */
public class Aa {

    private static final  int count = 20;
    CountDownLatch begin = new CountDownLatch(1);
    CountDownLatch end = new CountDownLatch(21);

    public void mm(Inter inter){
        final Inter inter1 = (Inter) Proxy.newProxyInstance(Inter.class.getClassLoader(),new Class[]{Inter.class},new MyInvocate(inter));
        for(int i = 0; i<count ;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        begin.await();
                        inter1.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    end.countDown();
                }
            }).start();
        }

    }

    @org.junit.Test
    public void tt(){
        Run1 run1 = new Run1();
        Aa aa = new Aa();
        aa.mm(run1);
        System.out.println("begin countDown");
        begin.countDown();
        try {
            end.wait();
        } catch (InterruptedException e) {
        }
    }
}
