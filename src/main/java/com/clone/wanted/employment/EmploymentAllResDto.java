package com.clone.wanted.employment;


import com.clone.wanted.Company.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmploymentAllResDto {

    private long employmentId;
    private String employmentTitle;
    private int rewardSum;
    private long companyId;
    private String companyName;
    private String region;


    public EmploymentAllResDto(Employment employment, Company company,int rewardSum) {
        this.employmentId = employment.getId();
        this.employmentTitle = employment.getEmploymentTitle();
        this.rewardSum = rewardSum;
        this.companyId = company.getId();
        this.companyName = company.getCompanyName();
        this.region = company.getRegion();
    }
}
