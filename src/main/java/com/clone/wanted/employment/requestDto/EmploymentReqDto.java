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
    private String address;
    private String deadline;
    private int applicantReward;
    private int recommenderReward;

    // 채용공고 수정시 사용되는 생성자
    public EmploymentReqDto(String employmentTitle, String address, String deadline, int applicantReward, int recommenderReward) {
        this.employmentTitle = employmentTitle;
        this.address = address;
        this.deadline = deadline;
        this.applicantReward = applicantReward;
        this.recommenderReward = recommenderReward;
    }
}
