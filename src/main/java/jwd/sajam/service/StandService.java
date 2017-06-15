package jwd.sajam.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.sajam.model.Stand;

public interface StandService {

	Page<Stand> findAll(int page);
	Stand findOne(Long id);
	void save(Stand stand);
	void delete(Long id);
	Page<Stand> findStandBySajamId(Long sajamId, int pageNum);
	Page<Stand> trazi(@Param("zakupac") String zakupac,
					  @Param("minP") Integer minP,
					  @Param("maxP") Integer maxP,
					  int page);

}
