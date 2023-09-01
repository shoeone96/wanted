package com.clone.wanted.application;

import com.clone.wanted.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    /**
     * @param email
     * @return BaseResponse
     * TODO: spring security 단 이후 Authentication 으로 변경
     */
    @PostMapping("api/v1/applications/{employmentId}")
    public BaseResponse<Void> enroll(@RequestBody String email, @PathVariable Long employmentId) {
        applicationService.enroll(email, employmentId);
        return BaseResponse.success();
    }
}
