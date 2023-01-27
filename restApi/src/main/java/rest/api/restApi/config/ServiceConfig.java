package rest.api.restApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import rest.api.restApi.mapper.Mapper;
import rest.api.restApi.model.MyUser;

import java.util.ArrayList;
import java.util.Collection;

//@Configuration
@EnableWebSecurity
public class ServiceConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        Collection<UserDetails> users = new ArrayList<>();
        Mapper mapper = null;
        MyUser[] myUsers = mapper.getUsers();
        for (MyUser user : myUsers) {
            UserDetails userDetails = User.withUsername(user.username).
                    password(passwordEncoder.encode(user.password)).roles("USER").build();
            users.add(userDetails);
        }
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }

}
