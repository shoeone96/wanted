package com.clone.wanted.skill;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Skill {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Long id;
    @Column(length = 20)
    private String skillName;
}
