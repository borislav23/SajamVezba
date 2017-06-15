package jwd.sajam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.sajam.model.Sajam;
import jwd.sajam.model.Stand;
import jwd.sajam.service.SajamService;

@Component
public class TestData {

	@Autowired
	private SajamService sajamService;

	@PostConstruct
	public void init(){


	Sajam lovRibolov = new Sajam();
	lovRibolov.setNaziv("Sajam lova i ribolova");
	lovRibolov.setMesto("Novi Sad");
	lovRibolov.setCena(500);
	lovRibolov.setOtvaranje("15.Maj 2017");
	lovRibolov.setZatvaranje("20.Maj 2017");

	Stand prvi = new Stand();
	prvi.setPovrsina(50);
	prvi.setZakupac("Mile Milic");
	prvi.setSajam(lovRibolov);

	Stand drugi = new Stand();
	drugi.setPovrsina(70);
	drugi.setZakupac("Djoko Djokic");
	drugi.setSajam(lovRibolov);

	sajamService.save(lovRibolov);

	Sajam nauke = new Sajam();
	nauke.setNaziv("Sajam nauke");
	nauke.setMesto("Novi Sad");
	nauke.setCena(700);
	nauke.setOtvaranje("15.Avgust 2017");
	nauke.setZatvaranje("20.Avgust 2017");

	Stand treci = new Stand();
	treci.setPovrsina(70);
	treci.setZakupac("Zora Zoric");
	treci.setSajam(nauke);

	Stand cetvrti = new Stand();
	cetvrti.setPovrsina(100);
	cetvrti.setZakupac("Jana Janic");
	cetvrti.setSajam(nauke);

	sajamService.save(nauke);

	Sajam automobila = new Sajam();
	automobila.setNaziv("Sajam automobila");
	automobila.setMesto("Beograd");
	automobila.setCena(100);
	automobila.setOtvaranje("15.Mart 2017");
	automobila.setZatvaranje("20.Mart 2017");

	Stand peti = new Stand();
	peti.setPovrsina(100);
	peti.setZakupac("Milan Micic");
	peti.setSajam(automobila);

	Stand sesti = new Stand();
	sesti.setPovrsina(120);
	sesti.setZakupac("Mia Mijic");
	sesti.setSajam(automobila);

	sajamService.save(automobila);

	}
}
