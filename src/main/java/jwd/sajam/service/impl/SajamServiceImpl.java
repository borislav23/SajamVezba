package jwd.sajam.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.sajam.model.Sajam;
import jwd.sajam.repository.SajamRepository;
import jwd.sajam.service.SajamService;

@Service
@Transactional
public class SajamServiceImpl implements SajamService {

	@Autowired
	SajamRepository sajamRepository;

	@Override
	public List<Sajam> findAll() {
		return sajamRepository.findAll();
	}

	@Override
	public Sajam findOne(Long id) {
		return sajamRepository.findOne(id);
	}

	@Override
	public void save(Sajam sajam) {
		sajamRepository.save(sajam);

	}

	@Override
	public void delete(Long id) {
		sajamRepository.delete(id);

	}

}
