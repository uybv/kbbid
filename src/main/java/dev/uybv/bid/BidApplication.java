package dev.uybv.bid;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.uybv.bid.dao.Member;
import dev.uybv.bid.dto.MemberService;

@SpringBootApplication
public class BidApplication {

	public static void main(String[] args) {
		SpringApplication.run(BidApplication.class, args);
	}

	@Autowired
	private MemberService memberSrv;

	@PostConstruct
	public void initUsers() {
		try {
			memberSrv.create(new Member("uybv", "123456", "uybv.cntt@gmail.com", "", ""));
		} catch (Exception ex) {
		}
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
