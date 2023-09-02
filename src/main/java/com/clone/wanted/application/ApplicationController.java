package com.clone.wanted.application;

import com.clone.wanted.application.requestDto.EstimateRequestDto;
import com.clone.wanted.application.responseDto.CompanyApplicationResponseDto;
import com.clone.wanted.application.responseDto.UserApplicationResponseDto;
import com.clone.wanted.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    /**
     * @param email
     * @return BaseResponse
     * TODO: spring security 단 이후 email 을 Authentication 으로 변경
     */
    @PostMapping("api/v1/applications/{employmentId}")
    public BaseResponse<Void> enroll(Authentication authentication, @PathVariable Long employmentId) {
        applicationService.enroll(authentication.getName(), employmentId);
        return BaseResponse.success();
    }

    @PatchMapping("api/v1/applications/{employmentId}/estimate")
    public BaseResponse<Void> estimate(Authentication authentication, @RequestBody EstimateRequestDto requestDto, @PathVariable Long employmentId){
        applicationService.estimate(authentication.getName(), requestDto, employmentId);
        return BaseResponse.success();
    }

    @PatchMapping("api/v1/applications/{employmentId}/cancel")
    public BaseResponse<Void> cancel(Authentication authentication, @PathVariable Long employmentId){
        applicationService.cancel(authentication.getName(), employmentId);
        return BaseResponse.success();
    }

    @GetMapping("api/v1/applications")
    public BaseResponse<List<UserApplicationResponseDto>> getUserApplicationList(Authentication authentication){
        return BaseResponse.success(applicationService.getUserApplications(authentication.getName()));
    }

    @GetMapping("api/v1/applications/{employmentId}")
    public BaseResponse<List<CompanyApplicationResponseDto>> getCompanyApplicationList(Authentication authentication, @PathVariable Long employmentId){
        return BaseResponse.success(applicationService.getCompanyApplications(authentication.getName(), employmentId));
    }
}
