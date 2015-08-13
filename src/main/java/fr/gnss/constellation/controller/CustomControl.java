package fr.gnss.constellation.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Eric on 13/08/2015.
 */
public class CustomControl extends VBox {

    @FXML
    Button btnButton;

    @FXML
    TextField txtChamp;

    public CustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fr/gnss/constellation/gui/fx/CustomControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void handleButton(ActionEvent action) {
        txtChamp.setText("clique");
    }

}
