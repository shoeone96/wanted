package com.clone.wanted.user.requestDto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private String password;
	private String name;
	private String email;
	private String phoneNumber;
}