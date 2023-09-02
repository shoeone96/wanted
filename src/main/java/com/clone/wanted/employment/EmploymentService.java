package com.clone.wanted.employment;

import com.clone.wanted.Company.Company;
import com.clone.wanted.Company.CompanyRepository;
import com.clone.wanted.employment.requestDto.EmploymentReqDto;
import com.clone.wanted.employment.responseDto.EmploymentAllResDto;
import com.clone.wanted.employment.responseDto.EmploymentDetailResDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
    public EmploymentDetailResDto retrieveEmployment(@PathVariable long employmentId) {
        Employment employment = employmentRepository.findById(employmentId).get();
        Company company = employment.getCompany();
        //임시 like Num
        int likeNum=5;

        List<String> hashtagName = employmentRepository.findHashtagName(employmentId);
        List<String> skillStack = employmentRepository.findSkillStackName(employmentId);

        EmploymentDetailResDto employmentResDto = new EmploymentDetailResDto(employment,company,likeNum,hashtagName,skillStack);

        return employmentResDto;
    }

    //채용공고 전체 조회
    public List<EmploymentAllResDto> retrieveAllEmployment() {
        List<Employment> employmentList = employmentRepository.findAll();

        //employmentList employmentsAllResDtoList로 변환하기
        ArrayList<EmploymentAllResDto> employmentResDtoList = new ArrayList<>();
        for(int i=0;i<employmentList.size();i++){
            Employment employment = employmentList.get(i);
            Company company = employment.getCompany();
            int applicantReward = employment.getApplicantReward();
            int recommenderReward = employment.getRecommenderReward();
            int rewardSum=applicantReward+recommenderReward;

            EmploymentAllResDto employmentResDto = new EmploymentAllResDto(employment, company,rewardSum);

            employmentResDtoList.add(employmentResDto);

        } //변환 끝

        return employmentResDtoList;
    }



}
