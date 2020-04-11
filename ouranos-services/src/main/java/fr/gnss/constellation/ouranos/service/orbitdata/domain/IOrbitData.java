package fr.gnss.constellation.ouranos.service.orbitdata.domain;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;

// TODO : remplacer OrbitDataBean par l'interface pour decoupler le truc
public interface IOrbitData {

	EphemerideType getEphemerideType();

	long getGpsWeek();

	int getDay();

	int getHour();

	OrbitType getOrbitType();
}
