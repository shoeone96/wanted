package com.clone.wanted.likes;


import com.clone.wanted.user.User;
import com.clone.wanted.employment.Employment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Likes {

    /*Todo User, Employment FK 값 develop 에 머지 후 오류 해결되는지 확인*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employment_id")
    private Employment employment;

    private Boolean likeStatus;


    public Likes(User user, Employment employment, Boolean likeStatus) {
        this.user = user;
        this.employment = employment;
        this.likeStatus = likeStatus;
    }


    public void modifyLikes(Boolean likeStatus){
        this.likeStatus = likeStatus;
    }
}
