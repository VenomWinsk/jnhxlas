package com.hxht.logprocess.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置访问项目外静态资源方法
 */
@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {


    @Value("${spring.resources.static-locations}")
    private String staticPath;

//    @Value("${offline_file}")
//    private String offlineFile;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        log.info("绑定静态文件:"+staticPath);
//        log.info("绑定静态文件:"+offlineFile);
        //log.info("绑定静态文件:"+staticPath);
        log.info("绑定静态文件"+"/file/");
        //linux 系统
        registry.addResourceHandler("/file/**")
                .addResourceLocations("resources/", "static/", "public/", "META-INF/resources/")
                .addResourceLocations("classpath:resources/", "classpath:static/", "classpath:public/", "classpath:META-INF/resources/")
                //.addResourceLocations("file:"+staticPath)
                .addResourceLocations("file:/file/");
//        registry.addResourceHandler("/file/**")
//                .addResourceLocations("file:"+staticPath)
//                .addResourceLocations("file:/file/offlineFile/");
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")//设置允许跨域的路径
//                .allowedOrigins("*")//设置允许跨域请求的域名
//                .allowCredentials(true)//是否允许证书 不再默认开启
//                .allowedMethods("GET", "POST", "PUT", "DELETE","PATCH")//设置允许的方法
//                .maxAge(3600);//跨域允许时间
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration corsConfiguration = new CorsConfiguration();
//        /* 是否允许请求带有验证信息 */
//        corsConfiguration.setAllowCredentials(true);
//        /* 允许访问的客户端域名 */
//        corsConfiguration.addAllowedOrigin("*");
//        /* 允许服务端访问的客户端请求头 */
//        corsConfiguration.addAllowedHeader("*");
//        /* 允许访问的方法名,GET POST等 */
//        corsConfiguration.addAllowedMethod("*");
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter(urlBasedCorsConfigurationSource);
//    }


}
