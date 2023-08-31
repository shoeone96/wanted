package com.clone.wanted.User;

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
@Setter
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	//유저 아이디
	@Column(unique = true)
	private String username;
	@JsonIgnore
	private String password;
	//실제 이름
	@Column(length = 20)
	private String name;
	@Column(length = 100)
	private String email;
	@Column( length = 15)
	private String phoneNumber;
	private LocalDate birthDate;
	@Column(length = 20)
	private String jobStatus;
	@Column(length = 30)
	private String jobGroup;
	@Column(length = 10)
	private UserType userType;

	@JsonIgnore
	@Column(name = "activated")
	private boolean activated;

	@ManyToMany
	@JoinTable(
			name = "user_authority",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
	private Set<Authority> authorities;

	public User(String subject, String s, Collection<? extends GrantedAuthority> authorities) {
		super();
	}
}
