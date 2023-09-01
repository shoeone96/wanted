package com.clone.wanted.likes;


import com.clone.wanted.employment.EmploymentHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Long> {
    Optional<Likes> findByUserUserId(Long UserId);
}
