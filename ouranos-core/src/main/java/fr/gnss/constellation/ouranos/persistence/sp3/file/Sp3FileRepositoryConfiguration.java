package fr.gnss.constellation.ouranos.persistence.sp3.file;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
@AllArgsConstructor
public class Sp3FileRepositoryConfiguration {

    private final Sp3FileRepositoryProperties sp3FileRepositoryProperties;

    @Produces
    public Sp3StorageDirectory getDefaultSp3StorageDirectory() {

        Sp3StorageDirectory defaultDownloadSp3Directory;
        if (StringUtils.isBlank(sp3FileRepositoryProperties.directory)) {
            defaultDownloadSp3Directory = new Sp3StorageDirectory(System.getProperty("user.home"));
        } else {
            defaultDownloadSp3Directory = new Sp3StorageDirectory(sp3FileRepositoryProperties.directory);
        }

        return defaultDownloadSp3Directory;
    }

}
