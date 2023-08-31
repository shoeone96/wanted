package com.clone.wanted.employment;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
