package project.ute.sbjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.ute.respository.CustomerRepository;
import project.ute.respository.UserRepository;
import project.ute.service.LoginService;
import project.ute.service.impl.LoginServiceImpl;

@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@ComponentScan("com.baeldung.security")
public class ApplicationConfig {
	@Autowired
	private  UserRepository repository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private LoginServiceImpl loginServiceImpl;
	
	@Bean
	public UserDetailsService userDetailsService() {
		int role = loginServiceImpl.getRole();
		
		if(role == 0) {
			return username -> repository.getByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		} 
		return email -> customerRepository.checkCustomerAccount(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}