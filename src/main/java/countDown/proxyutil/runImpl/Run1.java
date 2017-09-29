package countDown.proxyutil.runImpl;

import countDown.proxyutil.Inter;

/**
 * Created by xc on 2017/9/25.
 */
public class Run1 implements Inter{
    int i = 0;
    @Override
    public synchronized void run() {
        i++;
        System.out.println(String.format("run i = %d",i));
    }
}
