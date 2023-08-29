package com.clone.wanted.User;

import com.clone.wanted.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	//유저 아이디
	private String username;
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
}
