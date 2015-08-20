package fr.gnss.constellation.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fr.gnss.constellation.Exception.BusinessException;
import fr.gnss.constellation.librairy.coordinate.GeodeticCoordinate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class BaseStationCoordinateController extends VBox implements Initializable {

	@FXML
	TextField txtLongitude;

	@FXML
	TextField txtLatitude;

	@FXML
	TextField txtAltitude;

	public BaseStationCoordinateController() {
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/gnss/constellation/gui/fx/BaseStationCoordinate.fxml"));
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

	private double getLongitude() throws BusinessException {

		try {
			return Double.parseDouble(txtLongitude.getText());
		} catch (NumberFormatException e) {
			String message = "";
			throw new BusinessException(message, e);
		}
	}

	private double getLatitude() throws BusinessException {
		try {
			return Double.parseDouble(txtLatitude.getText());
		} catch (NumberFormatException e) {
			String message = "";
			throw new BusinessException(message, e);
		}
	}

	private double getAltitude() throws BusinessException {
		try {
			return Double.parseDouble(txtAltitude.getText());
		} catch (NumberFormatException e) {
			String message = "";
			throw new BusinessException(message, e);
		}
	}

	public GeodeticCoordinate getBaseCoordinate() throws BusinessException {
		double latitude = Math.toRadians(this.getLatitude());
		double longitude = Math.toRadians(this.getLongitude());
		double altitude = this.getAltitude();

		GeodeticCoordinate base = new GeodeticCoordinate(latitude, longitude, altitude);

		return base;
	}

}
