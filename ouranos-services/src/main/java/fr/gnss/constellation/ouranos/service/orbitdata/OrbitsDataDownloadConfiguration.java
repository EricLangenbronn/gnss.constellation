package fr.gnss.constellation.ouranos.service.orbitdata;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(OrbitsDataDownloadProperties.class)
public class OrbitsDataDownloadConfiguration {

	private final OrbitsDataDownloadProperties props;

	@Bean
	public String getDefaultDownloadSp3Directory() {

		String defaultDownloadSp3Directory = "";
		if (StringUtils.isNotBlank(props.getDirectory())) {
			defaultDownloadSp3Directory = props.getDirectory();
		} else {
			defaultDownloadSp3Directory = System.getProperty("user.home");
		}

		return defaultDownloadSp3Directory;
	}

}
