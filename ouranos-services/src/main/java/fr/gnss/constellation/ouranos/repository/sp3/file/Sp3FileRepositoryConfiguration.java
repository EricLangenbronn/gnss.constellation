package fr.gnss.constellation.ouranos.repository.sp3.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.File;

@Configuration
@EnableConfigurationProperties(Sp3FileRepositoryProperties.class)
@RequiredArgsConstructor
@Slf4j
public class Sp3FileRepositoryConfiguration {

    @Bean("sp3Directory")
    public File getSp3Directory(Sp3FileRepositoryProperties props) {

        File defaultDownloadSp3Directory;
        if (StringUtils.isBlank(props.getDirectory())) {
            defaultDownloadSp3Directory = new File(System.getProperty("user.home"));
        } else {
            defaultDownloadSp3Directory = new File(props.getDirectory());
        }

        return defaultDownloadSp3Directory;
    }

}
