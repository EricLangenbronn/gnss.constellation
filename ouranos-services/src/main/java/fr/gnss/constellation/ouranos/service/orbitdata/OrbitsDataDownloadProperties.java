package fr.gnss.constellation.ouranos.service.orbitdata;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "download.sp3")
public class OrbitsDataDownloadProperties {

	private final String directory;

}
