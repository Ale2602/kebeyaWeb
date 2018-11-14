package pe.edu.upc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
				"select * from (select nombre as username, clave as password, "
				+ "estado as enabled from usuario) as users where username = ? ")
				.authoritiesByUsernameQuery(
						"select * from (select nombre as username, tipo as AUTHORITY from usuario)"
						+ " as authorities where username = ? ");
	}

	public void configure(HttpSecurity http) throws Exception {
		try {
			http.authorizeRequests()
					.antMatchers("/category/**").access("hasRole('ROLE_DELIEVERY') or hasRole('ROLE_ADMIN')")
					.antMatchers("/client/**").access("hasRole('ROLE_VENDEDOR')  or hasRole('ROLE_ADMIN')")
					.antMatchers("/product/**").access("hasRole('ROLE_CLIENT')  or hasRole('ROLE_ADMIN')")
					
					.antMatchers("/welcome").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DELIEVERY') or hasRole('ROLE_VENDEDOR') or hasRole('ROLE_CLIENT') ").and()

					.formLogin().loginPage("/login").loginProcessingUrl("/j_spring_security_check")
					.defaultSuccessUrl("/welcome").failureUrl("/login?error").usernameParameter("usuario")
					.passwordParameter("password").and().logout().logoutSuccessUrl("/login?logout")
					.logoutUrl("/j_spring_security_logout").and().exceptionHandling().accessDeniedPage("/403").and()
					.csrf().disable();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}