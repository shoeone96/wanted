package com.clone.wanted.user;

import com.clone.wanted.config.BaseResponse;
import com.clone.wanted.config.BaseResponseStatus;
import com.clone.wanted.user.requestDto.LoginRequestDto;
import com.clone.wanted.user.requestDto.SigninRequestDto;
import com.clone.wanted.user.requestDto.EmailRequestDto;
import com.clone.wanted.user.requestDto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
	private final UserService userService;

	//회원 가입기능
	@PostMapping("/users/join")
	public BaseResponse<Void> signup(@RequestBody SigninRequestDto signinRequestDto) {
			userService.signup(signinRequestDto);
			return BaseResponse.success();
	}

	// 이메일 체크 기능
	@PostMapping("/users/email")
	public BaseResponse<String> checkEmail (@RequestBody EmailRequestDto emailRequestDto){
		return BaseResponse.success(userService.getUserByEmail(emailRequestDto.getEmail()));
	}

	// 로그인 기능
	@PostMapping("/users/login")
	public BaseResponse<String> authorize(@RequestBody LoginRequestDto loginRequestDto) {

		return BaseResponse.success(userService.login(loginRequestDto));

	}

	// 회원 탈퇴 기능
	@DeleteMapping("/users")
	public BaseResponse<Void> userDelete(Authentication authentication){
		userService.userDelete(authentication.getName());
		return BaseResponse.success();
	}

	@PutMapping("/users")
	public BaseResponse<Void> userUpdate(Authentication authentication, @RequestBody UserDto userDto){
		userService.userUpdate(authentication.getName(), userDto);
		return BaseResponse.success();
	}
}