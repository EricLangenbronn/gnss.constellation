package fr.gnss.constellation.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import fr.gnss.constellation.librairy.coordinate.GeodeticCoordinate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Parameters extends VBox implements Initializable {

	@FXML
	TemporalSpatialParam temporalSpacialParam;

	@FXML
	BaseStationCoordinate baseStationCoordinate;

	@FXML
	Button btnExecuter;

	public Parameters() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"/fr/gnss/constellation/gui/fx/Parameters.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@FXML
	public void handleExecute(ActionEvent e) {

		System.out.println("execute");
		try {

			LocalDateTime startDateTimeMeasure = temporalSpacialParam.getStartDateTimeMeasurment();
			LocalDateTime endDateTimeMeasure = temporalSpacialParam.getEndDateTimeMeasurment();

			GeodeticCoordinate base = baseStationCoordinate.getBaseCoordinate();

			System.out.println(startDateTimeMeasure);
			System.out.println(endDateTimeMeasure);
			System.out.println(base);



		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
