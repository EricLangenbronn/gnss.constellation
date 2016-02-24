package fr.gnss.constellation.ouranos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;

public class Resultats {

	private List<Entry<LocalDateTime, List<Satelite>>> visibleSats;
	private List<Error> errors;

	public Resultats() {
		super();
		visibleSats = new ArrayList<Entry<LocalDateTime, List<Satelite>>>();
		errors = new ArrayList<Error>();
	}

	public Resultats(List<Entry<LocalDateTime, List<Satelite>>> visibleSats) {
		super();
		this.visibleSats = visibleSats;
	}

	public List<Entry<LocalDateTime, List<Satelite>>> getVisibleSats() {
		return visibleSats;
	}

	public void setVisibleSats(List<Entry<LocalDateTime, List<Satelite>>> visibleSats) {
		this.visibleSats = visibleSats;
	}

	public void addError(Error error) {
		this.errors.add(error);
	}

	public List<Error> getErrors() {
		return this.errors;
	}

	public boolean inError() {
		return errors.size() == 0 ? false : true;
	}
}
