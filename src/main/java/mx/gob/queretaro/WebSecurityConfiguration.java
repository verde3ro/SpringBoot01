package mx.gob.queretaro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	// Autorización -- Url´s o Recursos - Pagina Login
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/", "/index*", "/css/**", "/js/**", "/img/**").permitAll() //Sin Autentificación
		.antMatchers("/home*").hasAnyRole("ADMIN", "USER") //Con Autentificación
		.antMatchers("/ciudad/**").hasAnyRole("ADMIN") //Con Autentificación solo rol Admin
		.and()
		.formLogin() // Login
		.loginProcessingUrl("/login") // j_security_check
		.loginPage("/index").usernameParameter("txtUsuario").passwordParameter("txtPassword") //j_username j_password
		.defaultSuccessUrl("/home")
		.failureUrl("/index?error=true")
		.permitAll()
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // j_logout
		.logoutSuccessUrl("/index?logout=true")
		.invalidateHttpSession(true) // borrar sesiones
		.permitAll()
		.and()
		.csrf()
		.disable();
	}

	// Autentificación - Usuarios
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.passwordEncoder(passwordEncoder())
		.withUser("user").password(passwordEncoder().encode("123456")).roles("USER")
		.and()
		.withUser("admin").password(passwordEncoder().encode("123456")).roles("USER", "ADMIN")
		.and()
		.withUser("verde3ro").password(passwordEncoder().encode("123456")).roles("USER", "ADMIN");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
