package com.clone.wanted.application;

import com.clone.wanted.user.User;
import com.clone.wanted.employment.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByUserAndEmployment(User user, Employment employment);
    List<Application> findAllByUser(User user);
    List<Application> findAllByEmployment(Employment employment);
    List<Application> findAllByUserAndEmployment(User user, Employment employment);

}
