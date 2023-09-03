package com.clone.wanted.likes.responseDto;

import com.clone.wanted.likes.Likes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikesDto {
	private long userId;
	private long employmentId;
	private String email;

	public static LikesDto of(Likes like){
		return new LikesDto(
				like.getUser().getId(),
				like.getEmployment().getId(),
				like.getUser().getEmail());
	}
}

