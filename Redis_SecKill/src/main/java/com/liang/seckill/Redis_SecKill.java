package com.liang.seckill;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * 抢购小程序的业务逻辑处理
 */
public class Redis_SecKill {

   private static Logger logger = Logger.getLogger(Redis_SecKill.class);

   //测试redis是否连接成功
    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1",6379);
        String ping = jedis.ping();
        System.out.println(ping);
    }

    public static boolean do_secKill(String user, String product) throws InterruptedException {

    	//创建redis连接
        Jedis jedis = new Jedis("127.0.0.1",6379);

        //在高并发的情况下,防止超卖现象的发生,添加乐观锁
        jedis.watch(product);
        
        //从redis中查询商品的剩余库存
        int stock = Integer.parseInt(jedis.get(product));

        //库存减小到0,不在售出
        if (stock <= 0){
            return  false;
        }

        //开启redis事物,确保库存变化在同一个事务中
        Transaction multi = jedis.multi();
        //库存减一
        multi.decr(product);
        //添加抢购成功用户的userid
        multi.lpush("user",user);
        //提交事物
        List<Object> list = multi.exec();
        if (list == null){
            logger.info(user+" : 秒杀失败");
        }else{
            logger.info(user+" : 秒杀成功");
        }

        return true;
    }
}
