package com.clone.wanted.employment;

import com.clone.wanted.Company.Company;
import com.clone.wanted.Company.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
@RequiredArgsConstructor
public class EmploymentService {

    private final EmploymentRepository employmentRepository;
    private final CompanyRepository companyRepository;

    public void createEmployment (EmploymentReqDto employmentReqDto){
        long companyId = employmentReqDto.getCompanyId();
        Company company = companyRepository.findById(companyId).get();

        Employment employment = new Employment(company, employmentReqDto);

        employmentRepository.save(employment);

    }
}
