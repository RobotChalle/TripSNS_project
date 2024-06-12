package com.smartwave.tripsns.config;

import com.smartwave.tripsns.interceptor.IDchkInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class SnsConfig implements WebMvcConfigurer {

    @Bean
    public IDchkInterceptor idchkInterceptor() {
        return new IDchkInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(idchkInterceptor())//애플리케이션 내에 인터셉터를 등록
                .addPathPatterns("/main")// 게시판 작성가능한 메인화면 인터셉터 등록
                .addPathPatterns("/post")
                .addPathPatterns("/posting")
                .addPathPatterns("/detail")
                .addPathPatterns("/shorts")
                .addPathPatterns("/short")
                .addPathPatterns("/addShort")
                .addPathPatterns("/addShortVideo")
                .addPathPatterns("/chatList")
//                .addPathPatterns("/**")
//                .excludePathPatterns("/css/**", "/images/**", "/js/**")
//                .excludePathPatterns("/main")
//                .excludePathPatterns("/idchk")
                .excludePathPatterns("/login*") //로그인 요청은 예외처리 -> interceptorx

                .excludePathPatterns("/join*");




    }
}
