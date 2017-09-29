package countDown.proxyutil;

import countDown.proxyutil.runImpl.Run1;

/**
 * Created by xc on 2017/9/25.
 */
public class Test {

    @org.junit.Test
    public void mm(){
        Run1 run1 = new Run1();
        Aa aa = new Aa();
        aa.mm(run1);
    }
}
