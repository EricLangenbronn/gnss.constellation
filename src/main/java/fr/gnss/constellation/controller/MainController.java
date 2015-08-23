package fr.gnss.constellation.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import fr.gnss.constellation.Exception.BusinessException;
import fr.gnss.constellation.Exception.TechnicalException;
import fr.gnss.constellation.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.librairy.almanach.sp3.Sp3FileReader;
import fr.gnss.constellation.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.service.ConfigurationService;
import fr.gnss.constellation.service.ExecutionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class MainController implements Initializable {

	@FXML
	ParametersController parameters;

	@FXML
	VisibilityController visibility;

	@FXML
	Label userInfo;

	@FXML
	Button btnExecute;

	@FXML
	ProgressBar progressBar;

	private ConfigurationService configService;

	public void initialize(URL arg0, ResourceBundle arg1) {
		configService = new ConfigurationService();
	}

	public void setUserInfo(String message) {
		userInfo.setText(message);
	}

	public void setClearUserInfo() {
		userInfo.setText("");
	}

	@FXML
	public void handleExecute(ActionEvent event) {
		setUserInfo("Traitement en cours.");
		try {
			LocalDateTime startMeasurment = parameters.getStartDateTimeMeasurment();
			LocalDateTime endMeasurment = parameters.getEndDateTimeMeasurment();
			GeodeticCoordinate basePosition = parameters.getBaseCoordinate();

			GeodeticCoordinate gStation = new GeodeticCoordinate(Math.toRadians(38.889139), Math.toRadians(-77.049),
					Math.toRadians(130.049));

			String fileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
			Sp3FileReader sp3FileParser = new Sp3FileReader(fileName);
			List<Entry<LocalDateTime, List<Satelite>>> visibleSatelite = new ArrayList<>();
			LocalDateTime start = LocalDateTime.parse("2013-12-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
			LocalDateTime end = LocalDateTime.parse("2013-12-22T14:00", DateTimeFormatter.ISO_DATE_TIME);

			visibleSatelite = configService.getSateliteVisiblePeriod(start, end, sp3FileParser, gStation);

			visibility.cleanSeries();
			visibility.setSeries(visibleSatelite);

		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (TechnicalException e) {
			e.printStackTrace();
		}
		setUserInfo("Fin du traitement.");
	}
}
