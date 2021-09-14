package com.hxht.logprocess.core.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UniqueIdUtil {

    private static long uid = 0L;

    private static Lock lock = new ReentrantLock();
    /**
     * 返回唯一ID
     ** 缓冲压力,返回基于时间的唯一ID
     * 例：
     * 1415842817170
     * @return
     * @throws Exception
     */
    public static long genId() {
        lock.lock();
        try {
            long id = 0L;
            do
                id = System.currentTimeMillis();
            while (id == uid);
            uid = id;
            return id;
        } finally {
            lock.unlock();
        }
    }
}
