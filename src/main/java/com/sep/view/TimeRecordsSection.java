package com.sep.view;

import com.sep.controller.TimeRecordController;
import com.sep.model.TimeRecord;
import com.sep.model.TimeRecordType;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.Locale;
import java.util.List;
import java.util.ResourceBundle;

public class TimeRecordsSection extends VBox {
	public TimeRecordsSection(ResourceBundle rs, Locale locale) {
		setSpacing(8);
		TimeRecordController controller = new TimeRecordController();
		Label title = new Label(rs.getString("timeRecords"));
		Label messageLabel = new Label();
		messageLabel.setVisible(false);
		messageLabel.setManaged(false);
		List<TimeRecordType> types;

		try {
			types = controller.getTypes(locale.getLanguage());
		} catch (SQLException e) {
			showMessage(messageLabel, rs.getString("databaseError"));
			getChildren().addAll(title, messageLabel);
			return;
		}

		TimeRecordType speedType = types.get(0);
		TimeRecordType distanceType = types.get(1);
		TimeRecordType timeType = types.get(2);
		TextField speedField = new TextField();
		speedField.setPromptText(speedType.getFieldName() + " (" + speedType.getUnit() + ")");
		speedField.setPrefWidth(250);
		TextField distanceField = new TextField();
		distanceField.setPromptText(distanceType.getFieldName() + " (" + distanceType.getUnit() + ")");
		distanceField.setPrefWidth(250);
		Label speedDescription = new Label(speedType.getDescription());
		speedDescription.setPrefWidth(250);
		speedDescription.setWrapText(true);
		Label distanceDescription = new Label(distanceType.getDescription());
		distanceDescription.setPrefWidth(250);
		distanceDescription.setWrapText(true);
		Button saveButton = new Button(rs.getString("save"));
		TableView<TimeRecord> table = new TableView<>();
		VBox recordsBox = new VBox(2, messageLabel, table);
		recordsBox.setPadding(new Insets(0, 0, 16, 0));

		TableColumn<TimeRecord, String> speedColumn = new TableColumn<>(speedType.getFieldName());
		speedColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getSpeed() + " " + speedType.getUnit()));
		TableColumn<TimeRecord, String> distanceColumn = new TableColumn<>(distanceType.getFieldName());
		distanceColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDistance() + " " + distanceType.getUnit()));
		TableColumn<TimeRecord, String> timeColumn = new TableColumn<>(timeType.getFieldName());
		timeColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTime() + " " + timeType.getUnit()));
		table.getColumns().addAll(speedColumn, distanceColumn, timeColumn);
		table.setPrefHeight(150);

		saveButton.setOnAction(event -> save(speedField, distanceField, table, controller, messageLabel, rs));
		load(table, controller, messageLabel, rs);
		getChildren().addAll(
				title,
				new HBox(8, speedField, distanceField),
				new HBox(8, speedDescription, distanceDescription),
				saveButton,
				recordsBox);
	}

	private void save(TextField speedField, TextField distanceField, TableView<TimeRecord> table,
			TimeRecordController controller, Label messageLabel, ResourceBundle rs) {
		try {
			controller.add(speedField.getText(), distanceField.getText());
			table.getItems().setAll(controller.getAll());
			speedField.clear();
			distanceField.clear();
			clearMessage(messageLabel);
		} catch (NumberFormatException | SQLException e) {
			showMessage(messageLabel, rs.getString("timeError"));
		}
	}

	private void load(TableView<TimeRecord> table, TimeRecordController controller, Label messageLabel, ResourceBundle rs) {
		try {
			table.getItems().setAll(controller.getAll());
		} catch (SQLException e) {
			showMessage(messageLabel, rs.getString("databaseError"));
		}
	}

	private void showMessage(Label message, String text) {
		message.setText(text);
		message.setVisible(true);
		message.setManaged(true);
	}

	private void clearMessage(Label message) {
		message.setText("");
		message.setVisible(false);
		message.setManaged(false);
	}
}
