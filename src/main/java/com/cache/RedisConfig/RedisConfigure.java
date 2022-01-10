package com.cache.RedisConfig;

import java.util.HashMap;
import java.util.Map;

public class RedisConfigure {


    public static void main(String[] args) {
        Map<Long, String> index = new HashMap<>();

        System.out.println("Begin->" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024));
        for (long i = 0l; i < 1000000; ++i) {
            index.put(i, " afjmakf akfalf   aksfalk a");
        }
        System.out.println("End->" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024));
    }
}
