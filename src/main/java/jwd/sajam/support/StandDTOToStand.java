package jwd.sajam.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.sajam.dto.StandDTO;
import jwd.sajam.model.Stand;
import jwd.sajam.service.SajamService;
import jwd.sajam.service.StandService;

@Component
public class StandDTOToStand implements Converter<StandDTO, Stand> {

	@Autowired
	private StandService standService;

	@Autowired
	private SajamService sajamService;

	@Override
	public Stand convert(StandDTO standDto) {

		Stand stand = new Stand();

		if(standDto.getId() == null){
			stand.setSajam(sajamService.findOne(standDto.getSajamID()));
		}else{
			stand = standService.findOne(standDto.getId());
		}
		stand.setId(standDto.getId());
		stand.setZakupac(standDto.getZakupac());
		stand.setPovrsina(standDto.getPovrsina());

		return stand;
	}

}
