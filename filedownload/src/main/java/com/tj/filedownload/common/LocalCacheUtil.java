package com.tj.filedownload.common;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author peng
 * @createDate 2022/8/2 11:33
 */
@Component
public class LocalCacheUtil<T> {
    @Autowired
    Cache<String, T> cache;

    public void add(String key, T value){
        cache.put(key, value);
    }

    public T get(String key){
        return cache.getIfPresent(key);
    }

    public void delete(String key){
        cache.invalidate(key);
    }

    public void deleteAll(){
        cache.invalidateAll();
    }
}
