package fr.gnss.constellation.ouranos.persistence.sp3.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;


@ConfigurationProperties(prefix = "products.access")
@Validated
@AllArgsConstructor
@ConstructorBinding
@Getter
public class Sp3InputStreamRepositoryProperties {

    private final String ftpServerName;
    private final String epochDirectory;

}
