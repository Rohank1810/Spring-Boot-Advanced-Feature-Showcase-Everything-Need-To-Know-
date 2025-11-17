package com.practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.practice.interceptor.StudentInterceptor;

@Configuration
public class InterceptorConfig  implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new StudentInterceptor())
		.addPathPatterns("/api/**")
		.excludePathPatterns("/newapi/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
