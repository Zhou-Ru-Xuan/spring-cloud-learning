package org.zhouruxuan.dashboard9001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class DashboardMain9001
{
    public static void main(String[] args) {
        SpringApplication.run(DashboardMain9001.class, args);
    }
}
