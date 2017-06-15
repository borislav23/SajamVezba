package jwd.sajam.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_sajam")
public class Sajam {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String naziv;
	@Column
	private String mesto;
	@Column
	private String otvaranje;
	@Column
	private String zatvaranje;
	@Column
	private Integer cena;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="sajam", fetch=FetchType.LAZY)
	private List<Stand> standovi = new ArrayList<>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	public String getOtvaranje() {
		return otvaranje;
	}
	public void setOtvaranje(String otvaranje) {
		this.otvaranje = otvaranje;
	}
	public String getZatvaranje() {
		return zatvaranje;
	}
	public void setZatvaranje(String zatvaranje) {
		this.zatvaranje = zatvaranje;
	}
	public Integer getCena() {
		return cena;
	}
	public void setCena(Integer cena) {
		this.cena = cena;
	}
	public List<Stand> getStandovi() {
		return standovi;
	}
	public void setStandovi(List<Stand> standovi) {
		this.standovi = standovi;
	}

	public void addStand(Stand stand){
		this.standovi.add(stand);

		if(!stand.equals(stand.getSajam())){
			stand.setSajam(this);
		}
	}

	public void removeStand(Stand stand){
		stand.setSajam(null);
		standovi.remove(stand);
	}
}
