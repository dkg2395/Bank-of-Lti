package com.lti.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable()
	  // .csrf.disble()
	  .authorizeRequests()
	  .antMatchers(HttpMethod.GET,"/bank/**").permitAll()
	  .anyRequest()
	  .authenticated()
	  .and()
	  .httpBasic();
	 }


   @Override
    @Bean
	protected UserDetailsService userDetailsService() {
        UserDetails ramesh = User.builder().username("Durgesh").password(passwordEncoder()
               .encode("Durgesh")).roles("USER").build();
        UserDetails admin = User.builder().username("Dk").password(passwordEncoder()
                .encode("Dk")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(ramesh, admin);
   }
}
