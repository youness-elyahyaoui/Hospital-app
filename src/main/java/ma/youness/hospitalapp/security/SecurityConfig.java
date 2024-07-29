package ma.youness.hospitalapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        return jdbcUserDetailsManager;
    }

    //@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        String pwd = passwordEncoder.encode("12345");
        System.out.println(pwd);
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(
                User.withUsername("user1").password(pwd).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("3333")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("4444")).roles("ADMIN,USER").build()
        );
        return inMemoryUserDetailsManager;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeHttpRequests().requestMatchers("/delete").hasRole("ADMIN");
        http.authorizeHttpRequests().anyRequest().authenticated();
        return http.build();
    }
}
