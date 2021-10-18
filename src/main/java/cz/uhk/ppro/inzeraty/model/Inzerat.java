package cz.uhk.ppro.inzeraty.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Inzerat {
	private int id;
	private String kategorie;
	private String text;
	private BigDecimal cena;
	private String hesloProUpravu;
	private Date datum;

	public Inzerat() {
		hesloProUpravu = UUID.randomUUID().toString().substring(0,6);
		datum = new Date();
	}

	public Inzerat(String kategorie, String text, BigDecimal cena){
		this();
		this.kategorie = kategorie;
		this.text = text;
		this.cena = cena;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKategorie() {
		return kategorie;
	}
	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public BigDecimal getCena() {
		return cena;
	}
	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}
	public String getHesloProUpravu() {
		return hesloProUpravu;
	}
	public void setHesloProUpravu(String hesloProUpravu) {
		this.hesloProUpravu = hesloProUpravu;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cena == null) ? 0 : cena.hashCode());
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result
				+ ((hesloProUpravu == null) ? 0 : hesloProUpravu.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((kategorie == null) ? 0 : kategorie.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inzerat other = (Inzerat) obj;
		if (cena == null) {
			if (other.cena != null)
				return false;
		} else if (!cena.equals(other.cena))
			return false;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (hesloProUpravu == null) {
			if (other.hesloProUpravu != null)
				return false;
		} else if (!hesloProUpravu.equals(other.hesloProUpravu))
			return false;
		if (id != other.id)
			return false;
		if (kategorie == null) {
			if (other.kategorie != null)
				return false;
		} else if (!kategorie.equals(other.kategorie))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inzerat [id=" + id + ", kategorie=" + kategorie + ", text="
				+ text + ", cena=" + cena + ", hesloProUpravu="
				+ hesloProUpravu + ", datum=" + datum + "]";
	}

	public static class Kategorie{
		public static final String NAKUP = "Nákup";
		public static final String PRODEJ = "Prodej";
		public static final String VYMENA = "Výměna";

		public static List<String> vratVsechnyKategorie(){
			return Arrays.asList(NAKUP, PRODEJ, VYMENA);
		}
	}
	
	
}
