package jwd.sajam.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.sajam.model.Stand;
import jwd.sajam.repository.StandRepository;
import jwd.sajam.service.StandService;

@Service
@Transactional
public class StandServiceImpl implements StandService {

	@Autowired
	StandRepository standRepository;

	@Override
	public Page<Stand> findAll(int pageNum) {
		return standRepository.findAll(new PageRequest(pageNum, 3));
	}

	@Override
	public Stand findOne(Long id) {
		return standRepository.findOne(id);
	}

	@Override
	public void save(Stand stand) {
		standRepository.save(stand);

	}

	@Override
	public void delete(Long id) {
		standRepository.delete(id);

	}

	@Override
	public Page<Stand> findStandBySajamId(Long sajamId, int pageNum) {
		return standRepository.findStandBySajamId(sajamId, new PageRequest(pageNum, 3));
	}

	@Override
	public Page<Stand> trazi(String zakupac, Integer minP, Integer maxP, int pageNum) {
		return standRepository.trazi(zakupac, minP, maxP, new PageRequest(pageNum, 2));
	}



}
