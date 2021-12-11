package fr.gnss.constellation.ouranos.librairy.almanach.sp3.reader;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Sp3Iterator implements Iterator<SatelliteTimeCoordinate<CartesianCoordinate3D>> {

    private final Sp3Reader sp3Reader;
    private SatelliteTimeCoordinate<CartesianCoordinate3D> nextClockPositions;

    /**
     * @param sp3Reader Reader for the SP3 data.
     * @throws IOException If unable to read data from the reader.
     */
    public Sp3Iterator(Sp3Reader sp3Reader) throws IOException {
        this.sp3Reader = sp3Reader;
        nextClockPositions = sp3Reader.readNext();
    }

    @Override
    public boolean hasNext() {
        return nextClockPositions != null;
    }

    @Override
    public SatelliteTimeCoordinate<CartesianCoordinate3D> next() {
        SatelliteTimeCoordinate<CartesianCoordinate3D> temp = nextClockPositions;

        try {
            nextClockPositions = sp3Reader.readNext();
        } catch (IOException e) {
            throw new NoSuchElementException();
        }

        return temp;
    }

}
