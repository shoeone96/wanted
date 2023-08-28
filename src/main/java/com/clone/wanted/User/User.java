package com.clone.wanted.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	//유저 아이디
	@Column(name = "userName", length = 255)
	private String username;

	@Column(name = "password", length = 255)
	private String password;

	//실제 이름
	@Column(name = "name", length = 20)
	private String name;

	@Column(name = "email", length = 100)
	private String email;

	@Column(name = "phoneNumber", length = 15)
	private String phoneNumber;

	@Column(name = "birthDate")
	private LocalDate birthDate;

	@Column(name = "jobStatus", length = 20)
	private String jobStatus;

	@CreatedDate
	@Column(name = "createdAt", updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updatedAt")
	private LocalDateTime updatedAt;

	@Column(name = "deletedAt")
	private LocalDateTime deletedAt;

	@Column(name = "jobGroup", length = 30)
	private String jobGroup;

	@Column(name = "userType", length = 10)
	private UserType userType;
}
