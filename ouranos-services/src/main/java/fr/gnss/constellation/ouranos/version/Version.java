package fr.gnss.constellation.ouranos.version;

import org.apache.commons.lang3.StringUtils;

public class Version implements Comparable<Version> {

	private int version;

	public Version() {
		this(0);
	}

	public Version(int version) {
		this.version = version;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Version other = (Version) obj;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public int compareTo(Version o) {
		int comparator = -1;

		if (this.version == o.getVersion()) {
			comparator = 0;
		}

		if (this.version < o.getVersion()) {
			comparator = -1;
		}

		if (this.version > o.getVersion()) {
			comparator = 1;
		}

		return comparator;
	}

	@Override
	public String toString() {
		String version = StringUtils.leftPad(String.valueOf(this.version), 2, "0");
		return "v" + version;
	}

}
