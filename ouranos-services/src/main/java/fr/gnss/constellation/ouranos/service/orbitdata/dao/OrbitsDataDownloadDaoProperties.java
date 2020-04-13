package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "products.access")
public class OrbitsDataDownloadDaoProperties {

	private final String ftpServerName;
	private final String epochDirectory;

}
