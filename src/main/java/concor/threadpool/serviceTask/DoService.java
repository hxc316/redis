package concor.threadpool.serviceTask;

/**
 * Created by xc on 2017/10/18.
 */
public class DoService {

    public void service(String orderNo){
        if("b".equals(orderNo)){
            throw new RuntimeException("mm");
        }
        System.out.println(orderNo);
    }
}
