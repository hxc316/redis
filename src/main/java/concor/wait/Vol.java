package concor.wait;

/**
 * Created by xc on 2017/9/29.
 */
public class Vol {
    // 如果没有加volatitle T1一直无法退出 获取不到 isPrepared=true的值
    static volatile boolean isPrepared = false;

    public static void main(String[] args) {
        T1 t1 = new T1();
        t1.start();
        try {
            t1.sleep(1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isPrepared = true;
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

    static class T1 extends Thread{
        @Override
        public void run() {
            while (true){
                if(isPrepared){
                    System.out.println(" run .... ");
                    break;
                }
            }
        }
    }

}
