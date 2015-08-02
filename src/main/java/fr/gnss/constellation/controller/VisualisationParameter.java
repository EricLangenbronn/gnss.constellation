package fr.gnss.constellation.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class VisualisationParameter implements Initializable {

	@FXML
	DatePicker startTime;

	@FXML
	TextField elevationMark;

	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
