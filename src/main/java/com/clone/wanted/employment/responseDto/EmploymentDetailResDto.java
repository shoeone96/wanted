package com.clone.wanted.employment.responseDto;

import com.clone.wanted.Company.Company;
import com.clone.wanted.employment.Employment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmploymentDetailResDto {
    private long employmentId;
    private String employmentTitle;
    private String address;
    private String deadline;
    private int applicantReward;
    private int recommenderReward;
    private long companyId;
    private String companyName;
    private int likeNum;
    private List<String> hashtagName;

    //상세 조회 생성자
    public EmploymentDetailResDto(Employment employment, Company company, int likeNum, List<String> hashtagName) {
        this.employmentId = employment.getId();
        this.employmentTitle = employment.getEmploymentTitle();
        this.address = employment.getAddress();
        this.deadline = employment.getDeadline();
        this.applicantReward = employment.getApplicantReward();
        this.recommenderReward = employment.getRecommenderReward();
        this.companyId = company.getId();
        this.companyName = company.getCompanyName();
        this.likeNum = likeNum;
        this.hashtagName = hashtagName;
    }


}
