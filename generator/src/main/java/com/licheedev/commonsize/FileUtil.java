package com.licheedev.commonsize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by John on 2018/3/5.
 */

public class FileUtil {

    public static void removeFile(File destFile) {
        if (destFile == null || !destFile.exists()) {
            return;
        }

        if (destFile.isDirectory()) {
            File[] files = destFile.listFiles();
            if (files != null) {
                for (File file : files) {
                    removeFile(file);
                }
            }
        }
        destFile.delete();
    }

    public static void copyDir(File src, File dest) {

        if (src.isDirectory()) {

            if (!dest.exists()) {
                dest.mkdirs();
            }

            File[] files = src.listFiles();
            if (files != null) {
                for (File file : files) {
                    copyDir(file, new File(dest, file.getName()));
                }
            }
        } else {
            copyFile(src, dest);
        }
    }

    public static void copyFile(File src, File dest) {

        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;

        try {

            fi = new FileInputStream(src);
            fo = new FileOutputStream(dest);
            in = fi.getChannel();//�õ���Ӧ���ļ�ͨ��
            out = fo.getChannel();//�õ���Ӧ���ļ�ͨ��
            in.transferTo(0, in.size(), out);//��������ͨ�������Ҵ�inͨ����ȡ��Ȼ��д��outͨ��  
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                fo.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
