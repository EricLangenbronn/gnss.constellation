package fr.gnss.constellation.ouranos.bean;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Resultats {

	private List<Error> errors;
	private List<Entry<String, Integer>> visibleSats;

	public Resultats() {
		super();
		visibleSats = new ArrayList<Entry<String, Integer>>();
		errors = new ArrayList<Error>();
	}

	public List<Entry<String, Integer>> getVisibleSats() {
		return visibleSats;
	}

	public void setVisibleSats(List<Entry<String, Integer>> visibleSats) {
		this.visibleSats = visibleSats;
	}

	public void addElem(String dateTime, int nbSat) {
		this.visibleSats.add(new SimpleEntry<String, Integer>(dateTime, nbSat));
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public void addError(Error error) {
		this.errors.add(error);
	}
}
