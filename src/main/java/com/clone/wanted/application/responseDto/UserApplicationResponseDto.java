package com.clone.wanted.application.responseDto;

import com.clone.wanted.application.Application;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserApplicationResponseDto {
    private Long employmentId;
    private String companyName;
    private String position;
    private LocalDateTime registerDate;
    private String applicationStatus;

    public static UserApplicationResponseDto of(Application application){
        return new UserApplicationResponseDto(
                application.getEmployment().getId(),
                application.getEmployment().getCompany().getCompanyName(),
                application.getEmployment().getEmploymentTitle(),
                application.getCreatedAt(),
                application.getApplicationStatus().name());
    }
}
