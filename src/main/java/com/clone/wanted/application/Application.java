package com.clone.wanted.application;

import com.clone.wanted.BaseEntity;
import com.clone.wanted.User.User;
import com.clone.wanted.employment.Employment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    private Application (User user, Employment employment){
        this.user = user;
        this.employment = employment;
        this.applicationStatus = ApplicationStatus.ONGOING;
    }

    public static Application newEnrollment(User user, Employment employment){
        return new Application(user, employment);
    }

}
