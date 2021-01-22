package dev.uybv.bid.dto;

import java.time.Instant;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.uybv.bid.dao.Member;
import dev.uybv.bid.dto.core.BaseService;
import dev.uybv.bid.dto.model.MemberDto;

@Service
public class MemberService extends BaseService<Member, Integer, MemberDto> implements IMemberService {

	private final PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public MemberService(ModelMapper modelMapper, PasswordEncoder bCryptPasswordEncoder) {
		super(modelMapper);
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		/*
		 * this.modelMapper.addMappings(new PropertyMap<MemberDto, Member>() {
		 * 
		 * @Override protected void configure() { skip(destination.getCreatedAt());
		 * skip(destination.getLastUpdatedAt()); } });
		 */
	}

	@Override
	public void beginCreate(Member entity) {
		entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		entity.setCreatedAt(Instant.now().getEpochSecond());
	}

	@Override
	public void beginUpdate(Integer id, Member entity) {
		entity.setId(id);
		entity.setLastUpdatedAt(Instant.now().getEpochSecond());
	}
}
