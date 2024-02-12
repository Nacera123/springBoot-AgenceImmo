package com.agenceImmo.security;

import com.agenceImmo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigurer {


    @Autowired
    private LoginSucessHandler loginSucessHandler;

    /**
     * securité le service qui permet de récupérer l'utilisateur à partir de son email
     * @return le service
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserService();
    }



    /**
     * Déterminer le systeme de cryptage
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * cette méthode elle va appeler les deux premiereres declaration
     * 1- récupérer le user
     * 2- type de cryptage
     * @return
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }


    /**
     * System de filtrage
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((auth) -> auth
                    .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                    .requestMatchers("/", "/assets/**", "/register").permitAll()
                    .anyRequest().authenticated()


            )
                //.formLogin(Customizer.withDefaults())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .successHandler(loginSucessHandler) // lorsque l'utilisateur est connecté elle va rediriger vers cette page
                )

                .logout(logout ->logout.permitAll())
                    .build();
    }
}
