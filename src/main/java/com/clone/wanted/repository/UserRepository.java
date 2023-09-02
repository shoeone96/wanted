package com.clone.wanted.repository;

import com.clone.wanted.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findOneWithAuthoritiesByUsername(String s);
	Optional<User> findOneWithAuthoritiesByEmail(String s);

	Optional<User> findByEmail(String email);
}
