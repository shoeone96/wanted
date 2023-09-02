package com.clone.wanted.controller;

import com.clone.wanted.user.User;
import com.clone.wanted.config.BaseResponse;
import com.clone.wanted.config.BaseResponseStatus;
import com.clone.wanted.service.UserService;
import com.clone.wanted.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
	private final UserService userService;

	// 회원가입
//	@PostMapping("/users/join")
//	public ResponseEntity<User> signup(
//			@Valid @RequestBody UserDto userDto
//	) {
//		return ResponseEntity.ok(userService.signup(userDto));
//	}

	//회원 가입기능
	@PostMapping("/users/join")
	public ResponseEntity<BaseResponse<Void >> signup(
			@Valid @RequestBody UserDto userDto
	) {
		try {
			userService.signup(userDto);

			return ResponseEntity.ok().body(new BaseResponse<>(BaseResponseStatus.USER_SIGN_SUCCESS));
		}
		catch (Exception e) {
			return ResponseEntity.ok().body(new BaseResponse<>(BaseResponseStatus.USER_SIGN_ERROR));
		}
	}

	// 이메일 체크 기능
	@PostMapping("/users/email")
	public ResponseEntity<BaseResponse<Void>> checkEmail (@RequestBody EmailRequest emailRequest){
		try {
			String email = emailRequest.getEmail();

			Optional<User> user = userService.getUserByEmail(email);

			if(user.isEmpty()){
				throw new Exception("이미 존재하는 이메일입니다");
			}

			return ResponseEntity.ok().body(new BaseResponse<>(BaseResponseStatus.EMAIL_FOUND));
		} catch (Exception e) {
			return ResponseEntity.ok().body(new BaseResponse<>(BaseResponseStatus.EMAIL_NOT_FOUND));
		}
	}

	public static class EmailRequest {
		private String email;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
//	@GetMapping("/user")
//	@PreAuthorize("hasAnyRole('GENERAL','CORPORATE')")
//	public ResponseEntity<User> getMyUserInfo() {
//		return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
//	}
//
//	// 특정 회원 조회
//	@GetMapping("/user/{username}")
//	@PreAuthorize("hasAnyRole('GENERAL','CORPORATE')")
//	public ResponseEntity<User> getUserInfo(@PathVariable String username) {
//		return ResponseEntity.ok(userService.getUserWithAuthorities(username).get());
//	}
}