package com.clone.wanted.user;

import com.clone.wanted.BaseEntity;
import com.clone.wanted.employment.requestDto.EmploymentReqDto;
import com.clone.wanted.user.requestDto.SigninRequestDto;
import com.clone.wanted.user.requestDto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	//유저 아이디
	private String password;
	//실제 이름
	@Column(length = 20)
	private String name;
	@Column(length = 100, unique = true)
	private String email;
	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private UserType userType;
	private String phoneNumber;

	@JsonIgnore
	@Column(name = "activated")
	private boolean activated;

	public void updateUser(UserDto userReqDto){
		this.password = userReqDto.getPassword();
		this.name = userReqDto.getName();
		this.email = userReqDto.getEmail();
		this.phoneNumber = userReqDto.getPhoneNumber();
	}
}
