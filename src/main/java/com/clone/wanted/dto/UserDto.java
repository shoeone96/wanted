package com.clone.wanted.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.clone.wanted.user.UserType;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String username;
	private String password;
	private String name;
	private String email;
	private String phoneNumber;
	private LocalDate birthDate;
	private String jobStatus;
	private String jobGroup;
	private UserType userType;

//	@NotNull
//	@Size(min = 3, max = 50)
//	private String username;
//
//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//	@NotNull
//	@Size(min = 3, max = 100)
//	private String password;

//	@NotNull
//	private String name;
//
//	@NotNull
//	private String email;
//
//	@NotNull
//	private String phoneNumber;
//
//	@NotNull
//	private LocalDate birthDate;
//
//	@Null
//	private String jobStatus;
//
//	@Null
//	private String jobGroup;
//
//	@NotNull
//	private UserType userType;
}