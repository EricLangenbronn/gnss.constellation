package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sp3Header {

	private String versionSymbol;
	private String posOrVelFlag;
	private LocalDateTime startTime;
	private String numberOfEpochs;
	private String dataUsed;
	private String coordinateSys;
	private String orbitType;
	private String agency;

	private String GPSWeek;
	private String sSecondsOfWeek;
	private String epochInterval;
	private String modJulDaySt;
	private String fractionalDay;

	private String numberOfSats;

	private List<String> satId;

	private List<Integer> satAccuracy;

	public Sp3Header() {
		this.satId = new ArrayList<>();
		this.satAccuracy = new ArrayList<>();
	}

	public String getVersionSymbol() {
		return versionSymbol;
	}

	public void setVersionSymbol(String versionSymbol) {
		this.versionSymbol = versionSymbol;
	}

	public String getPosOrVelFlag() {
		return posOrVelFlag;
	}

	public void setPosOrVelFlag(String posOrVelFlag) {
		this.posOrVelFlag = posOrVelFlag;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public String getNumberOfEpochs() {
		return numberOfEpochs;
	}

	public void setNumberOfEpochs(String numberOfEpochs) {
		this.numberOfEpochs = numberOfEpochs;
	}

	public String getDataUsed() {
		return dataUsed;
	}

	public void setDataUsed(String dataUsed) {
		this.dataUsed = dataUsed;
	}

	public String getCoordinateSys() {
		return coordinateSys;
	}

	public void setCoordinateSys(String coordinateSys) {
		this.coordinateSys = coordinateSys;
	}

	public String getOrbitType() {
		return orbitType;
	}

	public void setOrbitType(String orbitType) {
		this.orbitType = orbitType;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getGPSWeek() {
		return GPSWeek;
	}

	public void setGPSWeek(String gPSWeek) {
		GPSWeek = gPSWeek;
	}

	public String getsSecondsOfWeek() {
		return sSecondsOfWeek;
	}

	public void setsSecondsOfWeek(String sSecondsOfWeek) {
		this.sSecondsOfWeek = sSecondsOfWeek;
	}

	public String getEpochInterval() {
		return epochInterval;
	}

	public void setEpochInterval(String epochInterval) {
		this.epochInterval = epochInterval;
	}

	public String getModJulDaySt() {
		return modJulDaySt;
	}

	public void setModJulDaySt(String modJulDaySt) {
		this.modJulDaySt = modJulDaySt;
	}

	public String getFractionalDay() {
		return fractionalDay;
	}

	public void setFractionalDay(String fractionalDay) {
		this.fractionalDay = fractionalDay;
	}

	public String getNumberOfSats() {
		return numberOfSats;
	}

	public void setNumberOfSats(String numberOfSats) {
		this.numberOfSats = numberOfSats;
	}

	public List<String> getSatId() {
		return satId;
	}

	public void setSatId(List<String> satId) {
		this.satId = satId;
	}

	public List<Integer> getSatAccuracy() {
		return satAccuracy;
	}

	public void setSatAccuracy(List<Integer> satAccuracy) {
		this.satAccuracy = satAccuracy;
	}
}
