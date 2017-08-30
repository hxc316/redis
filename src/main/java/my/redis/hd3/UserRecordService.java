package my.redis.hd3;

import my.redis.a.RedisFactory;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by xc on 2017/8/30.
 */
public class UserRecordService {

    @Test
    public void addData(){
        UserHdRecord userHdRecord = new UserHdRecord(1,"xc.hu","1000万");
        UserHdRecord userHdRecord1 = new UserHdRecord(2,"kobe","5000万");
        List<UserHdRecord> list = new ArrayList<UserHdRecord>();
        list.add(userHdRecord);
        list.add(userHdRecord1);
        RedisFactory factory = new RedisFactory();
        Jedis jedis = factory.getJRedis();
//        jedis.rpush("recordList",userHdRecord.getName(),userHdRecord.getPrice());
//        jedis.rpush("recordList",userHdRecord1.getName(),userHdRecord1.getPrice());
        jedis.hset("rec"+userHdRecord.getId(),"nm",userHdRecord.getName());
        jedis.hset("rec"+userHdRecord.getId(),"price",userHdRecord.getPrice());
        jedis.hset("rec"+userHdRecord1.getId(),"nm",userHdRecord1.getName());
        jedis.hset("rec"+userHdRecord1.getId(),"price",userHdRecord1.getPrice());
        jedis.expire("rec1",10);
        jedis.expire("rec2",10);
    }

    @Test
    public void test1(){
        UserRecordService service = new UserRecordService();
        service.addData();
        RedisFactory factory = new RedisFactory();
        Jedis jedis = factory.getJRedis();
        while (true){
            Set<String> recs = jedis.keys("rec*");
            if(recs.isEmpty()){
                System.out.println("get keys [res*] is null ");
                try {
                    Thread.currentThread().sleep(4*2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            for(String  rec : recs){
                System.out.println(String.format("name = %s , price = %s",
                        jedis.hget(rec,"nm"),jedis.hget(rec,"price")));
            }
            try {
                Thread.currentThread().sleep(2*2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----------------");
        }



    }


}
