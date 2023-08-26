package com.clone.wanted.skill;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class EmploymentSkill {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmploymentSkillId")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skillId")
    private Skill skill;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employmentId")
    private Employment employment;
}
