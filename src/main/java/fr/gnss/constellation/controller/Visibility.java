package fr.gnss.constellation.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import fr.gnss.constellation.Exception.BusinessException;
import fr.gnss.constellation.librairy.almanach.sp3.PositionAndClockRecord;
import fr.gnss.constellation.librairy.almanach.sp3.Sp3FileReader;
import fr.gnss.constellation.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.service.TraitementPositions;

public class Visibility implements Initializable {

	@FXML
	private BarChart<String, Integer> barGraphe;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
/*
		try {
			String fileName = getClass().getResource("/Sp3File/igs17720.sp3")
					.getFile();
			Sp3FileReader sp3FileParser = new Sp3FileReader(fileName);
			GeodeticCoordinate gStation = new GeodeticCoordinate(
					Math.toRadians(38.889139), Math.toRadians(-77.049),
					Math.toRadians(130.049));

			List<Entry<LocalDateTime, List<PositionAndClockRecord>>> visibleSatelite = new ArrayList<>();
			while (true) {
				try {
					visibleSatelite.addAll(TraitementPositions.getSateliteVisble(
							sp3FileParser, gStation));
				} catch (BusinessException e) {
					break;
				}
			}

			XYChart.Series series1 = new XYChart.Series();
			for (Entry<LocalDateTime, List<PositionAndClockRecord>> e: visibleSatelite) {
				String date = e.getKey().format(DateTimeFormatter.ofPattern("HH:mm",new Locale("fr")));
				
				series1.getData().add(new XYChart.Data(date, e.getValue().size()));
			}

			barGraphe.getData().addAll(series1);

		} catch (Exception e) {
			e.printStackTrace();
		}
*/
	}

}