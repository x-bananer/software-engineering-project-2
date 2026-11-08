package com.sep;

import com.sep.view.TemperatureRecordsSection;
import com.sep.view.TimeRecordsSection;
import com.sep.view.LanguageSection;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		changeLocale(stage, Locale.US);
		stage.show();
	}

	private void changeLocale(Stage stage, Locale locale) {
		ResourceBundle rs = ResourceBundle.getBundle("i18n.MessagesBundle", locale);

		VBox layout = new VBox(
			16, 
			new LanguageSection(rs, locale, selectedLocale -> changeLocale(stage, selectedLocale)), 
			new TemperatureRecordsSection(rs, locale),
			new TimeRecordsSection(rs, locale)
		);

		layout.setPadding(new Insets(20));

		// if (locale.getLanguage().equals("ja")) {
		// 	layout.setStyle("-fx-font-family: 'Noto Sans CJK JP';");
		// }

		layout.setNodeOrientation(locale.getLanguage().equals("ar")
			? NodeOrientation.RIGHT_TO_LEFT
			: NodeOrientation.LEFT_TO_RIGHT);

		stage.setScene(new Scene(layout, 650, 600));
		stage.setTitle(rs.getString("title"));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
