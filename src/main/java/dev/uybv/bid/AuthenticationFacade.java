package dev.uybv.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import dev.uybv.bid.dao.Member;
import dev.uybv.bid.dao.MemberRepository;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	@Autowired
	private MemberRepository memberRepos;

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public Member getCurrent() {
		Authentication authentication = this.getAuthentication();
		return memberRepos.findByUsername(authentication.getName());
	}
}
