package wait;

/**
 * Created by xc on 2017/9/29.
 */
public class Vol {

    static boolean isPrepared = false;

    public static void main(String[] args) {
        T1 t1 = new T1();
        try {
            t1.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class T1 extends Thread{
        @Override
        public void run() {
            while (isPrepared){
                System.out.println(" run .... ");
            }
        }
    }

}
