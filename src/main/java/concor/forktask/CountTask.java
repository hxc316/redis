package concor.forktask;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by xc on 2017/10/10.
 */
public class CountTask extends RecursiveTask<Long>{
    private static final int mm = 100;
    private long start;
    private long end;

    public CountTask(long start,long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) < mm;
        if(canCompute){
            System.out.println("单计算...");
            for(long i=start;i<end;i++){
                sum += i;
            }
        }else{
            long step = (start + end) / 10;
            ArrayList<CountTask> tasks = new ArrayList<>();
            long pos = start;
            for(int i =0;i<10;i++){
                long lastOne = pos + step ;
                System.out.println("分计算:begin="+pos+",end="+lastOne);
                CountTask subTask = new CountTask(pos,lastOne);
                pos += step;
                tasks.add(subTask);
                subTask.fork();
            }
            for(CountTask t:tasks){
                sum += t.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int end = 200;
        CountTask task = new CountTask(0,end);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        try {
            long res = result.get();
            System.out.println("result:" + (res + end));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
