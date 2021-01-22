package dev.uybv.bid.dto;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.uybv.bid.dao.Member;
import dev.uybv.bid.dao.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepos;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepos.findByUsername(username);
		return new User(member.getUsername(), member.getPassword(), new ArrayList<>());
	}
}
