package org.zerock.ch07.repository.jpql;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.ch07.entity.*;
import org.zerock.ch07.repository.jpql.MovieSelector;

import java.util.Collection;

public class MovieSelectorImpl extends QuerydslRepositorySupport implements MovieSelector {

    public MovieSelectorImpl() {
        super(Movie.class);
    }

    @Override
    public Object[] getListPage2() {

        QMovie movie = QMovie.movie;
        QReview review = QReview.review;
        QMovieImage movieImage = QMovieImage.movieImage;


        JPQLQuery<MovieImage> query = from(movieImage);
        query.leftJoin(movie).on(movie.eq(movieImage.movie));
        JPQLQuery<Tuple> tuple2 = query
                .select(movie, movieImage ,
                        JPAExpressions.select(review.countDistinct()).from(review).where(review.movie.eq(movieImage.movie)),
                        JPAExpressions.select(review.grade.avg()).from(review).where(review.movie.eq(movieImage.movie))
                )
                .where( movieImage.inum.in(JPAExpressions.select(movieImage.inum.min()).from(movieImage).where(movie.eq(movieImage.movie))
                        .groupBy(movie, movieImage.movie))
            ).orderBy(movie.mno.desc());




        return tuple2.fetch().toArray();
    }
}
