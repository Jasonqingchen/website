package com.example.LqcSpringBoot.ut;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author：liuqingchen
 * @since：2022/06/09
 */
@Configuration
public class MyFilter extends WebMvcConfigurerAdapter {
    /**
     * addPathPatterns 添加拦截的请求路径
     * excludePathPatterns 就代表放行的请求
     * addResourceHandler 对静态资源的放行
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("自定义拦截器");
            registry.addInterceptor(new UserConfig()).addPathPatterns("/container/**","/menu/**").excludePathPatterns("/error/");
            super.addInterceptors(registry);
    }
}
