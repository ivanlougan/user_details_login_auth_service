package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class LoginConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated());
        http.formLogin(form -> form
                .loginPage("/login")
         //       .usernameParameter("user")
         //       .passwordParameter("pass")
         //       .loginProcessingUrl("/zaloguj")
                .permitAll());
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/byebye").permitAll()
        );

        //wyloguj po wysłaniu żądania GET pod adres /logout
        //po wylogowaniu przekieruj do strony /byebye
//        http.logout(logout -> logout
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
//                .logoutSuccessUrl("/byebye").permitAll()
//        );

        http.csrf().disable();
        return (SecurityFilterChain)http.build();
    }


    // does the same what CustomInMemoryUserDetailsManager class does

//    @Bean
//    public UserDetailsService userDetailsService() {
//        User.UserBuilder builder = User.builder();
//        UserDetails admin = builder.username("admin").password("{noop}hard").roles("ADMIN").build();
//        UserDetails user1 = builder.username("john").password("{noop}weak").roles("USER").build();
//        return new InMemoryUserDetailsManager(admin, user1);
//    }

    // W metodzie userDetailsService() tworzymy dwa obiekty typu UserDetails, które reprezentują odpowiednio
    // admina i użytkownika. Wykorzystujemy do tego celu obiekt UserBuilder, który jest implementacją
    // wzorca budowniczy. Nie tworzymy więc obiektów przez bezpośrednie wywołanie konstruktora,
    // tylko przez wywołanie odpowiedniej sekwencji metod. Metoda username() pozwala ustawić
    // nazwę użytkownika, password() odpowiada za hasło, a roles() za przypisane role,
    // które możemy wykorzystać do autoryzacji.
}
