package org.k8go4go.myprofile.config;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:src/main/resources/static/upload/");

        registry.addResourceHandler("/upload/temp/**")
                .addResourceLocations("file:src/main/resources/static/upload/temp");
    }

    @Bean
    public FilterRegistrationBean<Filter> logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();

        filterRegistrationBean.setFilter(new LogFilter());	// LogFilter등록
        filterRegistrationBean.setOrder(1);	// 먼저 적용할 필터 순서
        filterRegistrationBean.addUrlPatterns("/*"); //모든 url 다 적용 -> 모든 요청에 다 필터 적용
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();

        filterRegistrationBean.setFilter(new LoginCheckFilter());	// LoginCheckFilter등록
        filterRegistrationBean.setOrder(2);	// 먼저 등록할 필터 순서
        filterRegistrationBean.addUrlPatterns("/*"); //모든 url 다 적용 -> 모든 요청에 다 필터 적용
        return filterRegistrationBean;
    }
}