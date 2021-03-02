package org.zerock.ch07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ch07.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
