package project.ute.sbjwt.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.ute.service.CustomerService;
import project.ute.service.UsersService;
import project.ute.service.impl.LoginServiceImpl;
import project.ute.util.ConstantUtils;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtService;

	@Autowired
	private UsersService userService;
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private LoginServiceImpl loginServiceImpl;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		String action = ConstantUtils.GEN_TOKEN;
//		System.out.println("--------------Check------------------: " + loginServiceImpl.getRole());
		int role = loginServiceImpl.getRole();
		Optional<project.ute.model.User> user = null;
		Optional<project.ute.model.Customer> customer = null;
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(ConstantUtils.TOKEN_HEADER);
		if (jwtService.validateTokenLogin(authToken, action)) {
			String username = jwtService.getUsernameFromToken(authToken, action);
			user = userService.loadUserByEmail(username);
			customer = customerService.checkCustomerAccount(username);
			
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			
//			if(role == -1 ) {
//				if (user != null) {
//					System.out.println("-1 User ! = null");
//					handleGrantPermissionForUser(username, user, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, httpRequest);
//					
//				} else if (customer != null) {
//					System.out.println("-1 Customer ! = null");
//					handleGrantPermissionForCustomer(username, customer, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, httpRequest);
//				}
//			} else
			if(role == 0){
				if (user != null) {
//					System.out.println("User ! = null");
					handleGrantPermissionForUser(username, user, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, httpRequest);
					
				} 
			} else if (role == 1) {
				if(customer != null) {
//					System.out.println("Customer ! = null");
					handleGrantPermissionForCustomer(username, customer, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, httpRequest);
				}
			}
		}
		filterChain.doFilter(request, response);
	}

	public void handleGrantPermissionForUser(String username, Optional<project.ute.model.User> user, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, HttpServletRequest httpRequest) {
		UserDetails userDetail = new User(username, user.get().getPassword(), enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, user.get().getAuthorities());
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	public void handleGrantPermissionForCustomer(String username, Optional<project.ute.model.Customer> customer, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, HttpServletRequest httpRequest) {
		UserDetails userDetail = new User(username, customer.get().getPassword(), enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, customer.get().getAuthorities());
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}