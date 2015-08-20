package fr.gnss.constellation.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import fr.gnss.constellation.Exception.BusinessException;
import fr.gnss.constellation.librairy.coordinate.GeodeticCoordinate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ParametersController extends VBox implements Initializable {

	@FXML
	TemporalSpatialParamController temporalSpacialParam;

	@FXML
	BaseStationCoordinateController baseStationCoordinate;

	public ParametersController() {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	public LocalDateTime getStartDateTimeMeasurment() throws BusinessException {
		return temporalSpacialParam.getStartDateTimeMeasurment();
	}
	
	public LocalDateTime getEndDateTimeMeasurment() throws BusinessException {
		return temporalSpacialParam.getEndDateTimeMeasurment();
	}
	
	public GeodeticCoordinate getBaseCoordinate() throws BusinessException {
		return baseStationCoordinate.getBaseCoordinate();
	}
}
