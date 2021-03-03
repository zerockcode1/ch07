package org.zerock.ch07.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.ch07.entity.Movie;
import org.zerock.ch07.repository.jpql.MovieSelector;

public interface MovieRepository extends MovieSelector, JpaRepository<Movie, Long> {


//        @Query("select m, avg(coalesce(r.grade,0)),  count(r) from Movie m " +
//                "left outer join Review  r on r.movie = m group by m.mno, m.title, m.regDate, m.modDate")
//        Page<Object[]> getListPage(Pageable pageable);

        @Query("select m, min(mi) , avg(coalesce(r.grade,0)),  count(distinct r.reviewnum) from Movie m " +
                "left outer join MovieImage mi on mi.movie = m "+
                "left outer join Review  r on r.movie = m group by m.mno, m.title, m.regDate, m.modDate, mi.movie , r.movie")
        Page<Object[]> getListPage(Pageable pageable);
}
