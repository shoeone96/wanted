package com.clone.wanted.employment;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.DateTimeException;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="Employment")
public class Employment {

    /*Todo 1 develop 브랜치에서  merge 후 Company  FK키 오류 해결되는지 확인하기 */
    /*Todo 2 BaseEntity로 created_at, updated_at, deleted_at 상속받기 */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employmentId;


    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    private String employmentTitle;

    private String employmentContents;

    private String address;

    private LocalDateTime deadline;

    private int employmentReward;

    private String position;


}
