package dev.uybv.bid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.uybv.bid.JwtUtil;
import dev.uybv.bid.dto.model.AuthModel;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping
	public String login(@RequestBody AuthModel authModel) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authModel.getUsername(), authModel.getPassword()));
		} catch (Exception ex) {
			throw new Exception("Inavalid username/password");
		}
		return jwtUtil.generateToken(authModel.getUsername());
	}
}
