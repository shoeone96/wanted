package com.clone.wanted.application;

import com.clone.wanted.User.User;
import com.clone.wanted.User.UserRepository;
import com.clone.wanted.config.BaseException;
import com.clone.wanted.config.BaseResponseStatus;
import com.clone.wanted.employment.Employment;
import com.clone.wanted.employment.EmploymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {

    private final UserRepository userRepository;
    private final EmploymentRepository employmentRepository;
    private final ApplicationRepository applicationRepository;
    public void enroll(String email, Long employmentId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
        Employment employment = employmentRepository.findById(employmentId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.Employment_NOT_FOUND));
        applicationRepository.findByUserAndEmployment(user, employment)
                .ifPresent((application -> {
                    throw new BaseException(BaseResponseStatus.APPLICATION_ALREADY_EXIST);
                }));
        applicationRepository.save(Application.newEnrollment(user, employment));
    }
}
