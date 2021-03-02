package org.zerock.ch07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ch07.entity.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
