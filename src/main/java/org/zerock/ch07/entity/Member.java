package org.zerock.ch07.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

@SequenceGenerator(name = "seq_member", sequenceName ="seq_member",  allocationSize = 1)
@Table(name = "m_member")
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_member")
    private Long mid;

    private String email;

    private String pw;

    private String nickname;

}
