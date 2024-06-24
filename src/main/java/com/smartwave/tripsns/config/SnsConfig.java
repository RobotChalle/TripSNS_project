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
                .addPathPatterns("/post")// 게시글 등록 인터셉터 등록
                .addPathPatterns("/posting")// 게시글 저장 인터셉터 등록
                .addPathPatterns("/detail") // 자세히보기 인터셉터 등록
                .addPathPatterns("/shorts") // 여기부터 주석 달아주세용
                .addPathPatterns("/short")
                .addPathPatterns("/addShort")
                .addPathPatterns("/addShortVideo")
                .addPathPatterns("/profile")//프로필화면
                .addPathPatterns("/proupdate")//프로필 수정버튼
                .addPathPatterns("/userupdate")//개인정보 수정
                .addPathPatterns("/profileupdate")//프로필정보 수정
                .addPathPatterns("/withdrawform")//회원탈퇴 화면
                .addPathPatterns("/withdrawal")//회원탈퇴버튼
                .addPathPatterns("/followList")// 팔로우,팔로워 리스트
                .addPathPatterns("/manager")

//                .addPathPatterns("/**")
//                .excludePathPatterns("/css/**", "/images/**", "/js/**")
//                .excludePathPatterns("/main")
//                .excludePathPatterns("/idchk")
                .excludePathPatterns("/login*") //로그인 요청은 예외처리 -> interceptorx
                .excludePathPatterns("/join*");




    }
}
