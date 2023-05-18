package com.loginSignup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 *WebSecurityConfigurerAdapter 상속받는 클래스에 @EnableWebSecurity 어노테이션을
 *선언하면 SpringSecurityFilterChain 이 자동으로 포함됩니다.
 *WebSecurityConfigurerAdapter를 상송받아서 메소드 오버라이딩을 통해 보안 설정을 커스터마이징할 수 있습니다.
 *http 요청에 대한 보안 설정합니다. 페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드 등에 대한 설정을 작성합니다.
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig   {

   
    
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*");
			}
		};
	}
	
	
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    	//http.csrf().disable();
		http.headers().frameOptions().sameOrigin();
		
		
            http.formLogin()
                    .loginPage("/members/login")  //로그인 페이지 URL 을 설정
                    .defaultSuccessUrl("/")    // 로그인 성공시 이동할 URL 을 설정
                    .usernameParameter("email") //로그인시 사용할 파라미터 이름으로 email 을 지정
                    .failureUrl("/members/login/error")  // 로그인 실패시 이동할 URL 을 설정
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))   //로그아웃 URL 을 설정
                    .logoutSuccessUrl("/"); //로그아웃 성공시 이동할 URL 을 설정



            http.authorizeRequests()   //시큐리티 처리에 HttpServletRequest 를 이용한다는 것을 의미.
                    .antMatchers("/", "/members/**","/board/**",  "/css/**", "/js/**", "/img/**", "/images/**").permitAll()  //permitAll() 을 통해 모든 사용자가 인증(로그인 없이 해당 경로 접근 )
                    .antMatchers("/admin/**").hasRole("ADMIN")  // admin 으로 시작하는 경로는 해당 계정이 ADMIN Role 일 경우에만 접근이 가능
                    .anyRequest().authenticated();  //위설정한 경로를 제외한 나머지 경로들은 모든 인증을 요구하도록 설정


            http.exceptionHandling()
                    .authenticationEntryPoint(new CustomAuthenticationEntryPoint());  //인증되지 않은 사용자가 리소스에 접근하였을 때 수행되는 핸들러를 등록

            return http.getOrBuild();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




}
