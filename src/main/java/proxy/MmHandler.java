package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xc on 2017/9/21.
 */
public class MmHandler implements InvocationHandler{

    private Object object;

    public MmHandler(Object o){
        this.object = o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(object,args);
    }
}
