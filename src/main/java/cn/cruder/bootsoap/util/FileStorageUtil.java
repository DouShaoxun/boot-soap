package cn.cruder.bootsoap.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.Date;

/**
 * @author dousx
 * @date 2022-05-20 15:24
 */
public class FileStorageUtil {
    private static final String storage = "storage";
    private static final String test = "test";

    public static String storagePath() {
        String property = System.getProperty("user.dir");
        String storagePath = property + File.separator + storage + File.separator + DateUtil.format(new Date(), "yyyy/MM/dd");
        if (!FileUtil.exist(storagePath)) {
            FileUtil.mkdir(storagePath);
        }
        return storagePath;
    }


    public static String testDir() {
        String property = System.getProperty("user.dir");
        String testDir = property + File.separator + test + File.separator;
        if (!FileUtil.exist(testDir)) {
            FileUtil.mkdir(testDir);
        }
        return testDir;
    }


}
