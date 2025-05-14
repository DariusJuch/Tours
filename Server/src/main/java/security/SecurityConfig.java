package security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll() // Регистрация доступна всем
                        .requestMatchers(HttpMethod.POST, "/api/users/login").permitAll() // Логин доступен всем
                        .requestMatchers(HttpMethod.GET, "/api/excursions/**").permitAll() // Каталог экскурсий доступен всем
                        .requestMatchers(HttpMethod.POST, "/api/bookings/**").hasRole("user") // Бронирования только для user
                        .requestMatchers("/api/admin/**").hasRole("admin") // Админские эндпоинты только для admin
                        .requestMatchers("/", "/error", "/csrf").permitAll() // Статические ресурсы
                        .anyRequest().authenticated() // Все остальные запросы требуют авторизации
                )
                .formLogin(form -> form
                        .loginPage("/login") // Страница логина
                        .defaultSuccessUrl("/api/excursions", true) // Перенаправление после логина
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/api/excursions") // Перенаправление после выхода
                        .permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable) // Отключение CSRF (как в примере)
                .cors(Customizer.withDefaults()); // Включение CORS

        return http.build();
    }
}