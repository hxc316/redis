package concor.relock;

import com.sun.javafx.binding.StringFormatter;
import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xc on 2017/9/30.
 */
public class ValidateUser {

    public ReentrantLock checkLock = new ReentrantLock();
    public Condition condition = checkLock.newCondition();
    public ValidateUserOb ob;

    class IdV implements Runnable{
        public IdV(ValidateUserOb ob1){
            ob = ob1;
        }

        @Override
        public void run() {
            if(ob.userId == null){
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checkLock.lock();
                ob.msg.append(" id为空");
                System.out.println("IdV msg:" + ob.msg);
                ob.check = false;
                if(checkLock.isHeldByCurrentThread()){
                    checkLock.unlock();
                }
            }
        }
    }

    class NameV implements Runnable{

        public NameV(ValidateUserOb ob1){
            ob = ob1;
        }

        @Override
        public void run() {
            if(ob.userName == null){
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checkLock.lock();
                ob.msg.append("用户名为空");
                System.out.println("NameV msg:" + ob.msg);
                ob.check = false;
                if(checkLock.isHeldByCurrentThread()){
                    checkLock.unlock();
                }
            }
        }
    }

    @Test
    public void mm(){
        ValidateUserOb validate = new ValidateUserOb();
        Thread t1 = new Thread(new IdV(validate));
        Thread t2 = new Thread(new NameV(validate));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("idV.sate = %s,msg = %s",t1.getState(),validate.msg.toString()).toString());
    }

}
