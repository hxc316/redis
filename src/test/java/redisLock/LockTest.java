package redisLock;

import org.junit.Test;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xc on 2017/9/21.
 */
public class LockTest {

    private static final String LOCK_NO = "redis_distribution_lock_no_";
    private static int i = 0;

    @Test
    public void aa(){
        ExecutorService service = Executors.newFixedThreadPool(20);
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        RedisDistributionLockImpl redisLock = new RedisDistributionLockImpl(redisTemplate);
        service.execute(new Runnable() {
            @Override
            public void run() {
                task(Thread.currentThread().getName());
            }
        });
    }

    public void task(String name) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        //创建一个redis分布式锁
        RedisDistributionLockImpl redisLock = new RedisDistributionLockImpl(redisTemplate);
        //加锁时间
        Long lockTime;
        if ((lockTime = redisLock.lock((LOCK_NO+1)+"", name))!=null){
            //开始执行任务
            System.out.println(name + "任务执行中"+(i++));
            //任务执行完毕 关闭锁
            redisLock.unlock((LOCK_NO+1)+"", lockTime, name);
        }

    }
}
