package com.clone.wanted.skill;

import com.clone.wanted.employment.Employment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class EmploymentSkill {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Employment_skill_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skill skill;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employment_id")
    private Employment employment;
}
