package concor.forktask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by xc on 2017/10/17.
 */
public class Aa extends RecursiveTask<Integer>{

    @Override
    protected Integer compute() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2;
    }

    public static void main(String[] args) {
        Aa a1 = new Aa();
        Aa a2 = new Aa();
        a1.fork();
        a2.fork();
        Integer s1 = a1.join();
        Integer s2 = a2.join();
        System.out.println(s1+s2);

        Collections.synchronizedList(new ArrayList<String>());
    }
}
