package com.clone.wanted.employment.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmploymentReqDto {

    private long companyId;
    private String employmentTitle;
    private String employmentCon;
    private String deadline;
    private int applicantReward;
    private int recommenderReward;

}
