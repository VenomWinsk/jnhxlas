package com.hxht.logprocess;

import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.rolemanage.model.Role;
import com.hxht.logprocess.search.model.ForceLoginParams;
import com.hxht.logprocess.search.model.MultiRegionParams;
import com.hxht.logprocess.search.model.UnormalLoginParams;
import com.hxht.logprocess.search.service.AnalyseService;
import com.hxht.logprocess.usermange.model.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


@MapperScan("com.hxht.logprocess.**.dao")
@SpringBootApplication
@EnableCaching
@EnableAsync // 启动异步调用
public class LogprocessApplication implements CommandLineRunner {

    private int corePoolSize = 10;

    private int MaxPoolSize = 200;


    @Autowired
    AnalyseService analyseService;

    public static void main(String[] args) {

        SpringApplication.run(LogprocessApplication.class, args);
    }


    @Bean("taskExe")
    public Executor getAsyncExecutor() {
        // TODO Auto-generated method stub
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池数量，方法: 返回可用处理器的Java虚拟机的数量。
        executor.setCorePoolSize(corePoolSize);
        //最大线程数量
        executor.setMaxPoolSize(MaxPoolSize);
        //线程池的队列容量
        executor.setQueueCapacity(10);
        //线程名称的前缀
        executor.setThreadNamePrefix("this-excutor-");
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }




    @Override
    public void run(String... args) throws Exception {
//        UnormalLoginParams params = new UnormalLoginParams();
//        String[] strings = {"wd"};
//        params.setUnit(Arrays.asList(strings));
//        params.setAna_obj("coremail");
//        params.setCountry("nochina");
////        params.setAna_obj("coremail");
//
//        params.setSuccessNumber(6);
//        params.setTop(10000000);
////        analyseService.forceLoginAnalyse(params);
//        analyseService.unormalLoginAnalyse(params);

//        ForceLoginParams params = new ForceLoginParams();
//        String[] strings = {"wd"};
//        params.setUnit(Arrays.asList(strings));
//        params.setAna_obj("coremail");
//        params.setCountry("nochina");
////        params.setAna_obj("coremail");
//
////        params.setSuccessNumber(6);
//        params.setSuccessThreshold(1);
//        params.setSuccessThreshold(6);
//        params.setTop(10000000);
////        analyseService.forceLoginAnalyse(params);
//        analyseService.forceLoginAnalyse(params);

//        MultiRegionParams params = new MultiRegionParams();
//        String[] strings = {"wd"};
//        params.setUnit(Arrays.asList(strings));
//        params.setAna_obj("coremail");
//        params.setCountry("");
////        params.setAna_obj("coremail");
//
////        params.setSuccessNumber(6);
//        params.setSuccessCountry(2);
////        params.setSuccessThreshold(6);
//        params.setTop(10000000);
////        analyseService.forceLoginAnalyse(params);
//        analyseService.multiRegionAnalyse(params);
//        analyseService.createR2UTmpTable();
//        analyseService.genR2UTmp("","","");
//        analyseService.createR2UTmpTable();
//        analyseService.forceRefreshTmpTable();
    }
}
