package dev.uybv.bid.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

	public Member() {
	}

	public Member(String username, String password, String email, String facebook, String phone) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.facebook = facebook;
		this.phone = phone;
	}

	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Integer id;
	private @Column(nullable = false, length = 128, unique = true) String username;
	private @Column(nullable = false) String password;
	private @Column(nullable = false, length = 128) String email;
	private String facebook;
	private String phone;
	private @Column(updatable = false) Long createdAt;
	private Long lastUpdatedAt;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Long getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(Long lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}

}
