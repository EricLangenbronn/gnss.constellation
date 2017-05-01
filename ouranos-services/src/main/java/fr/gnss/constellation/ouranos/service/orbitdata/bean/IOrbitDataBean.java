package fr.gnss.constellation.ouranos.service.orbitdata.bean;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;

// TODO : remplacer OrbitDataBean par l'interface pour decoupler le truc
public interface IOrbitDataBean {

	EphemerideType getEphemerideType();

	long getGpsWeek();

	int getDay();

	int getHour();

	OrbitType getOrbitType();
}
