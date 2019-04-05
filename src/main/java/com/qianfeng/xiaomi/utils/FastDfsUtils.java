package com.qianfeng.xiaomi.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class FastDfsUtils {

    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;

    public FastDfsUtils() { }

    public FastDfsUtils(String configlocation) throws IOException, MyException {
        if (configlocation.startsWith("classpath")) {
            configlocation = configlocation.replace("classpath:", FastDfsUtils.class.getResource("/").getPath());
        }

        ClientGlobal.init(configlocation);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
    }

    public static String fileUpLoad(byte[] bs, String ext_name) throws IOException, MyException {
        return fileUpload(bs, ext_name, null);
    }

    public static String fileUpload(byte[] bs, String ext_name, NameValuePair[] nameValuePairs) throws IOException, MyException {
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        String upload_file1 = storageClient1.upload_file1(bs, ext_name, nameValuePairs);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < upload_file1.length(); i++) {
            stringBuilder.append(upload_file1.charAt(i));
            if(i == 0) {
                stringBuilder.append("/");
            }
        }
        return stringBuilder.toString();
    }
}
