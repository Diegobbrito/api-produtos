package br.com.gft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.gft.service.UsuarioDetailsService;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UsuarioDetailsService usuarioDetailsService;

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.userDetailsService(usuarioDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/categorias").permitAll()
			.antMatchers("/v2/api-docs").permitAll() 																									
			.antMatchers("/swagger-ui/*").permitAll()
			.antMatchers("/swagger-resources/**").permitAll()
			.anyRequest().authenticated()
			.and()
				.httpBasic()
			.and()
				.csrf().disable();
	}


}
