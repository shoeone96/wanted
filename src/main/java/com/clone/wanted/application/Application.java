package com.clone.wanted.application;

import com.clone.wanted.BaseEntity;
import com.clone.wanted.User.User;
import com.clone.wanted.employment.Employment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Application extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employment_id")
    private Employment employment;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

}
