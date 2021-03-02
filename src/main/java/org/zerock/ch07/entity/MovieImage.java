package org.zerock.ch07.entity;


import lombok.*;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "seq_movieimg", sequenceName ="seq_movieimg",  allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "movie")
public class MovieImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movieimg")
    private Long inum;

    private String uuid;

    private String imgName;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
}
