package fr.gnss.constellation.ouranos.bean.orbitdata;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;

public class OrbitDataBean {

	private EphemerideType ephemeride;
	private long epoch;
	private int dayInWeek;
	private int hour;
	private OrbitType orbitType;

	/**
	 * Initializes a newly created OrbitDataBean object.
	 * 
	 * @param ephemeride
	 *            - Ephemerid type
	 * @param epoch
	 *            - Number of week since 1980-01-06
	 * @param dayInWeek
	 *            - Number of the day in the week
	 * @param hour
	 *            - Number of hour in the day (0-23), -1 if igs or igu ephemerid
	 *            type
	 * @param orbitType
	 *            - Orbit type
	 */
	public OrbitDataBean(EphemerideType ephemeride, long epoch, int dayInWeek, int hour, OrbitType orbitType) {
		this.ephemeride = ephemeride;
		this.epoch = epoch;
		this.dayInWeek = dayInWeek;
		this.hour = hour;
		this.orbitType = orbitType;
	}

	public EphemerideType getEphemeride() {
		return ephemeride;
	}

	public void setEphemeride(EphemerideType ephemeride) {
		this.ephemeride = ephemeride;
	}

	public long getEpoch() {
		return epoch;
	}

	public void setEpoch(long epoch) {
		this.epoch = epoch;
	}

	public int getDayInWeek() {
		return dayInWeek;
	}

	public void setDayInWeek(int dayInWeek) {
		this.dayInWeek = dayInWeek;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public OrbitType getOrbitType() {
		return orbitType;
	}

	public void setOrbitType(OrbitType orbitType) {
		this.orbitType = orbitType;
	}

	@Override
	public String toString() {
		return "OrbitDataBean [ephemeride=" + ephemeride + ", epoch=" + epoch + ", dayInWeek=" + dayInWeek + ", hour="
				+ hour + ", orbitType=" + orbitType + "]";
	}

}
