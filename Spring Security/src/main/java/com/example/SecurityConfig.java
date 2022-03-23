package com.example;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requiresChannel().anyRequest().requiresSecure()
		.and().formLogin()
    		// to show the page where we enter login credentials 
			.loginPage("/login-form") 
			// to process authentication: /login handler method implemented by Spring Security
			.loginProcessingUrl("/myLogin")
			// where to go after successful login
			.defaultSuccessUrl("/success-login",true)
			// to show an error page if the authentication failed
			.failureUrl("/error-login")
			// everyone can access these requests
			.permitAll()
		.and().logout()
			.invalidateHttpSession(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login-form")
			.permitAll()
		.and().authorizeRequests()
			.antMatchers("/greeting").hasAnyRole("GUEST", "PREMIUM")
			.antMatchers("/best/**").hasRole("PREMIUM")
			.anyRequest().authenticated()
		.and().exceptionHandling().accessDeniedPage("/access-denied");
	}
	
	@Autowired 
	private UserDetailsService userDetailsService;
		
    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}