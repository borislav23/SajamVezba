package jwd.sajam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.sajam.model.Stand;

@Repository
public interface StandRepository
			extends JpaRepository<Stand, Long> {

	Page<Stand> findStandBySajamId(Long sajamId, Pageable page);

	@Query("select s from Stand s where "
			+ "(:zakupac IS NULL or s.zakupac like :zakupac ) AND "
			+ "(:minP IS NULL or s.povrsina >= :minP) AND"
			+ "(:maxP iS NULL or s.povrsina <= :maxP)"
			)
	Page<Stand> trazi(@Param("zakupac") String zakupac,
					  @Param("minP") Integer minP,
					  @Param("maxP") Integer maxP,
					  Pageable page);



}

