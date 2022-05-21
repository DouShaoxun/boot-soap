package cn.cruder.bootsoap.repository;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dousx
 * @date 2022-05-21 16:52
 */
@Service
public class FileRepository {

    private static final Map<String, String> REPOSITORY = new ConcurrentHashMap<>();

    private static final ReentrantLock LOCK = new ReentrantLock();

    /**
     * 保存文件信息
     *
     * @param fileRef  fileRef
     * @param filePath 文件路径
     */
    public void saveFilePath(String fileRef, String filePath) {
        LOCK.lock();
        try {
            REPOSITORY.put(fileRef, filePath);
        } finally {
            LOCK.unlock();
        }

    }


    /**
     * 通过fileRef获取文件信息(文件路径)
     *
     * @param fileRef fileRef
     * @return 文件路径
     */
    public String loadFile(String fileRef) {
        LOCK.lock();
        try {
            return REPOSITORY.getOrDefault(fileRef, "");
        } finally {
            LOCK.unlock();
        }
    }
}
