package org.zerock.ch07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ch07.entity.Review;

public interface ReveiwRepository extends JpaRepository<Review, Long> {
}
