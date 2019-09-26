package org.fengfei.lanproxy.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @ClassName: FileUtils
 * @Description:
 * @Author: huangzf.
 * @Version: V1.0
 * @CreateDate: 2019-09-26 09:39
 */
public class FileUtils {

    private static Logger log = LoggerFactory.getLogger(FileUtils.class);

    public FileUtils() {
    }

    public static File[] getFiles(String path) {
        File file = new File(path);
        File[] listFiles = file.listFiles();
        return listFiles;
    }

    public static void addFile() {
    }

    public static void copyFileUsingStream(File source, File destination) throws IOException {
        InputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream(source);
            out = new FileOutputStream(destination);
            byte[] buffer = new byte[1024];

            int length;
            while((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } catch (Exception var9) {
            log.error("复制文件error: {}", var9);
        } finally {
            if(in != null) {
                in.close();
            }

            if(out != null) {
                out.close();
            }

        }

    }

    public static void deleteFile(String sFile) {
        File file = new File(sFile);
        file.delete();
    }

    public static void reNameFile(String sFile) throws IOException {
        File file = new File("D:\\\\\\\\files\\\\test_copy.txt");
        File file2 = new File("D:\\\\\\\\files\\\\test_copy1.txt");
        if(file2.exists()) {
            throw new IOException("file exists");
        } else {
            boolean success = file.renameTo(file2);
            if(!success) {
                ;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        reNameFile("");
    }
}
