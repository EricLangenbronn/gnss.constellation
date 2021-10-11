package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@ConstructorBinding
@Getter
@Validated
@ConfigurationProperties(prefix = "products.access")
public class OrbitsDataDownloadDaoProperties {

	private final String ftpServerName;
	private final String epochDirectory;

}
