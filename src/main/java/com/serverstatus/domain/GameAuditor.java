package com.serverstatus.domain;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class GameAuditor implements AuditorAware<User> {
	
	public User getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null || authentication.isAuthenticated()) {
			return null;
		}
		
		return ((User) authentication.getPrincipal());
	}
}
