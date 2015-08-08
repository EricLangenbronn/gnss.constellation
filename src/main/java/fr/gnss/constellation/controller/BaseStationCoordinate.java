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
		// TODO Auto-generated method stub

	}
}
