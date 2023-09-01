package com.clone.wanted.employment;

import com.clone.wanted.Company.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmploymentResDto {
    private long employmentId;
    private String employmentTitle;
    private String address;
    private String deadline;
    private int applicantReward;
    private int recommenderReward;
    private long companyId;
    private String companyName;


    public EmploymentResDto(Employment employment,Company company) {
        this.employmentId = employment.getId();
        this.employmentTitle = employment.getEmploymentTitle();
        this.address = employment.getAddress();
        this.deadline = employment.getDeadline();
        this.applicantReward = employment.getApplicantReward();
        this.recommenderReward = employment.getRecommenderReward();
        this.companyId = company.getId();
        this.companyName = company.getCompanyName();
    }
}
