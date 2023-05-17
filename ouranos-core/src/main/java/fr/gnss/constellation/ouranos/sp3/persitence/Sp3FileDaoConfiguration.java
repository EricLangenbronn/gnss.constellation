package fr.gnss.constellation.ouranos.sp3.persitence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@ApplicationScoped
@AllArgsConstructor
public class Sp3FileDaoConfiguration {

  @Produces
  public Sp3StorageDirectory getDefaultSp3StorageDirectory(Sp3FileDaoProperties sp3FileDaoProperties) {

    Sp3StorageDirectory defaultDownloadSp3Directory;
    if (StringUtils.isBlank(sp3FileDaoProperties.directory())) {
      defaultDownloadSp3Directory = new Sp3StorageDirectory(System.getProperty("user.home"));
    } else {
      defaultDownloadSp3Directory = new Sp3StorageDirectory(sp3FileDaoProperties.directory());
    }

    return defaultDownloadSp3Directory;
  }

}
