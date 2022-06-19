package go.travel.global.config.auth;

import go.travel.domain.code.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;

//Spring Security 설정들을 활성화 시켜준다.
//WebSecurityConfigurerAdapter 2.7버전부터 막힘.
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                //URL별 권한 관리를 설정하는 옵션의 시작점이다.
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(UserRole.USER.name())
                //위에서 설정된값 이외에 나머지 요청들 설정 현재 설정은 인증된 사용자만 가능하게 변경했다.
                .anyRequest().authenticated()
                .and()
                .logout().deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/").permitAll()
                .and()
                    //권한이 없다면 ? -> 401내려준다.
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .oauth2Login()
                    .userInfoEndpoint().userService(customOAuth2UserService)
                    .and()
                    .successHandler((req, resp, auth) -> resp.sendRedirect("/"));
        return http.build();
    }

}
