package jwd.sajam.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.sajam.dto.StandDTO;
import jwd.sajam.model.Stand;

@Component
public class StandToStandDTO implements Converter<Stand, StandDTO> {

	@Override
	public StandDTO convert(Stand stand) {
		StandDTO dto = new StandDTO();

		dto.setId(stand.getId());
		dto.setZakupac(stand.getZakupac());
		dto.setPovrsina(stand.getPovrsina());
		dto.setSajamID(stand.getSajam().getId());
		dto.setSajamNaziv(stand.getSajam().getNaziv());

		return dto;
	}

	public List<StandDTO> convert(List<Stand> standovi){
		List<StandDTO> list = new ArrayList<>();

		for (Stand stand : standovi) {
			list.add(convert(stand));
		}
		return list;
	}

}
