package com.shjz.zp95sky.shjz.server.common.utils;

import org.redisson.api.*;

import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 * @author 华夏紫穹
 * @date 2021年03月25日 11:12
 */
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
