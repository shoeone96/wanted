package com.clone.wanted.likes;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name="Likes")
public class Likes {

    /*Todo User, Employment FK 값 develop 에 머지 후 오류 해결되는지 확인*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int likeId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="employment_id")
    private Employment employment;

    private Boolean likeStatus;



}
