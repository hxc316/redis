package countDown.proxyutil;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xc on 2017/9/25.
 */
public class MyInvocate  implements InvocationHandler {

    private Object object;

    public MyInvocate(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(object,args);
    }
}
