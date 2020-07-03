package htec.task.configuration;

import htec.task.model.enums.Role;
import htec.task.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();}


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilterBean() throws Exception {
        JwtAuthenticationFilter authenticationTokenFilter = new JwtAuthenticationFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                authorizeRequests().
                antMatchers(HttpMethod.POST, "/api/v1/cities").hasRole("ADMIN").
                antMatchers(HttpMethod.POST, "/api/v1/routes").hasRole("ADMIN").
                antMatchers(HttpMethod.POST, "/api/v1/airports").hasRole("ADMIN").
                antMatchers(HttpMethod.GET, "/api/v1/cities").hasRole("USER").
                antMatchers(HttpMethod.POST, "/api/v1/comments").hasRole("USER").
                antMatchers(HttpMethod.PUT, "/api/v1/comments").hasRole("USER").
                antMatchers(HttpMethod.DELETE, "/api/v1/comments").hasRole("USER").
                antMatchers(HttpMethod.GET, "/api/v1/routes").hasRole("USER").
                antMatchers("/api/v1/auth").permitAll().
                antMatchers("/api/v1/users").permitAll().
                anyRequest().authenticated();
        http.addFilterBefore(jwtAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }


}
