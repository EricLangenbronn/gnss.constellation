package fr.gnss.constellation.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;

public class Menus implements Initializable {

	@FXML
	private MenuBar menuBar;
	
	@FXML
	private void handleAboutAction(final ActionEvent event) {
		provideAboutFunctionality();
	}

	/**
	 * Perform functionality associated with "About" menu selection or CTRL-A.
	 */
	private void provideAboutFunctionality() {
		System.out.println("You clicked on About!");
	}

	public void initialize(URL location, ResourceBundle resources) {
		menuBar.setFocusTraversable(true);
	}

}
