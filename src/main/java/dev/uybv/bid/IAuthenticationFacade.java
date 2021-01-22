package dev.uybv.bid;

import org.springframework.security.core.Authentication;

import dev.uybv.bid.dao.Member;

public interface IAuthenticationFacade {
	Authentication getAuthentication();

	Member getCurrent();
}
