package redis;

/**
 * @author wengtao
 * @date 2020/4/25
 **/
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

public class RedisPool {

    private static JedisPool pool;

    public static void setRedisClusterPool(List<String> hostNodes,String password){

        JedisPoolConfig config = new JedisPoolConfig();
        //最大空闲连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
        config.setMinIdle(100);
        config.setMaxIdle(300);
        //最大连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
        config.setMaxTotal(300);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);
        String host = "daily.redis.mockuai.com";
        int port = 6379;

        if(hostNodes != null && hostNodes.size() > 0){
            String[] hostAndPort = hostNodes.get(0).split(":");

            host = hostAndPort[0];
            port = Integer.valueOf(hostAndPort[1]);
        }

        if(password == null || password.equals("")){
            pool = new JedisPool(config, host, port, 3000);
        }else {
            pool = new JedisPool(config, host, port, 3000, password);
        }
    }

    public static Jedis getJedis(){
        Jedis jedis=null;

        try {
            jedis = pool.getResource();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            return jedis;
        }
    }

    public static void returnRedis(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }

    }
}

