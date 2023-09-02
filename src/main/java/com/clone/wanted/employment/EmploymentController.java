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
    public BaseResponse createEmployment(@RequestBody EmploymentReqDto employmentReqDto) throws Exception {
        employmentService.createEmployment(employmentReqDto);
        // Baseresponse 생성자 만들기
        return new BaseResponse();
    }



    //채용 공고 상세조회
    @GetMapping("/employments/{employmentId}")
    public BaseResponse<EmploymentDetailResDto> retrieveEmployment(@PathVariable("employmentId") long employmentId) throws Exception {
        EmploymentDetailResDto employmentResDto = employmentService.retrieveEmployment(employmentId);


        return new BaseResponse<> (employmentResDto);
    }

    //채용 공고 전체조회
    @GetMapping("/employments")
    public BaseResponse<List<EmploymentAllResDto>> retrieveAllEmployment() throws Exception {
        List<EmploymentAllResDto> employmentResDtoList = employmentService.retrieveAllEmployment();
        return new BaseResponse<>(employmentResDtoList);
    }


}
