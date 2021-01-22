package dev.uybv.bid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	@Query("SELECT e FROM Member e WHERE e.id IN (:ids)")
	List<Member> findByIds(@Param("ids") List<Integer> ids);

	Member findByUsername(String username);
}
