package com.clone.wanted.likes;


import com.clone.wanted.application.responseDto.CompanyApplicationResponseDto;
import com.clone.wanted.config.BaseResponse;
import com.clone.wanted.likes.responseDto.LikesDto;
import com.clone.wanted.likes.responseDto.LikesResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LikesController {
    private final LikesService likesService;

    @PostMapping("/employment/{employmentId}/likes")
    public BaseResponse<Void> changeLikes(Authentication authentication, @PathVariable long employmentId){
        likesService.changeLikes(employmentId,authentication.getName());
        return BaseResponse.success();
    }

    @GetMapping("/employment/{employmentId}/likes")
    public BaseResponse<LikesResDto> retrieveLikes(@PathVariable long employmentId){
        return BaseResponse.success(likesService.retrieveLikes(employmentId));
    }

    //test -> 좋아요 누른 회원 정보 조회
    @GetMapping("/likes")
    public BaseResponse<List<LikesDto>> retrieveLikeAll() {
        List<LikesDto> likesList = likesService.retrieveLikeAll();

        return BaseResponse.success(likesList);
    }

}

