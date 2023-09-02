package com.clone.wanted.application.responseDto;

import com.clone.wanted.application.Application;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyApplicationResponseDto {

    private Long employmentId;
    private String username;
    private String position;
    private LocalDateTime registerDate;
    private String applicationStatus;

    public static CompanyApplicationResponseDto of(Application application){
        return new CompanyApplicationResponseDto(
                application.getEmployment().getId(),
                application.getUser().getUsername(),
                application.getEmployment().getEmploymentTitle(),
                application.getCreatedAt(),
                application.getApplicationStatus().name());
    }
}
