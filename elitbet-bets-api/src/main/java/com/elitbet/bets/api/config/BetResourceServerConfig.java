package com.elitbet.bets.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 *The @EnableResourceServer annotation adds a filter of type OAuth2AuthenticationProcessingFilter automatically
 *to the Spring Security filter chain.
 */
@Configuration
@EnableResourceServer
public class BetResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .headers()
                .frameOptions()
                .disable()
                .and()
            .authorizeRequests()
                .antMatchers("/help").permitAll()
                .antMatchers("/bets").permitAll()
                .anyRequest().authenticated();
    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("resource-server-rest-api");
    }


    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSourceOauth());
    }

    @Autowired
    private DataSource dataSourceOauth(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/oauth2");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    /*@Primary              another way to tie up this resource server with authorization server
    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
        tokenService.setClientId("spring-security-oauth2-read-write-client");
        tokenService.setClientSecret("spring-security-oauth2-read-write-client-password1234");
        return tokenService;
    }*/


}
