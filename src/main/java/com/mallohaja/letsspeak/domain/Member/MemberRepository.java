package com.mallohaja.letsspeak.domain.Member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
    List<Member> findAll();
    Member save(Member member);
    Optional<Member> findById(Long id);
}
