package fr.gnss.constellation.ouranos.bean;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Resultats {

	private List<Entry<String, Integer>> visibleSats;

	public Resultats() {
		super();
		visibleSats = new ArrayList<Entry<String, Integer>>();
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
}
