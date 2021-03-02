package org.zerock.ch07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ch07.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
