package redisLock;

import my.redis.a.RedisFactory;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by xc on 2017/9/21.
 */
public class JredisLock {

    @Test
    public void aa(){
        RedisFactory factory = new RedisFactory();
        Jedis jedis = factory.getJRedis();
//        jedis.setnx("login",);
//        factory.getJRedis().
    }
}
