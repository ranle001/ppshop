package com.ppshop.sso.service;

/**
 * redis操作接口
 * @author Administrator
 *
 */
public interface JedisClient {
	/**
	 * 获取key的值
	 * @param key
	 * @return
	 */
	String get(String key);
	/**
	 * 保存key value
	 * @param key
	 * @return
	 */
	String set(String key, String value);
	/**
	 * 返回哈希表中指定字段的值
	 * @param hkey
	 * @param key
	 * @return
	 */
	String hget(String hkey, String key);
	/**
	 * 为哈希表中的字段赋值
	 * @param hkey
	 * @param key
	 * @param value
	 * @return
	 */
	long hset(String hkey, String key, String value);
	/**
	 * 将 key 中储存的数字值增一
	 * @param key
	 * @return
	 */
	long incr(String key);
	/**
	 * 设置过期时间
	 * @param key
	 * @param second
	 * @return
	 */
	long expire(String key, int second);
	/**
	 * 查看过期时间
	 * @param key
	 * @return
	 */
	long ttl(String key);
	/**
	 * 删除操作
	 * @param key
	 * @return
	 */
	long del(String key);
	/**
	 * 删除哈希表 key 中的一个或多个指定字段
	 * @param hkey
	 * @param key
	 * @return
	 */
	long hdel(String hkey,String key);
}
