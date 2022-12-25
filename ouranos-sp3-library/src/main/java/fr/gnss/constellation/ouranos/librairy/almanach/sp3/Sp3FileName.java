package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import org.apache.commons.lang3.StringUtils;

public class Sp3FileName {

    // -------------------- Propriétés de la classe --------------------

    private EphemerideType ephemerideType; // igs, igr, igu
    private long gpsWeek; // GPS Week, week 0000 is Jan. 6-12, 1980
    private int day; // Day of week (Sun=0 --> Sat=6, Full Week=7 TODO : not yet
    // implemented)
    private int hour; // Start hour (00,06,12,18)
    private OrbitType orbitType; // Orbit, other extensions are clk,cls,erp,sum
    private String compressType; // Z Files are Unix compressed

    // ------------------------ Constructeur(s) ------------------------

    /**
     * Initializes a newly created Sp3FileName object.
     *
     * @param p_fileName - A file name string.
     * @throws IllegalArgumentException
     */
    public Sp3FileName(String p_fileName) {
        this.valueOf(p_fileName);
    }

    /**
     * Initializes a newly created Sp3FileName igu object with given parameters.
     *
     * @param ephemerideType - Ephemerid type
     * @param gpsWeek        - Number of week since 1980-01-06
     * @param day            - Number of the day in the week
     * @param hour           - Number of hour in the day (0-23), -1 if igs or igu ephemerid
     *                       type
     * @param orbitType      - Orbit type
     */
    public Sp3FileName(EphemerideType ephemerideType, long gpsWeek, int day, int hour, OrbitType orbitType) {
        this.ephemerideType = ephemerideType;
        this.gpsWeek = gpsWeek;
        this.day = day;
        this.orbitType = orbitType;
        this.hour = hour;
        this.compressType = "Z";
    }

    // ----------------------- Methodes internes -----------------------

    private String igsIgrIglFileName(boolean withCompressType) {
        // sssnnnnx.aaa.Z pour igs et igr
        StringBuilder fileName = new StringBuilder();
        fileName.append(String.valueOf(this.ephemerideType));
        fileName.append(String.valueOf(this.gpsWeek));
        fileName.append(String.valueOf(this.day));
        fileName.append(".");
        fileName.append(String.valueOf(this.orbitType));
        if (withCompressType) {
            fileName.append(".");
            fileName.append(String.valueOf(this.compressType));
        }

        return fileName.toString();
    }

    private String iguFileName(boolean withCompressType) {
        // sssnnnnx_hr.aaa.Z pour igu
        StringBuilder fileName = new StringBuilder();
        fileName.append(String.valueOf(this.ephemerideType));
        fileName.append(String.valueOf(this.gpsWeek));
        fileName.append(String.valueOf(this.day));
        fileName.append("_");
        if (this.hour <= 9) {
            fileName.append("0" + this.hour);
        } else {
            fileName.append(this.hour);
        }
        fileName.append(".");
        fileName.append(String.valueOf(this.orbitType));
        if (withCompressType) {
            fileName.append(".");
            fileName.append(String.valueOf(this.compressType));
        }

        return fileName.toString();
    }

    /**
     * Returns the Sp3FileNameFormat representation of the string argument.
     *
     * @param p_fileName - a string.
     * @throws IllegalArgumentException
     */
    private void valueOf(String p_fileName) {
        if (StringUtils.isBlank(p_fileName)) {
            String message = "Le nom du fichier doit être renseigné. [fileName=" + p_fileName + "]";
            throw new IllegalArgumentException(message);
        }

        try {
            String[] l_fileName = p_fileName.split("\\.");

            ephemerideType = EphemerideType.stringToEphemerideType(l_fileName[0].substring(0, 3));
            gpsWeek = Long.parseLong(l_fileName[0].substring(3, 7));
            day = Integer.parseInt(l_fileName[0].substring(7, 8));

            if (l_fileName[0].length() == 8) { // sssnnnnx.aaa.Z pour igs et igr
                this.hour = -1;
            } else { // sssnnnnx_hr.aaa.Z pour igu
                hour = Integer.parseInt(l_fileName[0].substring(9, 11));
            }

            orbitType = OrbitType.stringToOrbitType(l_fileName[1]);

            if (l_fileName.length > 2) {
                compressType = l_fileName[2];
            }
        } catch (NumberFormatException e) {
            String l_message = "Impossible de parser le nom du fichier. [fileName=" + p_fileName + "]";
            throw new IllegalArgumentException(l_message, e);
        }
    }

    public String getFileName(boolean withCompressType) {
        String fileName = "";

        switch (this.ephemerideType) {
            case igl:
                fileName = this.igsIgrIglFileName(withCompressType);
                break;
            case igs:
                fileName = this.igsIgrIglFileName(withCompressType);
                break;
            case igr:
                fileName = this.igsIgrIglFileName(withCompressType);
                break;
            case igu:
                fileName = this.iguFileName(withCompressType);
                break;
            default:
                break;
        }

        return fileName.toString();

    }

    // -------------------- Méthodes Getter / Setter -------------------

    /**
     * Get the type of the ephemeride.
     *
     * @return the type of the ephemeride.
     */
    public EphemerideType getEphemerideType() {
        return ephemerideType;
    }

    /**
     * Get the number of week from 1980-01-06
     *
     * @return the number of GPS week.
     */
    public long getGpsWeek() {
        return gpsWeek;
    }

    /**
     * Get the day of the week.
     *
     * @return the day of the week.
     */
    public int getDay() {
        return day;
    }

    /**
     * Get the start hour, if EphemeridType is "igu", -1 otherwise.
     *
     * @return the start hour.
     */
    public int getHour() {
        return hour;
    }

    /**
     * Get the orbit type.
     *
     * @return the orbit type.
     */
    public OrbitType getOrbitType() {
        return orbitType;
    }

    /**
     * Get the type of the compression.
     *
     * @return the type of the compression.
     */
    public String getCompressType() {
        return compressType;
    }

    /**
     * Return true if compress, otherwise false.
     *
     * @return true if compress, otherwise false.
     */
    public boolean isCompress() {
        if (StringUtils.isBlank(compressType)) {
            return false;
        } else {
            return true;
        }
    }

    // -------------------- Methodes de l'interface --------------------

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sp3FileName other = (Sp3FileName) obj;
        if (compressType == null) {
            if (other.compressType != null)
                return false;
        } else if (!compressType.equals(other.compressType))
            return false;
        if (day != other.day)
            return false;
        if (ephemerideType != other.ephemerideType)
            return false;
        if (gpsWeek != other.gpsWeek)
            return false;
        if (hour != other.hour)
            return false;
        if (orbitType != other.orbitType)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Sp3FileName [ephemerideType=" + ephemerideType + ", gpsWeek=" + gpsWeek + ", day=" + day + ", hour=" + hour + ", orbitType=" + orbitType
                + ", compressType=" + compressType + "]";
    }

}