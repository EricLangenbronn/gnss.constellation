package fr.gnss.constellation.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import fr.gnss.constellation.librairy.coordinate.GeodeticCoordinate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class Parameters implements Initializable {

	@FXML
	AnchorPane temporalSpacialParam;

	@FXML
	AnchorPane baseStationCoordinate;

	@FXML
	public void handleExecute(ActionEvent e) {
		System.out.println("execute");
		try {
			LocalDateTime startDateTimeMeasure = ((TemporalSpatialParam) temporalSpacialParam)
					.getStartDateTimeMeasurment();
			LocalDateTime endDateTimeMeasure = ((TemporalSpatialParam) temporalSpacialParam)
					.getEndDateTimeMeasurment();

			double latitude = Math
					.toRadians(((BaseStationCoordinate) baseStationCoordinate)
							.getLatitude());
			double longitude = Math
					.toRadians(((BaseStationCoordinate) baseStationCoordinate)
							.getLongitude());
			double altitude = ((BaseStationCoordinate) baseStationCoordinate)
					.getAltitude();

			GeodeticCoordinate base = new GeodeticCoordinate(latitude,
					longitude, altitude);

			System.out.println(startDateTimeMeasure);
			System.out.println(endDateTimeMeasure);
			System.out.println(altitude);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
