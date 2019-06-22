package com.ufc.br.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ufc.br.security.UserDetailsServiceImplementacao;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImplementacao userDetailImplmentacao;
	
	@Autowired
	SimpleAuthenticationHandler authenticationHandler;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailImplmentacao).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/font/**","/img/**","/js/**","/images/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		
		.antMatchers("/prato/cadastrar").hasRole("GERENTE")
		.antMatchers("/prato/listar").hasRole("GERENTE")
		.antMatchers("/prato/deletar/**").hasRole("GERENTE")
		.antMatchers("/pedido/atual").hasRole("CLIENTE")
		.antMatchers("/").permitAll()
		.antMatchers("/pratos").permitAll()
		.antMatchers("/contato").permitAll()
		.antMatchers("/sobre").permitAll()
		.antMatchers("/gerente/cadastrar").permitAll()
		.antMatchers("/gerente/deletar").permitAll()
		.antMatchers("/cliente/cadastrar").permitAll()
		.antMatchers("/logar").permitAll()
		
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/logar")
        .loginProcessingUrl("/logar")
        .usernameParameter("email")
        .passwordParameter("senha")
        .successHandler(authenticationHandler)
		
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logar");
	}
	
}