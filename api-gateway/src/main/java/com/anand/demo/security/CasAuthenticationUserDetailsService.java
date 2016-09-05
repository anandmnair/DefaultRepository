package com.anand.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CasAuthenticationUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken authentication) throws UsernameNotFoundException {
		System.out.println("******** (loadUserDetails) Name : " + authentication.getName() + "    Principle : " +authentication.getPrincipal().toString() );
		List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(authentication.getName(), authentication.getName(), authorities);
	}
}
