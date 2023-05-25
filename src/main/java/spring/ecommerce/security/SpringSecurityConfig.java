package spring.ecommerce.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@AllArgsConstructor
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;


    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // Obrigatório declarar o passwordEncoder, para não dar erro na inicialização
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Cadeia de Segurança
    // https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html
    // Obs: Não precisa colocar prefixo "ROLE_" no hasRole
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> {authorize
                        .requestMatchers(RegexRequestMatcher.regexMatcher("^/users/pagination\\?_page=\\[0-9]+&_limit=\\[0-9]+$")).hasRole("ADMIN")
                        .anyRequest().permitAll();
                });
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
