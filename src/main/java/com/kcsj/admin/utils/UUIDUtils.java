package com.kcsj.admin.utils;

import java.util.UUID;

/**
 * @author lcz
 * @create 2021-06-14-14:13
 */
public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
