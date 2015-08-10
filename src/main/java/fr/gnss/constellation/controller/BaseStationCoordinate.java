package fr.gnss.constellation.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class BaseStationCoordinate implements Initializable {

	@FXML
	TextField txtLongitude;

	@FXML
	TextField txtLatitude;

	@FXML
	TextField txtAltitude;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public double getLongitude() {
		return Double.parseDouble(txtLongitude.getText());
	}

	public double getLatitude() {
		return Double.parseDouble(txtLatitude.getText());
	}

	public double getAltitude() {
		return Double.parseDouble(txtAltitude.getText());
	}

}
