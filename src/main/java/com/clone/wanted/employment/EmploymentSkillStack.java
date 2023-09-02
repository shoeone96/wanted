package com.clone.wanted.employment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class EmploymentSkillStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employmnet_skill_stack")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employment_id")
    private Employment employment;

    private String skillName;

}
