package com.shjz.zp95sky.shjz.server.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 * @author zhangpeng
 */
@Slf4j
public class RedisUtil {

    private final RedissonClient redissonClient;

    public RedisUtil(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 设置键值
     */
    public <T> void set(String key, T value) {
        getBucket(key).set(value);
    }

    /**
     * 设置键值并设置有效时间
     */
    public <T> void set(String key, T value, long timeToLive, TimeUnit timeUnit) {
        getBucket(key).set(value, timeToLive, timeUnit);
    }

    /**
     * 删除键
     */
    public boolean del(String key) {
        return getBucket(key).delete();
    }

    /**
     * 获取键值对
     */
    public Object get(String key) {
        return getBucket(key).get();
    }

    /**
     * key 是否存在
     */
    public Boolean exists(String key) {
        return getBucket(key).isExists();
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
     * @return 加锁成功，返回true，否则返回false
     */
    public boolean lock(String key) {
        RLock lock = redissonClient.getLock(key);
        return lock.tryLock();
    }

    /**
     * 加锁
     * @param key 加锁key
     * @param waitTime 加锁最大等待时间
     * @param leaseTime 多长时间之后释放锁
     * @param timeUnit 时间单位
     * @return 加锁成功，返回true，否则返回false
     */
    public boolean lock(String key, Integer waitTime, Integer leaseTime, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(3, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("redisson lock error", e);
            return false;
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

}
