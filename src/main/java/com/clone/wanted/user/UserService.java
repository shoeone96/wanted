package com.clone.wanted.user;

import com.clone.wanted.config.BaseException;
import com.clone.wanted.config.BaseResponseStatus;
import com.clone.wanted.user.requestDto.LoginRequestDto;
import com.clone.wanted.security.TokenProvider;
import com.clone.wanted.utils.SecurityUtil;
import com.clone.wanted.user.requestDto.SigninRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	@Value("${jwt.secret-key}")
	private String secretKey;
	@Value("${jwt.token.expired-time-ms}")
	private Long expiredTimeMs;

	@Transactional
	public User signup(SigninRequestDto signinRequestDto) {
		userRepository.findByUsername(signinRequestDto.getUsername())
				.ifPresent((value) -> {
					throw new BaseException(BaseResponseStatus.USER_NAME_DUPLICATED);
				});

		Authority authority = null;
		// 가입되어 있지 않은 회원이면,
		// 권한 정보 만들고
		if(signinRequestDto.getUserType().equals(UserType.GENERAL)){
			authority = Authority.builder()
					.authorityName("GENERAL")
					.build();
		}
		else if(signinRequestDto.getUserType().equals(UserType.CORPORATE)) {
			authority = Authority.builder()
					.authorityName("CORPORATE")
					.build();
		}


		// 유저 정보를 만들어서 save
		User user = User.builder()
				.username(signinRequestDto.getUsername())
				.password(passwordEncoder.encode(signinRequestDto.getPassword()))
				.name(signinRequestDto.getName())
				.email(signinRequestDto.getEmail())
				.phoneNumber(signinRequestDto.getPhoneNumber())
				.birthDate(signinRequestDto.getBirthDate())
				.jobStatus(signinRequestDto.getJobStatus())
				.jobGroup(signinRequestDto.getJobGroup())
				.userType(signinRequestDto.getUserType())
				.activated(true)
				.build();

//		AuthorityRepository.save(authority);
		return userRepository.save(user);
	}

	// 유저,권한 정보를 가져오는 메소드

	public Optional<User> getUserWithAuthorities(String username) {
		return userRepository.findByUsername(username);
	}

	// 현재 securityContext에 저장된 username의 정보만 가져오는 메소드

	public Optional<User> getMyUserWithAuthorities() {
		return SecurityUtil.getCurrentUsername()
				.flatMap(userRepository::findByUsername);
	}

	public String getUserByEmail(String email){
		userRepository.findByEmail(email)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.EMAIL_CHECK_FAIL));
		return "이메일 확인에 성공했습니다.";
	}

	public String login(LoginRequestDto loginRequestDto) {
		User user = userRepository.findByEmail(loginRequestDto.getEmail())
				.orElseThrow(() -> new BaseException(BaseResponseStatus.EMAIL_CHECK_FAIL));
		if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
			throw new BaseException(BaseResponseStatus.INVALID_PASSWORD);
		}
		return  TokenProvider.generateToken(user.getUsername(), secretKey, expiredTimeMs);

	}

	public UserAuth loadUserByUserName(String username) {
		return userRepository.findByUsername(username).map(UserAuth::fromUser).orElseThrow(() ->
				new BaseException(BaseResponseStatus.USER_NOT_FOUND));
	}
}