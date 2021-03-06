package com.hazelcast.examples;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryExpiredListener;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryRemovedListener;
import javax.cache.event.CacheEntryUpdatedListener;
import java.io.Serializable;
import java.util.Iterator;

/**
 * Simple Cache Entry Listener to print the received events to System.out
 */
public class MyCacheEntryListener<K, V> implements CacheEntryCreatedListener<K, V>,
        CacheEntryUpdatedListener<K, V>, CacheEntryExpiredListener<K, V>,
        CacheEntryRemovedListener<K, V>, Serializable {

    public MyCacheEntryListener() {
    }

    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
        System.out.println("CREATE EVENT RECEIVED: ");
        System.out.println(printEvent(cacheEntryEvents));
    }

    @Override
    public void onExpired(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
        System.out.println("EXPIRE EVENT RECEIVED: ");
        System.out.println(printEvent(cacheEntryEvents));
    }

    @Override
    public void onRemoved(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
        System.out.println("REMOVE EVENT RECEIVED: ");
        System.out.println(printEvent(cacheEntryEvents));
    }

    @Override
    public void onUpdated(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
        System.out.println("UPDATE EVENT RECEIVED: ");
        System.out.println(printEvent(cacheEntryEvents));
    }

    private String printEvent(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents){
        StringBuilder sb = new StringBuilder();
        final Iterator<CacheEntryEvent<? extends K, ? extends V>> iterator = cacheEntryEvents.iterator();
        while (iterator.hasNext()){
            final CacheEntryEvent<? extends K, ? extends V> next = iterator.next();
            sb.append("Key: ");
            sb.append(next.getKey());
            sb.append(", Value:");
            sb.append(next.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }
}