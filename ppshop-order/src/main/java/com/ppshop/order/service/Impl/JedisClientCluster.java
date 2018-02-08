package com.ppshop.order.service.Impl;
//package com.ppshop.rest.service.Impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.ppshop.rest.service.JedisClient;
//
//import redis.clients.jedis.JedisCluster;
//
//@Service("jedisClientCluster")
//public class JedisClientCluster implements JedisClient {
//	@Autowired
//	private JedisCluster JedisCluster;
//
//	@Override
//	public String get(String key) {
//		return JedisCluster.get(key);
//	}
//
//	@Override
//	public String set(String key, String value) {
//		return JedisCluster.set(key, value);
//	}
//
//	@Override
//	public String hget(String hkey, String key) {
//		return JedisCluster.hget(hkey, key);
//	}
//
//	@Override
//	public long hset(String hkey, String key, String value) {
//		return JedisCluster.hset(hkey, key, value);
//	}
//
//	@Override
//	public long incr(String key) {
//		return JedisCluster.incr(key);
//	}
//
//	@Override
//	public long expire(String key, int second) {
//		return JedisCluster.expire(key, second);
//	}
//
//	@Override
//	public long ttl(String key) {
//		return JedisCluster.ttl(key);
//	}
//
//	@Override
//	public long del(String key) {
//		return JedisCluster.del(key);
//	}
//
//	@Override
//	public long hdel(String hkey, String key) {
//		return JedisCluster.hdel(hkey, key);
//	}
//
//}
