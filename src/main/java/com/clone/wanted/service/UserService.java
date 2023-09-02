package com.clone.wanted.service;

import com.clone.wanted.User.Authority;
import com.clone.wanted.User.UserType;
import com.clone.wanted.repository.AuthorityRepository;
import com.clone.wanted.utils.SecurityUtil;
import com.clone.wanted.User.User;
import com.clone.wanted.repository.UserRepository;
import com.clone.wanted.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;
	private final PasswordEncoder passwordEncoder;


	@Transactional
	public User signup(UserDto userDto) {
		if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
			throw new RuntimeException("이미 가입되어 있는 유저입니다.");
		}

		Authority authority = null;
		// 가입되어 있지 않은 회원이면,
		// 권한 정보 만들고
		if(userDto.getUserType().equals(UserType.GENERAL)){
			authority = Authority.builder()
					.authorityName("GENERAL")
					.build();
		}
		else if(userDto.getUserType().equals(UserType.CORPORATE)) {
			authority = Authority.builder()
					.authorityName("CORPORATE")
					.build();
		}


		// 유저 정보를 만들어서 save
		User user = User.builder()
				.username(userDto.getUsername())
				.password(passwordEncoder.encode(userDto.getPassword()))
				.name(userDto.getName())
				.email(userDto.getEmail())
				.phoneNumber(userDto.getPhoneNumber())
				.birthDate(userDto.getBirthDate())
				.jobStatus(userDto.getJobStatus())
				.jobGroup(userDto.getJobGroup())
				.userType(userDto.getUserType())
				.authorities(Collections.singleton(authority))
				.activated(true)
				.build();

//		AuthorityRepository.save(authority);
		return userRepository.save(user);
	}

	// 유저,권한 정보를 가져오는 메소드
	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthorities(String username) {
		return userRepository.findOneWithAuthoritiesByUsername(username);
	}

	// 현재 securityContext에 저장된 username의 정보만 가져오는 메소드
	@Transactional(readOnly = true)
	public Optional<User> getMyUserWithAuthorities() {
		return SecurityUtil.getCurrentUsername()
				.flatMap(userRepository::findOneWithAuthoritiesByUsername);
	}

	@Transactional(readOnly = false)
	public Optional<User> getUserByEmail(String email){
		return userRepository.findByEmail(email);
	}
}