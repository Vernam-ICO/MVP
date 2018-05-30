package com.dimiroma.vernam.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtils {
	private final AuthenticationManager authenticationManager;

	@Autowired
	public AuthenticationUtils(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public CustomUserDetails authenticate(final String user, final String pass) {
		final UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user, pass);

		final Authentication auth = authenticationManager.authenticate(authRequest);
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(auth);

		return (CustomUserDetails) auth.getPrincipal();
	}
}
