package fr.gnss.constellation.ouranos.orbitdata.sp3.persitence;

import java.io.File;
import java.io.FilenameFilter;

public class Sp3StorageDirectory {

  private final File sp3StorageDirectory;

  public Sp3StorageDirectory(String path) {
    sp3StorageDirectory = new File(path);
  }

  public Sp3StorageDirectory(File file) {
    sp3StorageDirectory = file;
  }

  public boolean isAccessible() {
    return sp3StorageDirectory.exists() && sp3StorageDirectory.isDirectory() && sp3StorageDirectory.canRead();
  }

  public boolean isWritable() {
    return sp3StorageDirectory.exists() && sp3StorageDirectory.isDirectory() && sp3StorageDirectory.canWrite();
  }

  public File[] listFiles() {
    return sp3StorageDirectory.listFiles();
  }

  public File[] listFiles(FilenameFilter filter) {
    return sp3StorageDirectory.listFiles(filter);
  }

  public String getAbsolutePath() {
    return sp3StorageDirectory.getAbsolutePath();
  }
}
