package jwd.sajam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.sajam.dto.SajamDTO;
import jwd.sajam.dto.StandDTO;
import jwd.sajam.model.Sajam;
import jwd.sajam.model.Stand;
import jwd.sajam.service.SajamService;
import jwd.sajam.service.StandService;
import jwd.sajam.support.SajamToSajamDTO;
import jwd.sajam.support.StandToStandDTO;

@RestController
@RequestMapping("/api/sajmovi")
public class ApiSajamController {

	@Autowired
	private SajamService sajamService;

	@Autowired
	private StandService standService;

	@Autowired
	private SajamToSajamDTO toDto;

	@Autowired
	private StandToStandDTO toStandDto;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<SajamDTO>> getAll(){

		List<Sajam> lista = sajamService.findAll();

		return new ResponseEntity<>(toDto.convert(lista), HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<SajamDTO> getOne(@PathVariable Long id){

		Sajam sajam = sajamService.findOne(id);

		if(sajam.equals(null)){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDto.convert(sajam), HttpStatus.OK);
	}


	@RequestMapping(method=RequestMethod.GET, value="/{idSajam}/standovi")
	public ResponseEntity<List<StandDTO>> getBySajam(@PathVariable Long idSajam, @RequestParam(defaultValue="0") int page){

		Page<Stand> standovi = standService.findStandBySajamId(idSajam, page);

		HttpHeaders header = new HttpHeaders();
		header.add("totalPages", Integer.toString(standovi.getTotalPages()));

		return new ResponseEntity<>(toStandDto.convert(standovi.getContent()), header, HttpStatus.OK);

	}
}
