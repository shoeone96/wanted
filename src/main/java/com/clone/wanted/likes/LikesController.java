package com.clone.wanted.likes;


import com.clone.wanted.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LikesController {
    private final LikesService likesService;

    @PostMapping("/employment/{employmentId}/likes")
    public BaseResponse changeLikes(@PathVariable long employmentId){
        //Todo userId 나중에 PathVariable로 넘어올 것임 , 그 때 변경
        //UserId 임시값
        long userId=4;
        likesService.changeLikes(employmentId,userId);

        return new BaseResponse<>();
    }

    @GetMapping("/employment/{employmentId}/likes")
    public BaseResponse<LikesResDto> retrieveLikes(@PathVariable long employmentId){
        //UserId 임시값
        long userId=1;
        LikesResDto likesResDto = likesService.retrieveLikes(employmentId, userId);

        return new BaseResponse<>(likesResDto);
    }



    @GetMapping("/employment/{employmentId}/likes1")
    public int getLikes(@PathVariable long employmentId){
        //UserId 임시
        return likesService.getLikeNum(employmentId);
    }





}

