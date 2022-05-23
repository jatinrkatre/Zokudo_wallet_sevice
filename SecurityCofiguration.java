package com.wallet.zokudo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityCofiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll().and().csrf().disable().cors();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**/api/**")
        .excludePathPatterns("/**/authorize/**")
        .excludePathPatterns("/**/reversal/**")
        .excludePathPatterns("/**/credit/**")
        .excludePathPatterns("/**/downloadTransactionList/**")
         .excludePathPatterns("/**/downloadDailyPrefundList/**")
         .excludePathPatterns("/**/downloadVirtualAccountList/**")
        .excludePathPatterns("/**/downloadPrefundList/**")
        .excludePathPatterns("/**/downloadReconReport/**")
        .excludePathPatterns("/**/downloadReconSummaryReport/**")
        .excludePathPatterns("/**/downloadPointBalanceList/**")
        .excludePathPatterns("/**/downloadPointBalanceLedgerList/**")
        .excludePathPatterns("/**/downloadHostReconTransactionReport/**");
    }
}
