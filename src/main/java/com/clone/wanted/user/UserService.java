package com.clone.wanted.user;

import com.clone.wanted.application.Application;
import com.clone.wanted.application.ApplicationStatus;
import com.clone.wanted.config.BaseException;
import com.clone.wanted.config.BaseResponseStatus;
import com.clone.wanted.employment.Employment;
import com.clone.wanted.user.requestDto.LoginRequestDto;
import com.clone.wanted.security.TokenProvider;
import com.clone.wanted.user.requestDto.SigninRequestDto;
import com.clone.wanted.user.requestDto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder; // encoder 추가
	private final TokenProvider tokenProvider;
	@Value("${jwt.secret-key}")
	private String secretKey;
	@Value("${jwt.token.expired-time-ms}")
	private Long expiredTimeMs;

	@Transactional
	public void signup(SigninRequestDto signinRequestDto) {
		userRepository.findByEmail(signinRequestDto.getEmail())
				.ifPresent((value) -> {
					throw new BaseException(BaseResponseStatus.EMAIL_ALREADY_SIGNED);
				});



		// 유저 정보를 만들어서 save
		User user = User.builder()
				.password(encoder.encode(signinRequestDto.getPassword()))
				.name(signinRequestDto.getName())
				.email(signinRequestDto.getEmail())
				.userType(UserType.returnStatus(signinRequestDto.getUserType()))
				.phoneNumber(signinRequestDto.getPhoneNumber())
				.activated(true)
				.build();

		userRepository.save(user);
	}

	// 유저,권한 정보를 가져오는 메소드
	public String getUserByEmail(String email){
		userRepository.findByEmail(email)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.EMAIL_CHECK_FAIL));
		return "이메일 확인에 성공했습니다.";
	}

	public String login(LoginRequestDto loginRequestDto) {
		User user = userRepository.findByEmail(loginRequestDto.getEmail())
				.orElseThrow(() -> new BaseException(BaseResponseStatus.EMAIL_CHECK_FAIL));
		if (!encoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
			throw new BaseException(BaseResponseStatus.INVALID_PASSWORD);
		}
		return  TokenProvider.generateToken(user.getEmail(), secretKey, expiredTimeMs);
	}

	public UserAuth loadUserByUserName(String email) {
		return userRepository.findByEmail(email).map(UserAuth::fromUser).orElseThrow(() ->
				new BaseException(BaseResponseStatus.USER_NOT_FOUND));
	}

	public void userDelete(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));

		userRepository.delete(user);
	}

	public void userUpdate(String email, UserDto userDto) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
		userDto.setPassword(encoder.encode(userDto.getPassword()));
		user.updateUser(userDto);
		userRepository.save(user);
	}
}