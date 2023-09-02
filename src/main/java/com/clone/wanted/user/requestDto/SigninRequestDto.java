package com.clone.wanted.user.requestDto;

import lombok.*;
import com.clone.wanted.user.UserType;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SigninRequestDto {

	private String password;
	private String name;
	private String email;
	private UserType userType;
	private String phoneNumber;

}