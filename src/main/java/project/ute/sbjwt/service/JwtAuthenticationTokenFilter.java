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
import project.ute.service.UsersService;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter  {
	private final static String TOKEN_HEADER = "Authorization";

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UsersService userService;

	@Override
	protected void doFilterInternal( @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(TOKEN_HEADER);
		System.out.println("----------Token: " + authToken + "------------------");
		if (jwtService.validateTokenLogin(authToken)) {
			String username = jwtService.getUsernameFromToken(authToken);
			Optional<project.ute.model.User> user = userService.loadUserByEmail(username);
//			System.out.println("User: " + user.get().getRole() + " " + user.get().getPassword() + " 1");
			if (user != null) {
				System.out.println("User: " + user.get().getRole() + " " + user.get().getPassword() + " 2");
				System.out.println("User: " + user.get().getAuthorities() + " 2");
				boolean enabled = true;
				boolean accountNonExpired = true;
				boolean credentialsNonExpired = true;
				boolean accountNonLocked = true;
//				user.get().getPassword()
				UserDetails userDetail = new User(username, user.get().getPassword(), enabled, accountNonExpired,
						credentialsNonExpired, accountNonLocked, user.get().getAuthorities());
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,
						null, userDetail.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				System.out.println("============Authentcation: " + authentication + "=================");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}
}