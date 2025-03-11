package com.jesper.seckill.util;

import java.util.UUID;

public class UUIDUtil {

    private static final SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static long getSnowflakeId() {
        return idWorker.nextId();
    }
}