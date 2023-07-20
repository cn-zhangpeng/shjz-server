package com.shjz.zp95sky.shjz.server.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 * @author zhangpeng
 */
@Slf4j
public class CustomRedisUtil {

    private final RedissonClient redissonClient;

    public CustomRedisUtil(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 设置键值并设置有效时间
     */
    public <T> void set(String key, T value, long timeToLive, TimeUnit timeUnit) {
        RBucket<T> rBucket = getBucket(key);
        rBucket.set(value, timeToLive, timeUnit);
    }

    /**
     * 删除键
     */
    public <T> boolean del(String key) {
        RBucket<T> rBucket = getBucket(key);
        return rBucket.delete();
    }

    /**
     * 获取键值对
     */
    public <T> T get(String key) {
        RBucket<T> rBucket = getBucket(key);
        return rBucket.get();
    }

    /**
     * key 是否存在
     */
    public <T> boolean exists(String key) {
        RKeys keys = getKeys();
        return keys.countExists(key) > 0;
    }

    /**
     * key 的失效时间
     */
    public long getExpire(String key) {
        return getBucket(key).remainTimeToLive();
    }

    /**
     * 加锁
     * @param key 加锁key
     * @param waitTime 加锁最大等待时间
     * @param leaseTime 多长时间之后释放锁
     * @param timeUnit 时间单位
     * @return 加锁成功，返回true，否则返回false
     */
    public RLock lock(String key, Integer waitTime, Integer leaseTime, TimeUnit timeUnit) {
        RLock lock = getLock(key);
        try {
            lock.tryLock(waitTime, leaseTime, timeUnit);
            return lock;
        } catch (InterruptedException e) {
            log.error("redisson lock error", e);
            return null;
        }
    }

    /**
     * 设置指定 key 的值，并返回 key 的旧值
     */
    public <T> T getAndSet(String key, T value) {
        RBucket<T> rBucket = getBucket(key);
        return rBucket.getAndSet(value);
    }

    /**
     * 设置指定 key 的值，并返回 key 的旧值，并设置键的有效期
     */
    public <T> T getAndSet(String key, T value, long timeToLive, TimeUnit timeUnit) {
        RBucket<T> rBucket = getBucket(key);
        return rBucket.getAndSet(value, timeToLive, timeUnit);
    }

    private <T> RBucket<T> getBucket(String key) {
        return redissonClient.getBucket(key);
    }

    private RKeys getKeys() {
        return redissonClient.getKeys();
    }

    private RLock getLock(String key) {
        return redissonClient.getLock(key);
    }

}
