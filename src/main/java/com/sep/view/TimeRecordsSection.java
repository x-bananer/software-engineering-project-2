package com.sep.view;

import com.sep.controller.TimeRecordController;
import com.sep.model.TimeRecord;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class TimeRecordsSection extends VBox {

	public TimeRecordsSection() {
		setSpacing(8);

		Label title = new Label("Time Records");

		TextField speedField = new TextField();
		speedField.setPromptText("Speed (km/h)");

		TextField distanceField = new TextField();
		distanceField.setPromptText("Distance (km)");

		Button saveButton = new Button("Save");

		TableView<TimeRecord> table = new TableView<>();

		TableColumn<TimeRecord, String> speedColumn = new TableColumn<>("Speed (km/h)");
		speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));

		TableColumn<TimeRecord, String> distanceColumn = new TableColumn<>("Distance (km)");
		distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));

		TableColumn<TimeRecord, String> timeColumn = new TableColumn<>("Time (h)");
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

		speedColumn.setPrefWidth(150);
		distanceColumn.setPrefWidth(150);
		timeColumn.setPrefWidth(150);
		table.setPrefHeight(150);

		table.getColumns().addAll(speedColumn, distanceColumn, timeColumn);

		TimeRecordController controller = new TimeRecordController();
		saveButton.setOnAction(event -> save(speedField, distanceField, table, controller));
		load(table, controller);

		getChildren().addAll(title, new HBox(8, speedField, distanceField), saveButton, table);
	}

	private void save(TextField speedField, TextField distanceField, TableView<TimeRecord> table, TimeRecordController controller) {
		try {
			controller.add(speedField.getText(), distanceField.getText());
			table.getItems().setAll(controller.getAll());
			speedField.clear();
			distanceField.clear();
		} catch (NumberFormatException | SQLException e) {
			System.out.println("Could not save time");
		}
	}

	private void load(TableView<TimeRecord> table, TimeRecordController controller) {
		try {
			table.getItems().setAll(controller.getAll());
		} catch (SQLException e) {
			System.out.println("Could not load time records");
		}
	}

}
