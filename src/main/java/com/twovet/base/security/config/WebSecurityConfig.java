package com.twovet.base.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
 
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/register","/login").permitAll()
				.antMatchers("/home","/config/notif").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
				.antMatchers("/*", "/config/*","/catalog/*","/widget/*", "/widget/master/*", "/widget?*", "/widget/master").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/*","/home", "/booking").access("hasAnyRole('ROLE_DOCTOR', 'ROLE_ADMIN')")
				.antMatchers("/*","/home", "/examination/**").access("hasAnyRole('ROLE_DOCTOR', 'ROLE_ADMIN')")
				.antMatchers("/*","/home", "/examination/summary?**").access("hasAnyRole('ROLE_DOCTOR', 'ROLE_ADMIN')")
				.and()
			.formLogin()
				.loginProcessingUrl("/j_spring_security_check") 
				.loginPage("/login")
				.defaultSuccessUrl("/home")
				.failureUrl("/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
			.exceptionHandling()
				.accessDeniedPage("/403")
				.and()
			.logout()
			.logoutSuccessUrl("/login")
			.permitAll();
	}
}
