package fr.gnss.constellation.ouranos.service.template.velocity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.apache.velocity.tools.generic.FormatConfig;

public class SatellitePositionTool extends FormatConfig {

	public SatellitePositionTool() {
	}

	public String format(Double value) {

		DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
		decimalFormatSymbols.setDecimalSeparator('.');
		DecimalFormat decimalFormat = new DecimalFormat("#0.000");
		decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);

		return decimalFormat.format(value);
	}
}