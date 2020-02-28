package com.isa.reservation.config;

import com.isa.reservation.security.TokenUtils;
import com.isa.reservation.security.auth.RestAuthenticationEntryPoint;
import com.isa.reservation.security.auth.TokenAuthenticationFilter;
import com.isa.reservation.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService jwtUserDetailsService;   //sadrzi metodu loadByUsername

    // Neautorizovani pristup zastcenim resursima
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Autowired
    TokenUtils tokenUtils;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // komunikacija izmedju klijenta i servera je stateless
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                // za neautorizovane zahteve posalji 401 gresku
                     .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()


                .authorizeRequests()
                .antMatchers("/api/users/register").permitAll()
                .antMatchers("/api/users/register/activate").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/airlines/**").permitAll()
                .antMatchers(HttpMethod.GET, "api/carServices/**").permitAll()
                .antMatchers(HttpMethod.GET,"api/cars/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/flights/search").permitAll()
                .antMatchers(HttpMethod.POST, "/api/flights/filter").permitAll()
                .antMatchers("/api/users/findUser").permitAll()
                .and();
              //  .anyRequest().authenticated();

        // presretni svaki zahtev filterom
        http.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);

        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
        web.ignoring().antMatchers(HttpMethod.GET, "/api/cars");
//        web.ignoring().antMatchers(HttpMethod.POST, "/api/auth/login");
//        web.ignoring().antMatchers(HttpMethod.POST, "/api/users/register");
//        web.ignoring().antMatchers(HttpMethod.PUT, "/api/users/register/activate");
//        web.ignoring().antMatchers(HttpMethod.GET, "/api/users/findUser");
//        web.ignoring().antMatchers(HttpMethod.GET, "/api/airlines/**");
//        web.ignoring().antMatchers(HttpMethod.GET, "/api/destinations/**");
//        web.ignoring().antMatchers(HttpMethod.GET, "/api/flights/**");
//        //web.ignoring().antMatchers(HttpMethod.POST, "/api/flights/search");
//        web.ignoring().antMatchers(HttpMethod.GET, "/api/seatArrangement/**");
//        web.ignoring().antMatchers(HttpMethod.GET, "/api/cars/**");
//        web.ignoring().antMatchers(HttpMethod.GET, "/api/cars/");
//        web.ignoring().antMatchers(HttpMethod.GET, "/api/carServices/**");
//        web.ignoring().antMatchers(HttpMethod.GET, "/api/carReservations/available");
//        web.ignoring().antMatchers(HttpMethod.GET, "/api/reservations/**");

    //    web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");

    }

}
