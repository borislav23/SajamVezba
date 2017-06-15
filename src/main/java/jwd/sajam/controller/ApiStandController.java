package jwd.sajam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.sajam.dto.StandDTO;
import jwd.sajam.model.Sajam;
import jwd.sajam.model.Stand;
import jwd.sajam.service.SajamService;
import jwd.sajam.service.StandService;
import jwd.sajam.support.StandDTOToStand;
import jwd.sajam.support.StandToStandDTO;

@RestController
@RequestMapping("/api/standovi")
public class ApiStandController {

	@Autowired
	private StandService standService;

	@Autowired
	private SajamService sajamService;

	@Autowired
	private StandToStandDTO toDto;

	@Autowired
	private StandDTOToStand toStand;

//	@RequestMapping(method=RequestMethod.GET)
//	public ResponseEntity<List<StandDTO>> getAll(@RequestParam(defaultValue="0") int page){
//
//		Page<Stand> lista = standService.findAll(page);
//
//		HttpHeaders header = new HttpHeaders();
//		header.add("totalPages", Integer.toString(lista.getTotalPages()));
//
//		return new ResponseEntity<>(toDto.convert(lista.getContent()), header, HttpStatus.OK);
//	}

	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<StandDTO> getOne(@PathVariable Long id){

		Stand stand = standService.findOne(id);

		if(stand == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDto.convert(stand), HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<StandDTO>> search
						(@RequestParam(required=false) String zakupac,
						 @RequestParam(required=false) Integer minP,
						 @RequestParam(required=false) Integer maxP,
						 @RequestParam(defaultValue="0") int pageNum){

		Page<Stand> stand;

		if(zakupac != null || minP != null || maxP != null){
			stand = standService.trazi(zakupac, minP, maxP, pageNum);
		}else{
			stand = standService.findAll(pageNum);
		}

		HttpHeaders header = new HttpHeaders();
		header.add("totalPages", Integer.toString(stand.getTotalPages()));

		return new ResponseEntity<>(toDto.convert(stand.getContent()), header, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<StandDTO> add(@RequestBody StandDTO stand){

		Stand stand2 = toStand.convert(stand);
		standService.save(stand2);

		return new ResponseEntity<>(toDto.convert(stand2), HttpStatus.CREATED);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<StandDTO> edit(@RequestBody StandDTO izmenjen, @PathVariable Long id){

		Stand stand = standService.findOne(id);
		Sajam sajam = sajamService.findOne(id);

		if(stand==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		stand.setZakupac(izmenjen.getZakupac());
		stand.setPovrsina(izmenjen.getPovrsina());
		//stand.setSajam(sajam);

		standService.save(stand);

		return new ResponseEntity<>(toDto.convert(stand), HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{id}/sajam/{sajamId}")
	public ResponseEntity<StandDTO> remove(@PathVariable Long id, @PathVariable Long sajamId){

		Sajam sajam = sajamService.findOne(sajamId);
		Stand stand = standService.findOne(id);

		sajam.removeStand(stand);

		standService.delete(id);
		sajamService.save(sajam);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
