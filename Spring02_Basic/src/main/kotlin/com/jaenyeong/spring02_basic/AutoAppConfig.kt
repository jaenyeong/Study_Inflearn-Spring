package com.jaenyeong.spring02_basic

import com.jaenyeong.spring02_basic.common.CustomInterceptor
import com.jaenyeong.spring02_basic.common.CustomProxyInterceptor
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType.ANNOTATION
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@ComponentScan(
    // basePackages를 지정하지 않으면 @ComponentScan 애너테이션이 태깅된 클래스의 패키지가 스캔 시작 위치가 됨
    // 일반적으로 @ComponentScan 태깅 클래스를 프로젝트 최상단에 위치시킴
    // 스프링부트의 경우 @SpringBootApplication 애너테이션 안에 @ComponentScan 애너테이션이 존재하며
    // 일반적으로  @SpringBootApplication 태깅 클래스를 프로젝트 최상단에 위치시킴
//    basePackages = ["com.jaenyeong"],
    excludeFilters = [Filter(type = ANNOTATION, classes = [Configuration::class])]
)
class AutoAppConfig(
    private val customInterceptor: CustomInterceptor,
    private val customProxyInterceptor: CustomProxyInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(customInterceptor)
            .addPathPatterns("/custom-log/**")

        registry.addInterceptor(customProxyInterceptor)
            .addPathPatterns("/custom-proxy-log/**")
    }
}
