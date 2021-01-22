package dev.uybv.bid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MediaRepository extends JpaRepository<Media, Integer> {
	List<Media> findByBidId(@Param("bidId") Integer bidId);

	@Query("SELECT e FROM Media e WHERE e.bidId IN (:bidIds)")
	List<Media> findByBidIds(@Param("bidIds") List<Integer> bidIds);
}
