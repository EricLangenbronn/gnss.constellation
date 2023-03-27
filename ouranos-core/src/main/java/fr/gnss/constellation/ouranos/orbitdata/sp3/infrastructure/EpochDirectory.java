package fr.gnss.constellation.ouranos.orbitdata.sp3.infrastructure;

import org.apache.commons.lang3.StringUtils;

public class EpochDirectory {

  private final String epochDirectory;

  public EpochDirectory(String epochDirectory) {
    if (StringUtils.isBlank(epochDirectory)) {
      throw new IllegalArgumentException("Le répertoire ne peut être vide ou null");
    }

    this.epochDirectory = epochDirectory;
  }

  public String getValue() {
    return this.epochDirectory;
  }
}
