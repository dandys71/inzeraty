package cz.uhk.ppro.inzeraty.sluzby;

import java.util.List;

import cz.uhk.ppro.inzeraty.model.Inzerat;

public interface UlozisteInzeratu {
	 public List<Inzerat> getInzeraty();
	 public void pridej(Inzerat i);
	 public void odstran(Inzerat i);
	 public void odstran(int id);
	 public void uprav(Inzerat i);
	 public Inzerat getById(int id);
	 public List<Inzerat> getInzeratyByKategorie(String kategorie);
}
