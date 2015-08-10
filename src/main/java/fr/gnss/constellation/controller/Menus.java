package fr.gnss.constellation.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;

public class Menus implements Initializable {

	@FXML
	private MenuBar menuBar;

	@FXML
	private void handleCloseApp(ActionEvent event) {
		Platform.exit();
	}
	
	@FXML
	private void handleVisibility(ActionEvent event) {
	}
	
	@FXML
	private void handlePlot(ActionEvent event) {
	}
	
	public void initialize(URL location, ResourceBundle resources) {
	}

}
