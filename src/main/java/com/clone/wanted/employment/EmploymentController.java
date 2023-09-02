package com.clone.wanted.employment;


import com.clone.wanted.config.BaseResponse;
import com.clone.wanted.employment.requestDto.EmploymentReqDto;
import com.clone.wanted.employment.responseDto.EmploymentAllResDto;
import com.clone.wanted.employment.responseDto.EmploymentDetailResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class EmploymentController {

    private final EmploymentService employmentService;

    //채용 공고 생성
    @PostMapping("/employments")
    public BaseResponse<Void> createEmployment(@RequestBody EmploymentReqDto employmentReqDto) {
        employmentService.createEmployment(employmentReqDto);
        return BaseResponse.success();
    }

    //채용공고 수정
    @PutMapping("/employments/{employmentId}")
    public BaseResponse<Void> updateEmployment(@PathVariable long employmentId, @RequestBody EmploymentReqDto employmentReqDto ) {
        employmentService.updateEmployment(employmentId,employmentReqDto);
        return BaseResponse.success();
    }

    //채용 공고 상세조회
    @GetMapping("/employments/{employmentId}")
    public BaseResponse<EmploymentDetailResDto> retrieveEmployment(@PathVariable("employmentId") long employmentId) {
        EmploymentDetailResDto employmentResDto = employmentService.retrieveEmployment(employmentId);
        return BaseResponse.success(employmentResDto);
    }

    //채용 공고 전체조회
    @GetMapping("/employments")
    public BaseResponse<List<EmploymentAllResDto>> retrieveAllEmployment() {
        List<EmploymentAllResDto> employmentResDtoList = employmentService.retrieveAllEmployment();
        return BaseResponse.success(employmentResDtoList);
    }

    //채용공고 삭제
    @DeleteMapping("/employments/{employmentId}")
    public BaseResponse<Void> deleteEmployment(@PathVariable long employmentId) {
        employmentService.deleteEmployment(employmentId);
        return BaseResponse.success();
    }


}
