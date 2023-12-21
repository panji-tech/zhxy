package com.zsz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author :         Mingxuan_x
 * @version :        1.0
 * @Description: 系统工具类
 * @Telephone :      15135964789
 * @createDate :     2021/4/11 14:38
 * @updateUser :     Mingxuan_x
 * @updateDate :     2021/4/11 14:38
 * @updateRemark :   修改内容
 **/
@Component
public class SystemUtil {

    @Value("${file.upload.windows.dir}")
    private String windowsPath;


    @Value("${file.upload.linux.dir}")
    private String linuxPath;

    @Value("${file.upload.mac.dir}")
    private String macPath;


    private final String LINUX = "linux";
    private final String WINDOWS = "windows";

    /**
     * 获取文件存储路径
     *
     * @return:
     * @Author: Mingxuan_X
     * @Date: 2021/4/11
     */

    public String getFilePrefix() {
        String s = null;
        //判断操作系统环境
        String environment = System.getProperty("os.name").toLowerCase();
        if (environment.contains(LINUX)) {
            s = linuxPath;
        } else if (environment.contains(WINDOWS)) {
            s = windowsPath;
        } else {
            s = macPath;
        }
        return s;
    }


}
