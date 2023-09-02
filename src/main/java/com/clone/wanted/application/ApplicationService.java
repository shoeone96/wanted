package com.clone.wanted.application;

import com.clone.wanted.company.Company;
import com.clone.wanted.company.CompanyRepository;
import com.clone.wanted.user.User;
import com.clone.wanted.user.UserRepository;
import com.clone.wanted.user.UserType;
import com.clone.wanted.application.requestDto.EstimateRequestDto;
import com.clone.wanted.application.responseDto.CompanyApplicationResponseDto;
import com.clone.wanted.application.responseDto.UserApplicationResponseDto;
import com.clone.wanted.config.BaseException;
import com.clone.wanted.config.BaseResponseStatus;
import com.clone.wanted.employment.Employment;
import com.clone.wanted.employment.EmploymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {

    private final UserRepository userRepository;
    private final EmploymentRepository employmentRepository;
    private final ApplicationRepository applicationRepository;
    private final CompanyRepository companyRepository;
    public void enroll(String email, Long employmentId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
        Employment employment = employmentRepository.findById(employmentId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.EMPLOYMENT_NOT_FOUND));
        applicationRepository.findByUserAndEmployment(user, employment)
                .ifPresent((application -> {
                    if(application.getApplicationStatus() == ApplicationStatus.CANCEL) {
                        application.updateStatus(ApplicationStatus.ONGOING);
                    } else throw new BaseException(BaseResponseStatus.APPLICATION_ALREADY_EXIST);
                }));
        applicationRepository.save(Application.newEnrollment(user, employment));
    }

    public void estimate(String email, EstimateRequestDto requestDto, Long employmentId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
        if(user.getUserType() == UserType.CORPORATE_USER) throw new BaseException(BaseResponseStatus.REQUEST_NOT_ALLOWED);
        Employment employment = employmentRepository.findById(employmentId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.EMPLOYMENT_NOT_FOUND));
        Application application = applicationRepository.findByUserAndEmployment(user, employment)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.EMPLOYMENT_NOT_FOUND));
        application.updateStatus(ApplicationStatus.returnStatus(requestDto.getApplicationStatus()));
    }

    public void cancel(String email, Long employmentId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
        Employment employment = employmentRepository.findById(employmentId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.EMPLOYMENT_NOT_FOUND));
        Application application = applicationRepository.findByUserAndEmployment(user, employment)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.EMPLOYMENT_NOT_FOUND));
        application.updateStatus(ApplicationStatus.CANCEL);
    }

    public List<UserApplicationResponseDto> getUserApplications(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
        return applicationRepository.findAllByUser(user)
                .stream()
                .map(UserApplicationResponseDto::of)
                .toList();
    }

    public List<CompanyApplicationResponseDto> getCompanyApplications(String email, Long employmentId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
        if(user.getUserType() == UserType.CORPORATE_USER) throw new BaseException(BaseResponseStatus.REQUEST_NOT_ALLOWED);
        Company company = companyRepository.findByUser(user)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.COMPANY_NOT_FOUND));
        if(company.getUser() != user) throw new BaseException(BaseResponseStatus.REQUEST_NOT_ALLOWED);
        Employment employment = employmentRepository.findById(employmentId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.EMPLOYMENT_NOT_FOUND));
        return applicationRepository.findAllByEmployment(employment)
                .stream()
                .map(CompanyApplicationResponseDto::of)
                .toList();
    }
}
