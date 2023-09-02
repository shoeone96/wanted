package com.clone.wanted.user.requestDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

	@NotNull
	@Size(min = 3, max = 50)
	private String email;

	@NotNull
	@Size(min = 3, max = 100)
	private String password;
}