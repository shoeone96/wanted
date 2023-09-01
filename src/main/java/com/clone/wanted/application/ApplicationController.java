package com.clone.wanted.application;

import com.clone.wanted.application.requestDto.EstimateRequestDto;
import com.clone.wanted.application.responseDto.CompanyApplicationResponseDto;
import com.clone.wanted.application.responseDto.UserApplicationResponseDto;
import com.clone.wanted.config.BaseResponse;
import lombok.RequiredArgsConstructor;
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
    public BaseResponse<Void> enroll(@RequestBody String email, @PathVariable Long employmentId) {
        applicationService.enroll(email, employmentId);
        return BaseResponse.success();
    }

    @PatchMapping("api/v1/applications/{employmentId}/estimate")
    public BaseResponse<Void> estimate(@RequestBody String email, @RequestBody EstimateRequestDto requestDto, @PathVariable Long employmentId){
        applicationService.estimate(email, requestDto, employmentId);
        return BaseResponse.success();
    }

    @PatchMapping("api/v1/applications/{employmentId}/cancel")
    public BaseResponse<Void> cancel(@RequestBody String email, @PathVariable Long employmentId){
        applicationService.cancel(email, employmentId);
        return BaseResponse.success();
    }

    @GetMapping("api/v1/applications")
    public BaseResponse<List<UserApplicationResponseDto>> getUserApplicationList(@RequestBody String email){
        return BaseResponse.success(applicationService.getUserApplications(email));
    }

    @GetMapping("api/v1/applications/{employmentId}")
    public BaseResponse<List<CompanyApplicationResponseDto>> getCompanyApplicationList(@RequestBody String email, @PathVariable Long employmentId){
        return BaseResponse.success(applicationService.getCompanyApplications(email, employmentId));
    }
}
