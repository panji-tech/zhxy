package com.zsz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
public class ZhxyApplication {

    public static void main(String[] args) throws UnknownHostException {
        log.info("服务开始启动~");
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ZhxyApplication.class, args);
        ConfigurableEnvironment env = applicationContext.getEnvironment();

        log.info("\n---------------- 关注公众号【ITSource每日分享】,每天分享一个 IT 资源------------------------------------------\n\t" +
                        "Application: '{}' is running! Access URLs:\n\t" +
                        "后端地址: \t\thttp://127.0.0.1:{}\n\t" +
                        "API Doc: \thttp://127.0.0.1:{}/swagger-ui.html\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                //InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                //InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
        log.info("-------服务启动完成:{}-------", InetAddress.getLocalHost().getHostAddress());
    }

}
