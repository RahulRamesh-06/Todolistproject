package com.deloitte.project.security;
import com.deloitte.project.filters.Jwtfilter;
import com.deloitte.project.service.Authservice;
import com.deloitte.project.service.UserlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.persistence.Id;
@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserlistService userlistService;

    @Autowired
    Authservice authservice;

    @Autowired
    Jwtfilter jwtFilter;


    /**
     *  Security Implementation
     *
     * @author Rahul Ramesh
     *
     */

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(authservice);
  }
  @Override
  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
//      httpSecurity.csrf().disable().authorizeRequests().antMatchers("/authenticate")
//                .permitAll().antMatchers("/register").permitAll().
//              anyRequest().authenticated().and().sessionManagement()
//              .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//      httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

      httpSecurity.csrf().disable().antMatcher("/api/**").authorizeRequests().anyRequest().authenticated().and().sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }


  @Bean
    public PasswordEncoder passwordEncoder () {
        return NoOpPasswordEncoder.getInstance();
  }

}
