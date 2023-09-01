package com.clone.wanted.likes;

import com.clone.wanted.User.User;
import com.clone.wanted.User.UserRepository;
import com.clone.wanted.config.BaseResponse;
import com.clone.wanted.employment.Employment;
import com.clone.wanted.employment.EmploymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Transactional
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final EmploymentRepository employmentRepository;

    public void changeLikes(long employmentId,long userId) {
        // 사용자가 첫 좋아요여서 like id가 없는 경우
        try{
            Likes likes = likesRepository.findByUserUserId(userId).get();
        }catch (Exception e){
            User user = userRepository.findById(userId).get();
            Employment employment = employmentRepository.findById(employmentId).get();
            Likes likesSave = new Likes(user, employment, false);
            likesRepository.save(likesSave);
        }
        Likes likes2 = likesRepository.findByUserUserId(userId).get();

        // 사용자의 값이 false인 경우 즉 좋아요가 안 눌려져 있어서 좋아요를 누르는 상황
         if (likes2.getLikeStatus() == true) {
            Likes likesTrue = likesRepository.findById(likes2.getId()).get();
            likesTrue.modifyLikes(false);
            likesRepository.save(likesTrue);
        }
        // 사용자의 값이 true인 경우 즉 좋아요가  눌려져 있어서 좋아요 취소를 누르는 상황
        else if (likes2.getLikeStatus() == false) {
            Likes likesFalse = likesRepository.findById(likes2.getId()).get();
            likesFalse.modifyLikes(true);
            likesRepository.save(likesFalse);
        }
    }

//    public LikesResDto retrieveLikes(long employmentId,long userId){
//
//
//
//        return
//    }


    public int getLikeNum(long employmentId){
        int likeNum = likesRepository.getLikeNum(employmentId);
        return likeNum;
    }





}
