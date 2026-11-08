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
import java.util.Locale;
import java.util.ResourceBundle;

public class TemperatureRecordsSection extends VBox {
	public TemperatureRecordsSection(ResourceBundle rs, Locale locale) {
		setSpacing(8);

		String langCode = locale.getLanguage();
		TemperatureRecordController controller = new TemperatureRecordController();
		Label title = new Label(rs.getString("temperatureRecords"));
		ComboBox<String> conversionSelect = new ComboBox<>();
		Label descriptionLabel = new Label();
		TextField temperatureField = new TextField();
		temperatureField.setPromptText(rs.getString("enterTemperature"));
		Button convertButton = new Button(rs.getString("convert"));
		Label messageLabel = new Label();
		messageLabel.setVisible(false);
		messageLabel.setManaged(false);
		TableView<TemperatureRecord> table = new TableView<>();
		List<TemperatureType> tempTypes = new ArrayList<>();

		TableColumn<TemperatureRecord, String> typeColumn = new TableColumn<>(rs.getString("type"));
		typeColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTempType().getName()));
		TableColumn<TemperatureRecord, String> fromColumn = new TableColumn<>(rs.getString("from"));
		fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));
		TableColumn<TemperatureRecord, String> toColumn = new TableColumn<>(rs.getString("to"));
		toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));
		table.getColumns().addAll(typeColumn, fromColumn, toColumn);
		table.setPrefHeight(150);

		loadTypes(conversionSelect, descriptionLabel, tempTypes, controller, langCode, messageLabel, rs);
		conversionSelect.setOnAction(event -> showDescription(conversionSelect, descriptionLabel, tempTypes));
		convertButton.setOnAction(event -> convert(conversionSelect, tempTypes, temperatureField, table, controller, langCode, messageLabel, rs));
		load(table, controller, langCode, messageLabel, rs);
		getChildren().addAll(title, conversionSelect, descriptionLabel, temperatureField, convertButton, messageLabel, table);
	}

	private void loadTypes(ComboBox<String> select, Label description, List<TemperatureType> types,
			TemperatureRecordController controller, String langCode, Label message, ResourceBundle rs) {
		try {
			types.addAll(controller.getTypes(langCode));
			for (TemperatureType type : types) {
				select.getItems().add(type.getName());
			}
			select.getSelectionModel().selectFirst();
			showDescription(select, description, types);
		} catch (SQLException e) {
			showMessage(message, rs.getString("databaseError"));
		}
	}

	private void showDescription(ComboBox<String> select, Label description, List<TemperatureType> types) {
		int index = select.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			description.setText(types.get(index).getDescription());
		}
	}

	private void convert(ComboBox<String> select, List<TemperatureType> types, TextField input,
			TableView<TemperatureRecord> table, TemperatureRecordController controller, String langCode,
			Label message, ResourceBundle rs) {
		try {
			int index = select.getSelectionModel().getSelectedIndex();
			controller.add(types.get(index), input.getText(), langCode);
			table.getItems().setAll(controller.getAll(langCode));
			input.clear();
			clearMessage(message);
		} catch (NumberFormatException | IndexOutOfBoundsException | SQLException e) {
			showMessage(message, rs.getString("temperatureError"));
		}
	}

	private void load(TableView<TemperatureRecord> table, TemperatureRecordController controller,
			String langCode, Label message, ResourceBundle rs) {
		try {
			table.getItems().setAll(controller.getAll(langCode));
		} catch (SQLException e) {
			showMessage(message, rs.getString("databaseError"));
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
