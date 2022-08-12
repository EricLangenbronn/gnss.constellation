package fr.gnss.constellation.ouranos.persistence.sp3.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "sp3")
@Validated
@AllArgsConstructor
@ConstructorBinding
@Getter
public class Sp3FileRepositoryProperties {

    private final String directory;

}
