package com.zsz.util;

import com.zsz.config.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
@Slf4j
public class LocalUploadUtil {

    public static final String PATH_PREFIX = "/upload/";

    @Resource
    private SystemUtil systemUtil;

    public String upload(MultipartFile file) {
        String newFileName = genNewFileName(file);

        // 创建本地文件夹
        File fileDirectory = new File(systemUtil.getFilePrefix());
        if (!fileDirectory.exists()) {
            // 如果不存在,创建文件夹
            if (!fileDirectory.mkdir()) {
                throw new RuntimeException("文件夹创建失败,路径为：" + fileDirectory);
            }
        }
        try {
            //创建文件
            File destFile = new File(systemUtil.getFilePrefix() + newFileName);
            // 文件传输到本地
            file.transferTo(destFile);
        } catch (IOException e) {
            log.error("文件创建失败:{}", e.getMessage(), e);
            throw new RuntimeException("文件创建失败:{}" + e.getMessage());
        }
        return PATH_PREFIX + newFileName;

    }

    /**
     * 生成文件名称
     */
    private static String genNewFileName(MultipartFile file) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        StringBuilder fileName = new StringBuilder();
        fileName.append(sdf.format(new Date())).append(new Random().nextInt(100)).append(getSuffixName(file));
        return fileName.toString();
    }

    /**
     * 获取文件后缀名称
     */
    private static String getSuffixName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        return suffixName;
    }

}
