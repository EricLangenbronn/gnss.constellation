package fr.gnss.constellation.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import fr.gnss.constellation.Exception.BusinessException;

public class TemporalSpatialParam implements Initializable {

	@FXML
	TextField txtSTYears;

	@FXML
	ComboBox<Integer> cbSTMonth;

	@FXML
	ComboBox<Integer> cbSTDay;

	@FXML
	ComboBox<Integer> cbSTHours;

	@FXML
	ComboBox<Integer> cbSTMinutes;

	@FXML
	ComboBox<Integer> cbSTSecondes;

	@FXML
	ComboBox<Integer> cbDDays;

	@FXML
	ComboBox<Integer> cbDHours;

	@FXML
	ComboBox<Integer> cbDMinutes;

	@FXML
	ComboBox<Integer> cbDSeconds;

	@FXML
	TextField txtEM;

	private ObservableList<Integer> cbSTMonthData = FXCollections
			.observableArrayList();

	private ObservableList<Integer> cbSTDayData = FXCollections
			.observableArrayList();

	private ObservableList<Integer> cbSTHoursData = FXCollections
			.observableArrayList();

	private ObservableList<Integer> cbSTMinutesData = FXCollections
			.observableArrayList();

	private ObservableList<Integer> cbSTSecondesData = FXCollections
			.observableArrayList();

	private ObservableList<Integer> cbDDaysData = FXCollections
			.observableArrayList();

	private ObservableList<Integer> cbDHoursData = FXCollections
			.observableArrayList();

	private ObservableList<Integer> cbDMinutesData = FXCollections
			.observableArrayList();

	private ObservableList<Integer> cbDSecondsData = FXCollections
			.observableArrayList();

	public void loadData() {
		Integer[] MonthData = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		Integer[] HoursData = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
				14, 15, 16, 17, 18, 19, 20, 21, 22, 23 };
		Integer[] STDayData = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15,
				16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };
		Integer[] MinSecData = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15,
				16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
				32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
				48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60 };
		Integer[] DDaysData = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		cbSTMonthData.addAll(MonthData);
		cbSTDayData.addAll(STDayData);
		cbSTHoursData.addAll(HoursData);
		cbSTMinutesData.addAll(MinSecData);
		cbSTSecondesData.addAll(MinSecData);
		cbDDaysData.addAll(DDaysData);
		cbDHoursData.addAll(HoursData);
		cbDMinutesData.addAll(MinSecData);
		cbDSecondsData.addAll(MinSecData);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.loadData();

		cbSTMonth.setItems(cbSTMonthData);
		cbSTDay.setItems(cbSTDayData);
		cbSTHours.setItems(cbSTHoursData);
		cbSTMinutes.setItems(cbSTMinutesData);
		cbSTSecondes.setItems(cbSTSecondesData);
		cbDDays.setItems(cbDDaysData);
		cbDHours.setItems(cbDHoursData);
		cbDMinutes.setItems(cbDMinutesData);
		cbDSeconds.setItems(cbDSecondsData);
	}

	public LocalDateTime getStartDateTimeMeasurment() throws BusinessException {
		String dateTime = txtSTYears.getText() + "-"
				+ cbSTMonth.getSelectionModel() + "-"
				+ cbSTDay.getSelectionModel() + "T"
				+ cbSTHours.getSelectionModel() + ":"
				+ cbSTMinutes.getSelectionModel() + ":"
				+ cbSTSecondes.getSelectionModel();

		LocalDateTime startDateTimeMeasurement = null;
		try {
			startDateTimeMeasurement = LocalDateTime.parse(dateTime,
					DateTimeFormatter.ISO_DATE_TIME);
		} catch (DateTimeParseException e) {
			String message = "Impossible de parser la date. [dateTime="
					+ dateTime + "]";
			throw new BusinessException(message, e);
		}

		return startDateTimeMeasurement;
	}

	public LocalDateTime getPeriodMeasurment() throws BusinessException {

		LocalDateTime startDateTimeMeasurement = this
				.getStartDateTimeMeasurment();
		
		LocalDateTime endDateTimeMeasurment = startDateTimeMeasurement
				.plusDays(cbDDays.getValue()).plusHours(cbDHours.getValue())
				.plusMinutes(cbDMinutes.getValue())
				.plusSeconds(cbDSeconds.getValue());

		return endDateTimeMeasurment;
	}
}
