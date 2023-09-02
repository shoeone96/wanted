package com.clone.wanted.user;

import com.clone.wanted.BaseEntity;
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
	@Column(unique = true)
	private String username;
	@JsonIgnore
	private String password;
	//실제 이름
	@Column(length = 20)
	private String name;
	@Column(length = 100, unique = true)
	private String email;
	@Column( length = 15)
	private String phoneNumber;
	private String birthDate;
	@Column(length = 20)
	private String jobStatus;
	@Column(length = 30)
	private String jobGroup;
	@Column(length = 10)
	private UserType userType;

	@JsonIgnore
	@Column(name = "activated")
	private boolean activated;

}
