/**
 * 
 */
package com.hred.common.cache.memcached;

import com.hred.common.cache.AbstractCacheFactory;
import com.hred.common.cache.Cache;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.SystemException;

public class MemcachedCacheFactory extends AbstractCacheFactory {

	private static MemcachedCacheFactory factory = null;
	
    public static synchronized MemcachedCacheFactory getInstance() {
    	if(factory == null){
    		factory = new MemcachedCacheFactory();
    	}
    	return factory;
    }
	
	@Override
	public Cache getCache(String cacheName, int timeToLiveSeconds,
			int maxElementsInCache) {
		
		Cache memedCache = null;		
		if(cacheName == null) {
			throw new SystemException(ExceptionCodes.CACHE_NAME_NOT_PROVIDED);
		}
		
		memedCache = new SimpleCache(cacheName, timeToLiveSeconds);
		
		return memedCache;
	}
}
