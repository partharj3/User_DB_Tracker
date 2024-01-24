package com.jsp.ums.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.ums.entity.User;

public class CustomUserDetail implements UserDetails{
	
	private User user;
	
	public CustomUserDetail(User user) {
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return user.getUserpassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { // not expired
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // not locked
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {  // is user present
		return true;
	}
}
