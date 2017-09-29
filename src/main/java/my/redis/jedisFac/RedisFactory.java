package my.redis.jedisFac;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by xc on 2017/8/30.
 */
public class RedisFactory {
    @Test
    public void aa(){
        //获得连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数, 默认8个
        config.setMaxTotal(8);
        //设置最大空闲连接数, 默认8个
        config.setMaxIdle(8);
        //获得连接池
        JedisPool jedisPool = new JedisPool(config, "192.168.29.197",6379);
        //获得核心对象
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("name2", "lisi");                       //保存数据
            String value = jedis.get("name2");                //获取数据
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();                                    //释放资源
        }
    }

    public Jedis getJRedis(){
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数, 默认8个
        config.setMaxTotal(8);
        //设置最大空闲连接数, 默认8个
        config.setMaxIdle(8);
        //获得连接池
        JedisPool jedisPool = new JedisPool(config, "192.168.29.197",6379);
        //获得核心对象
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();                                    //释放资源
        }
        return jedis;
    }
}
