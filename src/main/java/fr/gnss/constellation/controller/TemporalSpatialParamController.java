package fr.gnss.constellation.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import fr.gnss.constellation.Exception.BusinessException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TemporalSpatialParamController extends VBox implements Initializable {

	@FXML
	TextField txtSTYears;

	@FXML
	ComboBox<String> cbSTMonth;

	@FXML
	ComboBox<String> cbSTDay;

	@FXML
	ComboBox<String> cbSTHours;

	@FXML
	ComboBox<String> cbSTMinutes;

	@FXML
	ComboBox<String> cbSTSecondes;

	@FXML
	ComboBox<String> cbDDays;

	@FXML
	ComboBox<String> cbDHours;

	@FXML
	ComboBox<String> cbDMinutes;

	@FXML
	ComboBox<String> cbDSeconds;

	@FXML
	TextField txtEM;

	private ObservableList<String> cbSTMonthData = FXCollections.observableArrayList();

	private ObservableList<String> cbSTDayData = FXCollections.observableArrayList();

	private ObservableList<String> cbSTHoursData = FXCollections.observableArrayList();

	private ObservableList<String> cbSTMinutesData = FXCollections.observableArrayList();

	private ObservableList<String> cbSTSecondesData = FXCollections.observableArrayList();

	private ObservableList<String> cbDDaysData = FXCollections.observableArrayList();

	private ObservableList<String> cbDHoursData = FXCollections.observableArrayList();

	private ObservableList<String> cbDMinutesData = FXCollections.observableArrayList();

	private ObservableList<String> cbDSecondsData = FXCollections.observableArrayList();

	public TemporalSpatialParamController() {
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/gnss/constellation/gui/fx/TemporalSpatialParam.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public void loadData() {
		String[] MonthData = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		String[] HoursData = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23" };
		String[] STDayData = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		String[] MinSecData = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
				"32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48",
				"49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60" };
		String[] DDaysData = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10" };

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

	public boolean isStartDateTimeSelected() {
		boolean res = false;

		int cbSTMonthIndex = cbSTMonth.getSelectionModel().getSelectedIndex();
		int cbSTDayIndex = cbSTDay.getSelectionModel().getSelectedIndex();
		int cbSTHoursIndex = cbSTHours.getSelectionModel().getSelectedIndex();
		int cbSTMinutesIndex = cbSTMinutes.getSelectionModel().getSelectedIndex();
		int cbSTSecondesIndex = cbSTSecondes.getSelectionModel().getSelectedIndex();
		if ((cbSTMonthIndex > -1) && (cbSTDayIndex > -1) && (cbSTHoursIndex > -1) && (cbSTMinutesIndex > -1)
				&& (cbSTSecondesIndex > -1)) {
			res = true;
		}

		return res;
	}

	public boolean isEndDateTimeSelected() {
		boolean res = false;

		int cbDDaysIndex = cbDDays.getSelectionModel().getSelectedIndex();
		int cbDHoursIndex = cbDHours.getSelectionModel().getSelectedIndex();
		int cbDMinutesIndex = cbDMinutes.getSelectionModel().getSelectedIndex();
		int cbDSecondsIndex = cbDSeconds.getSelectionModel().getSelectedIndex();
		if ((cbDDaysIndex > -1) && (cbDHoursIndex > -1) && (cbDMinutesIndex > -1) && (cbDSecondsIndex > -1)) {
			res = true;
		}

		return res;
	}

	public LocalDateTime getStartDateTimeMeasurment() throws BusinessException {

		LocalDateTime startDateTimeMeasurement = null;
		if (isStartDateTimeSelected()) {
			String dateTime = txtSTYears.getText() + "-" + cbSTMonth.getSelectionModel().getSelectedItem() + "-"
					+ cbSTDay.getSelectionModel().getSelectedItem() + "T"
					+ cbSTHours.getSelectionModel().getSelectedItem() + ":"
					+ cbSTMinutes.getSelectionModel().getSelectedItem() + ":"
					+ cbSTSecondes.getSelectionModel().getSelectedItem();
			try {
				startDateTimeMeasurement = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
			} catch (DateTimeParseException e) {
				String message = "Impossible de parser la date. [dateTime=" + dateTime + "]";
				throw new BusinessException(message, e);
			}
		} else {
			String message = "Veuillez selectionner tous les champs de la date de début de mesure";
			throw new BusinessException(message);
		}

		return startDateTimeMeasurement;
	}

	public LocalDateTime getEndDateTimeMeasurment() throws BusinessException {

		LocalDateTime endDateTimeMeasurment = null;
		if (isEndDateTimeSelected()) {
			LocalDateTime startDateTimeMeasurement = this.getStartDateTimeMeasurment();
			endDateTimeMeasurment = startDateTimeMeasurement
					.plusDays(Long.parseLong(cbDDays.getSelectionModel().getSelectedItem()))
					.plusHours(Long.parseLong(cbDHours.getSelectionModel().getSelectedItem()))
					.plusMinutes(Long.parseLong(cbDMinutes.getSelectionModel().getSelectedItem()))
					.plusSeconds(Long.parseLong(cbDSeconds.getSelectionModel().getSelectedItem()));
		} else {
			String message = "Veuillez selectionner tous les champs de la date de fin de mesure";
			throw new BusinessException(message);
		}

		return endDateTimeMeasurment;
	}
}
