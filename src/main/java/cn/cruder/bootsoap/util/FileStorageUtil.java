package cn.cruder.bootsoap.util;

import cn.hutool.core.io.FileUtil;

import java.io.File;

/**
 * @author dousx
 * @date 2022-05-20 15:24
 */
public class FileStorageUtil {
    private static final String storage = "storage";

    public static String storagePath() {
        String property = System.getProperty("user.dir");
        String storagePath = property + File.separator + storage;
        if (!FileUtil.exist(storagePath)) {
            FileUtil.mkdir(storagePath);
        }
        return storagePath;
    }
}
