package com.sep.view;

import com.sep.controller.TimeRecordController;
import com.sep.model.TimeRecord;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ResourceBundle;

public class TimeRecordsSection extends VBox {

	public TimeRecordsSection(ResourceBundle rs) {
		setSpacing(8);

		Label title = new Label(rs.getString("timeRecords"));

		TextField speedField = new TextField();
		speedField.setPromptText(rs.getString("speed"));

		TextField distanceField = new TextField();
		distanceField.setPromptText(rs.getString("distance"));

		Button saveButton = new Button(rs.getString("save"));
		Label messageLabel = new Label();

		TableView<TimeRecord> table = new TableView<>();

		TableColumn<TimeRecord, String> speedColumn = new TableColumn<>(rs.getString("speed"));
		speedColumn.setCellValueFactory(cell -> new SimpleStringProperty( cell.getValue().getSpeedValue() + " " + rs.getString("kilometersPerHour")));

		TableColumn<TimeRecord, String> distanceColumn = new TableColumn<>(rs.getString("distance"));
		distanceColumn.setCellValueFactory(cell -> new SimpleStringProperty( cell.getValue().getDistanceValue() + " " + rs.getString("kilometers")));

		TableColumn<TimeRecord, String> timeColumn = new TableColumn<>(rs.getString("time"));
		timeColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTimeValue() + " " + rs.getString("hours")));

		speedColumn.setPrefWidth(150);
		distanceColumn.setPrefWidth(150);
		timeColumn.setPrefWidth(150);
		table.setPrefHeight(150);

		table.getColumns().addAll(speedColumn, distanceColumn, timeColumn);

		TimeRecordController controller = new TimeRecordController();
		saveButton.setOnAction(event -> save(speedField, distanceField, table, controller, messageLabel, rs));
		load(table, controller, messageLabel, rs);

		getChildren().addAll(title, new HBox(8, speedField, distanceField), saveButton, messageLabel, table);
	}

	private void save(TextField speedField, TextField distanceField, TableView<TimeRecord> table, TimeRecordController controller, Label messageLabel, ResourceBundle rs) {
		try {
			controller.add(speedField.getText(), distanceField.getText());
			table.getItems().setAll(controller.getAll());
			speedField.clear();
			distanceField.clear();
			messageLabel.setText("");
		} catch (NumberFormatException | SQLException e) {
			messageLabel.setText(rs.getString("timeError"));
		}
	}

	private void load(TableView<TimeRecord> table, TimeRecordController controller, Label messageLabel, ResourceBundle rs) {
		try {
			table.getItems().setAll(controller.getAll());
		} catch (SQLException e) {
			messageLabel.setText(rs.getString("databaseError"));
		}
	}

}
