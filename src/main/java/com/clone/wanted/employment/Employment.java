package com.clone.wanted.employment;


import com.clone.wanted.BaseEntity;
import com.clone.wanted.company.Company;
import com.clone.wanted.employment.requestDto.EmploymentReqDto;
import com.clone.wanted.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
@Entity
public class Employment extends BaseEntity {

    /*Todo 1 develop 브랜치에서  merge 후 Company  FK키 오류 해결되는지 확인하기 */
    /*Todo 2 BaseEntity로 created_at, updated_at, deleted_at 상속받기 */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;
    private String employmentTitle;

    @Column(name = "employment_con")
    private String employmentCon;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private String deadline;
    private int recommenderReward;
    private int applicantReward;


    public Employment(Company company, EmploymentReqDto employmentReqDto) {
        this.company = company;
        this.employmentTitle = employmentReqDto.getEmploymentTitle();
        this.employmentCon = employmentReqDto.getEmploymentCon();
        this.deadline = employmentReqDto.getDeadline();
        this.recommenderReward = employmentReqDto.getRecommenderReward();
        this.applicantReward = employmentReqDto.getApplicantReward();
    }

    public void modifyEmployment(EmploymentReqDto employmentReqDto){
        this.employmentTitle = employmentReqDto.getEmploymentTitle();
        this.employmentCon = employmentReqDto.getEmploymentCon();
        this.deadline = employmentReqDto.getDeadline();
        this.recommenderReward = employmentReqDto.getRecommenderReward();
        this.applicantReward = employmentReqDto.getApplicantReward();
    }
}
