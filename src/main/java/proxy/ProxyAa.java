package proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created by xc on 2017/9/21.
 */
public class ProxyAa {

    @Test
    public void test(){
        InterMm mm = new InterMmImpl();
        InterMm mmOb = (InterMm) Proxy.newProxyInstance(InterMm.class.getClassLoader(),new Class[]{InterMm.class},new MmHandler(mm));
        mmOb.sayMM();
    }
}
