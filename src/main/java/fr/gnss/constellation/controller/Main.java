package fr.gnss.constellation.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class Main implements Initializable {

	@FXML
	Parameters parameters;

	@FXML
	Visibility visibility;
	
	@FXML
	Label userInfo;
	
	@FXML
	ProgressBar progressBar;

	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
