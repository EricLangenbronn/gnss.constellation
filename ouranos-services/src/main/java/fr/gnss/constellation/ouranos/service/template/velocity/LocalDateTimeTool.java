package fr.gnss.constellation.ouranos.service.template.velocity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.velocity.tools.generic.FormatConfig;

public class LocalDateTimeTool extends FormatConfig {

	public LocalDateTimeTool() {
	}

	public String format(LocalDateTime dateTime, String dateFormat) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		String formattedDateTime = dateTime.format(formatter);

		return formattedDateTime;
	}
}