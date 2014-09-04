package com.voropaev.issuetracker.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.voropaev.issuetracker.utils.IssueTrackerUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.formLogin()
        		.usernameParameter("j_username") // default is username
        		.passwordParameter("j_password") // default is password
        		.loginPage("/login.html") // default is /login with an HTTP get
        		.failureUrl("/login-error") // default is /login-error
        		.loginProcessingUrl("/security_check") // default is /login with an HTTP post
        	.and()
        		.logout().logoutSuccessUrl("/logout")
        	.and()
        		.csrf().disable();
        
        logger.info("configure(HttpSecurity http)");
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsServiceBean());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() {
		return new IssueTrackerUserDetailsService();
	}	
}
