package com.hred.common.cache;

/**
 * @author Vinay Thandra
 *
 */
public interface CacheFactory {

	Cache getCache(String cacheName);

	Cache getCache(String cacheName, int timeToLiveSeconds, int maxElementsInCache);	
}
