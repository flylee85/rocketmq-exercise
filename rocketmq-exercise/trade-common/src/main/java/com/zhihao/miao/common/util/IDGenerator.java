package com.zhihao.miao.common.util;

import java.util.UUID;

public class IDGenerator {
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
