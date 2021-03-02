package org.zerock.ch07.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "seq_movie", sequenceName ="seq_movie",  allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Movie extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movie")
    private Long mno;

    private String title;
}
