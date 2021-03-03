package org.zerock.ch07.repository.jpql;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.ch07.entity.*;
import org.zerock.ch07.repository.jpql.MovieSelector;

public class MovieSelectorImpl extends QuerydslRepositorySupport implements MovieSelector {

    public MovieSelectorImpl() {
        super(Movie.class);
    }

    @Override
    public Object[] getListPage2() {

        QMovie movie = QMovie.movie;
        QReview review = QReview.review;
        QMovieImage movieImage = QMovieImage.movieImage;

        JPQLQuery<Movie> jpqlQuery = from (movie);
        jpqlQuery.leftJoin(review).on(review.movie.eq(movie));


        JPQLQuery<Tuple> tuple = jpqlQuery
                .select(movie.mno,
                    JPAExpressions.select(movieImage).from(movieImage).where(movieImage.movie.eq(movie)),
                    review.grade.avg(),
                    review.countDistinct())
                .groupBy(movie, review.movie)
                .orderBy(QMovie.movie.mno.desc());

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        BooleanExpression expression = movie.mno.gt(0L);


        return tuple.fetch().toArray();
    }
}
