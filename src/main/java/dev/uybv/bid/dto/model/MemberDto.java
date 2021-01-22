package dev.uybv.bid.dto.model;

public class MemberDto {
	
	public MemberDto() {}
	
	public MemberDto(String username, String email, String facebook, String phone) {
		super();
		this.username = username;
		this.email = email;
		this.facebook = facebook;
		this.phone = phone;
	}

	private Integer id;
	private String username;
	private String email;
	private String facebook;
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
