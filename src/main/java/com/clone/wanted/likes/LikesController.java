package com.clone.wanted.likes;


import com.clone.wanted.config.BaseResponse;
import com.clone.wanted.likes.responseDto.LikesResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

}

