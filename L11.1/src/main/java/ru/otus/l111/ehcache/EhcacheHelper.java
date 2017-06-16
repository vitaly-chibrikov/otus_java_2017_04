package ru.otus.l111.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

/**
 * Created by tully.
 */
public class EhcacheHelper {
    static final int MAX_ENTRIES = 10;
    static final int LIFE_TIME_SEC = 1;
    static final int IDLE_TIME_SEC = 1;
    
    static Cache createLifeCache(CacheManager manager, String name) {
        CacheConfiguration configuration = new CacheConfiguration(name, MAX_ENTRIES);
        configuration.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.FIFO)
                .timeToLiveSeconds(LIFE_TIME_SEC);

        Cache cache = new Cache(configuration);
        manager.addCache(cache);

        return cache;
    }

    static Cache createIdleCache(CacheManager manager, String name) {
        CacheConfiguration configuration = new CacheConfiguration(name, MAX_ENTRIES);
        configuration.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.FIFO)
                .timeToIdleSeconds(IDLE_TIME_SEC);

        Cache cache = new Cache(configuration);
        manager.addCache(cache);

        return cache;
    }

    static Cache createEternalCache(CacheManager manager, String name) {
        CacheConfiguration configuration = new CacheConfiguration(name, MAX_ENTRIES);
        configuration.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.FIFO)
                .eternal(true);

        Cache cache = new Cache(configuration);
        manager.addCache(cache);

        return cache;
    }
}
