package fr.gnss.constellation.ouranos.librairy.almanach.sp3.reader;

import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.TimeCoordinateSatellitePosition;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.Sp3CoreParser;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3HeaderParser;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import java.io.Closeable;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sp3Reader implements Closeable, Iterable<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> {

  private final Sp3FileReader sp3FileReader;
  private final Sp3CoreParser sp3CoreParser;
  private final Sp3HeaderParser sp3HeaderParser;

  private Sp3Header sp3Header;

  public Sp3Reader(Sp3File sp3File) throws IOException {
    Sp3FileType sp3FileType = sp3File.getSp3FileType();

    this.sp3FileReader = new Sp3FileReader(sp3File, true);

    this.sp3HeaderParser = new Sp3HeaderParser(sp3FileType);
    this.sp3CoreParser = new Sp3CoreParser(sp3FileType);
  }

  public Sp3Header getSp3Header() throws IOException {

    if (sp3Header != null) {
      return sp3Header;
    }

    if (sp3FileReader.isEndOfHeader()) {
      throw new RuntimeException("Lecture sequenciel, impossble d'acceder au header");
    }

    List<String> header = new ArrayList<>();
    do {
      header.add(sp3FileReader.readLine());
    } while (!sp3FileReader.isEndOfHeader());

    sp3Header = sp3HeaderParser.loadSp3Header(header.toArray(new String[header.size()]));

    return sp3Header;
  }

  public List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> getPositionsAndClocksRecords(
      LocalDateTime startClock, LocalDateTime endClock) throws IOException {

    if (endClock.isBefore(startClock)) {
      throw new IllegalArgumentException(
          "Start clock does not be before end clock : [startClock=" + startClock + ",endClock=" + endClock + "]");
    }

    while (!sp3FileReader.isEndOfHeader()) { // skip all header
      sp3FileReader.readLine();
    }

    LocalDateTime currentClock;
    do { // skip all lines before start clock
      String line;
      do {
        line = sp3FileReader.readLine();
      } while (!Sp3FileReader.isEof(line) && !Sp3CoreParser.isClockLine(line));

      currentClock = sp3CoreParser.parseEpochHeader(line);
    } while (currentClock.isBefore(startClock));

    long currentBeginLinePosInFile = sp3FileReader.getFilePointer();
    List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> positionsAndClocksRecords = new ArrayList<>();
    while (currentClock.isBefore(endClock)) { // recuperation des données entre les deux dates

      List<String> satPosition = new ArrayList<>();
      String line;
      do {
        currentBeginLinePosInFile = sp3FileReader.getFilePointer();
        line = sp3FileReader.readLine();

        if (Sp3CoreParser.isPositionLine(line)) {
          satPosition.add(line);
        }

      } while (!Sp3FileReader.isEof(line) && !Sp3CoreParser.isClockLine(line));
      positionsAndClocksRecords.add(
          sp3CoreParser.parsePositionsAndClock(currentClock, satPosition.toArray(new String[satPosition.size()])));

      if (Sp3FileReader.isEof(line)) {
        currentClock = endClock.plus(15, ChronoUnit.MINUTES); // fin du fichier plus de données pour ces paramètres
      } else {
        currentClock = sp3CoreParser.parseEpochHeader(line);
      }

    }
    sp3FileReader.seek(currentBeginLinePosInFile); // on a lu une ligne en trop, celle de la clock, on revient en arriere

    return positionsAndClocksRecords;
  }

  public TimeCoordinateSatellitePosition<CartesianCoordinate3D> readNext() throws IOException {

    while (!sp3FileReader.isEndOfHeader()) { // skip all header
      sp3FileReader.readLine();
    }

    String currentClock = sp3FileReader.readLine();
    if (Sp3FileReader.isEof(currentClock)) {
      return null;
    }

    String line;
    long currentBeginLinePosInFile;
    List<String> satPosition = new ArrayList<>();
    do {
      currentBeginLinePosInFile = sp3FileReader.getFilePointer();
      line = sp3FileReader.readLine();

      if (Sp3CoreParser.isPositionLine(line)) {
        satPosition.add(line);
      }

    } while (!Sp3FileReader.isEof(line) && !Sp3CoreParser.isClockLine(line));
    sp3FileReader.seek(currentBeginLinePosInFile); // on a lu une ligne en trop, celle de la clock, on revient en arriere

    return sp3CoreParser.parsePositionsAndClock(currentClock, satPosition.toArray(new String[satPosition.size()]));
  }

  @Override
  public Iterator<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> iterator() {
    try {
      return new Sp3Iterator(this);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void close() {
    this.sp3FileReader.close();
  }
}
