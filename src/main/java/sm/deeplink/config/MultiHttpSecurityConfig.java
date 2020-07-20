package sm.deeplink.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MultiHttpSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Configuration
    @Order(1)
    public class ApiSecurityAdapter extends WebSecurityConfigurerAdapter {

        @Bean
        public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
            return new JwtAuthenticationFilter();
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
                //<= Security only available for /api/**
            http.cors().and().csrf().disable().
                    authorizeRequests()

                    .antMatchers("/resources/**", "/api/token/*", "/api/deeplinks/*", "/api/users/*", "/*", "/login", "/signup",
                            "/dashboard/add-deeplink", "/dashboard/user-profile", "/dashboard/show-deeplink",
                            "/404", "/deeplink/*", "/deeplink/login", "/deeplink/signup",
                            "/deeplink/dashboard/add-deeplink", "/deeplink/dashboard/user-profile",
                            "/deeplink/dashboard/show-deeplink", "/deeplink/404").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http
                    .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

          /*  http.antMatcher("/api/**")
                    .authorizeRequests()
                    .antMatchers("/api/register").permitAll()
                    .antMatchers("/api/login").permitAll()
                    .antMatchers("/api/public").permitAll()
                    .antMatchers("/api/lost").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .apply(new JWTConfigurer(this.tokenProvider))
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
        }
    }


    @Configuration
    public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http // <= Security available for others (not /api/)
                    .authorizeRequests()
                    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                    .antMatchers("/").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/resources/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/central", false)
                    .failureForwardUrl("/login/fail")
                    .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .and()
                    .csrf();

        }
    }
}
