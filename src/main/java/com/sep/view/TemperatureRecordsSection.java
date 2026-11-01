package com.sep.view;

import com.sep.controller.TemperatureRecordController;
import com.sep.model.TemperatureRecord;
import com.sep.model.TemperatureType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class TemperatureRecordsSection extends VBox {

	public TemperatureRecordsSection() {
		setSpacing(8);

		Label title = new Label("Temperature Records");

		ComboBox<TemperatureType> conversionSelect = new ComboBox<>();

		TextField temperatureField = new TextField();
		temperatureField.setPromptText("Enter temperature");

		Button convertButton = new Button("Convert");

		TableView<TemperatureRecord> table = new TableView<>();

		TableColumn<TemperatureRecord, String> typeColumn = new TableColumn<>("Type");
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

		TableColumn<TemperatureRecord, String> fromColumn = new TableColumn<>("From");
		fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));

		TableColumn<TemperatureRecord, String> toColumn = new TableColumn<>("To");
		toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));

		typeColumn.setPrefWidth(160);
		fromColumn.setPrefWidth(150);
		toColumn.setPrefWidth(150);
		table.setPrefHeight(150);

		table.getColumns().addAll(typeColumn, fromColumn, toColumn);

		TemperatureRecordController controller = new TemperatureRecordController();
		loadTypes(conversionSelect, controller);
		convertButton.setOnAction(event -> convert(conversionSelect, temperatureField, table, controller));
		load(table, controller);

		getChildren().addAll(title, conversionSelect, temperatureField, convertButton, table);
	}

	private void convert(ComboBox<TemperatureType> conversionSelect, TextField temperatureField, TableView<TemperatureRecord> table, TemperatureRecordController controller) {
		try {
			controller.add(conversionSelect.getValue(), temperatureField.getText());
			table.getItems().setAll(controller.getAll());
			temperatureField.clear();
		} catch (NumberFormatException | SQLException e) {
			System.out.println("Could not save temperature");
		}
	}

	private void loadTypes(ComboBox<TemperatureType> conversionSelect, TemperatureRecordController controller) {
		try {
			conversionSelect.getItems().setAll(controller.getTypes());
			conversionSelect.setValue(conversionSelect.getItems().get(0));
		} catch (SQLException e) {
			System.out.println("Could not load temperature types");
		}
	}

	private void load(TableView<TemperatureRecord> table, TemperatureRecordController controller) {
		try {
			table.getItems().setAll(controller.getAll());
		} catch (SQLException e) {
			System.out.println("Could not load temperature records");
		}
	}

}
