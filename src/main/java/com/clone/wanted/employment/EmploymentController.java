package com.clone.wanted.employment;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class EmploymentController {

    private final EmploymentService employmentService;

    //채용 공고 생성
    @PostMapping("/employments")
    public ResponseEntity createEmployment(@RequestBody EmploymentReqDto employmentReqDto) {
        employmentService.createEmployment(employmentReqDto);
        return ResponseEntity.ok(null);
    }

    //채용 공고 상세조회
    /**Todo 좋아요 수 컬럼으로 추가해서 반환하기*/
    @GetMapping("/employments/{employmentId}")
    public ResponseEntity<EmploymentResDto> retrieveEmployment(@PathVariable long employmentId) {
        EmploymentResDto employmentResDto = employmentService.retrieveEmployment(employmentId);
        return ResponseEntity.ok(employmentResDto);
    }

    //채용 공고 전체조회
    /**Todo 좋아요 수 컬럼으로 추가해서 반환하기*/
    @GetMapping("/employments")
    public ResponseEntity<List<EmploymentResDto>> retrieveAllEmployment() {
        List<EmploymentResDto> employmentResDtoList = employmentService.retrieveAllEmployment();
        return ResponseEntity.ok(employmentResDtoList);
    }


}
