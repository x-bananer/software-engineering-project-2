package com.sep.view;

import com.sep.controller.TemperatureRecordController;
import com.sep.model.TemperatureRecord;
import com.sep.model.TemperatureType;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TemperatureRecordsSection extends VBox {

	public TemperatureRecordsSection(ResourceBundle rs) {
		setSpacing(8);

		Label title = new Label(rs.getString("temperatureRecords"));

		ComboBox<String> conversionSelect = new ComboBox<>();
		List<TemperatureType> temperatureTypes = new ArrayList<>();

		TextField temperatureField = new TextField();
		temperatureField.setPromptText(rs.getString("enterTemperature"));

		Button convertButton = new Button(rs.getString("convert"));
		Label messageLabel = new Label();

		TableView<TemperatureRecord> table = new TableView<>();

		TableColumn<TemperatureRecord, String> typeColumn = new TableColumn<>(rs.getString("type"));
		typeColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTemperatureType().getName()));

		TableColumn<TemperatureRecord, String> fromColumn = new TableColumn<>(rs.getString("from"));
		fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));

		TableColumn<TemperatureRecord, String> toColumn = new TableColumn<>(rs.getString("to"));
		toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));

		typeColumn.setPrefWidth(160);
		fromColumn.setPrefWidth(150);
		toColumn.setPrefWidth(150);
		table.setPrefHeight(150);

		table.getColumns().addAll(typeColumn, fromColumn, toColumn);

		TemperatureRecordController controller = new TemperatureRecordController();
		loadTypes(conversionSelect, temperatureTypes, controller, messageLabel, rs);
		convertButton.setOnAction(event -> convert(conversionSelect, temperatureTypes, temperatureField, table, controller, messageLabel, rs));
		load(table, controller, messageLabel, rs);

		getChildren().addAll(title, conversionSelect, temperatureField, convertButton, messageLabel, table);
	}

	private void convert(ComboBox<String> conversionSelect, List<TemperatureType> temperatureTypes, TextField temperatureField, TableView<TemperatureRecord> table, TemperatureRecordController controller, Label messageLabel, ResourceBundle rs) {
		try {
			int selectedIndex = conversionSelect.getSelectionModel().getSelectedIndex();
			controller.add(temperatureTypes.get(selectedIndex), temperatureField.getText());
			table.getItems().setAll(controller.getAll());
			temperatureField.clear();
			messageLabel.setText("");
		} catch (NumberFormatException | SQLException e) {
			messageLabel.setText(rs.getString("temperatureError"));
		}
	}

	private void loadTypes(ComboBox<String> conversionSelect, List<TemperatureType> temperatureTypes, TemperatureRecordController controller, Label messageLabel, ResourceBundle rs) {
		try {
			temperatureTypes.addAll(controller.getTypes());
			for (TemperatureType type : temperatureTypes) {
				conversionSelect.getItems().add(type.getName());
			}
			conversionSelect.getSelectionModel().selectFirst();
		} catch (SQLException e) {
			messageLabel.setText(rs.getString("databaseError"));
		}
	}

	private void load(TableView<TemperatureRecord> table, TemperatureRecordController controller, Label messageLabel, ResourceBundle rs) {
		try {
			table.getItems().setAll(controller.getAll());
		} catch (SQLException e) {
			messageLabel.setText(rs.getString("databaseError"));
		}
	}

}
