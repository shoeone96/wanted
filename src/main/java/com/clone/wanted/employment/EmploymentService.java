package com.clone.wanted.employment;

import com.clone.wanted.Company.Company;
import com.clone.wanted.Company.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmploymentService {

    private final EmploymentRepository employmentRepository;
    private final CompanyRepository companyRepository;

    //채용공고 생성
    public void createEmployment (EmploymentReqDto employmentReqDto){
        long companyId = employmentReqDto.getCompanyId();
        //람다식으로 예외처리 해보기 + 옵셔널 사용방법 찾아보기
        Company company = companyRepository.findById(companyId).get();

        Employment employment = new Employment(company, employmentReqDto);

        employmentRepository.save(employment);
    }
    //채용공고 상세 조회
    public EmploymentResDto retrieveEmployment(@PathVariable long employmentId) {
        Employment employment = employmentRepository.findById(employmentId).get();
        Company company = employment.getCompany();

        EmploymentResDto employmentResDto = new EmploymentResDto(employment, company);

        return employmentResDto;
    }

    //채용공고 전체 조회
    public List<EmploymentResDto> retrieveAllEmployment() {
        List<Employment> employmentList = employmentRepository.findAll();

        //employmentList employmentsResDtoList로 변환하기
        ArrayList<EmploymentResDto> employmentResDtoList = new ArrayList<>();

        for(int i=0;i<employmentList.size();i++){
            Employment employment = employmentList.get(i);
            Company company = employment.getCompany();
            employmentResDtoList.add(new EmploymentResDto(employment,company));
        } //변환 끝

        return employmentResDtoList;
    }



}
