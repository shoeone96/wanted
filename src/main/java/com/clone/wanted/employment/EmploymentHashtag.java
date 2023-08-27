package com.clone.wanted.employment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="EmploymentHashtag")
public class EmploymentHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employment_Hashtag_id")
    private int employmentHashtagId;

    @ManyToOne
    @JoinColumn(name="employment_id")
    private Employment employment;

    @Column(name="hashtag_name")
    private String hashtagName;

}
