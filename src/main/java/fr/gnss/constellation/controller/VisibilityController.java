package fr.gnss.constellation.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import fr.gnss.constellation.librairy.almanach.sp3.Satelite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class VisibilityController extends VBox implements Initializable {

	@FXML
	private BarChart<String, Integer> barGraphe;

	public VisibilityController() {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/gnss/constellation/gui/fx/Visibility.fxml"));
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

	public void setSeries(List<Entry<LocalDateTime, List<Satelite>>> visibleSatelite) {
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		for (Entry<LocalDateTime, List<Satelite>> e : visibleSatelite) {
			String date = e.getKey().format(DateTimeFormatter.ofPattern("HH:mm", new Locale("fr")));

			series.getData().add(new XYChart.Data<String, Integer>(date, e.getValue().size()));
		}

		barGraphe.getData().add(series);
	}

	public void cleanSeries() {
		this.barGraphe.getData().clear();
	}

}