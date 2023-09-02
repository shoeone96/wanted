package com.clone.wanted.likes;


import com.clone.wanted.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Long> {
    Optional<Likes> findByUser(User user);


    @Query(value = " select res_tb.user_id, res_tb.name  from (select users.user_id, users.name, likeEm_tb.like_status from (select * from likes where employment_id=:employmentId)AS likeEm_tb join users on users.user_id= likeEm_tb.user_id) AS res_tb where res_tb.like_status=1",nativeQuery = true)
    List<Object[]> getModal(@Param("employmentId") Long employmentId);

    @Query(value = "select count(like_tb.like_id) as likeNum from (select * from likes where likes.employment_id=:employmentId) as like_tb where like_tb.like_status=1",nativeQuery = true)
    int getLikeNum(@Param("employmentId")long employmentId );



}
