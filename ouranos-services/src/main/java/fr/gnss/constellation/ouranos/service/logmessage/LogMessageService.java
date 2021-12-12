package fr.gnss.constellation.ouranos.service.logmessage;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogMessageService implements ILogMessageService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogMessageService.class);

    // -------------------- Services --------------------

    private final Environment environment;

    // -------------------- Methodes de l'interface --------------------

    @Override
    public String getDefautErrorMessage(String code) {
        return environment.getProperty(code);
    }
}
