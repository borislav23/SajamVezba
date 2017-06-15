package jwd.sajam.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.sajam.dto.SajamDTO;
import jwd.sajam.model.Sajam;

@Component
public class SajamToSajamDTO implements Converter<Sajam, SajamDTO> {

	@Override
	public SajamDTO convert(Sajam sajam) {
		SajamDTO dto = new SajamDTO();

		dto.setId(sajam.getId());
		dto.setNaziv(sajam.getNaziv());
		dto.setMesto(sajam.getMesto());
		dto.setCena(sajam.getCena());
		dto.setOtvaranje(sajam.getOtvaranje());
		dto.setZatvaranje(sajam.getZatvaranje());

		return dto;
	}

	public List<SajamDTO> convert(List<Sajam> sajmovi){

		List<SajamDTO> lista = new ArrayList<>();

		for (Sajam s : sajmovi) {
			lista.add(convert(s));
		}
		return lista;
	}

}
