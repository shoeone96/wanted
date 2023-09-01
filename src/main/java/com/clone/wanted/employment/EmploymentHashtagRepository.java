package com.clone.wanted.employment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentHashtagRepository extends JpaRepository<Employment,Long> {
}
