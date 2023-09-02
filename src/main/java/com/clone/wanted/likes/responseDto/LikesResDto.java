package com.clone.wanted.likes.responseDto;


import com.clone.wanted.likes.responseDto.LikeModalResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikesResDto {
    private List<LikeModalResDto> likeModalResDto;
    private int likesNum;
}



