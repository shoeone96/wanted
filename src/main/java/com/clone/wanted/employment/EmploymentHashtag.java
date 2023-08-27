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
    private int employmentHashtagId;

    @ManyToOne
    @JoinColumn(name="employmentId")
    private Employment employment;

    private String hashtagName;

}
