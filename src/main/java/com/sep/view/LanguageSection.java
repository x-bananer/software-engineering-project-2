package com.sep.view;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class LanguageSection extends HBox {
	public LanguageSection(ResourceBundle rs, Locale currentLocale, Consumer<Locale> onLanguageSelected) {
		setSpacing(8);

		ComboBox<String> languageSelect = new ComboBox<>();
		
		languageSelect.getItems().addAll("English", "Suomi", "Svenska", "日本語", "العربية");

		languageSelect.setValue(switch (currentLocale.getLanguage()) {
			case "fi" -> "Suomi";
			case "sv" -> "Svenska";
			case "ja" -> "日本語";
			case "ar" -> "العربية";
			default -> "English";
		});

		languageSelect.setOnAction(event -> {
			Locale locale;

			switch (languageSelect.getValue()) {
				case "Suomi":
					locale = new Locale("fi", "FI");
					break;
				case "Svenska":
					locale = new Locale("sv", "SE");
					break;
				case "日本語":
					locale = new Locale("ja", "JP");
					break;
				case "العربية":
					locale = new Locale("ar", "SA");
					break;
				default:
					locale = Locale.US;
			}

			onLanguageSelected.accept(locale);
		});

		getChildren().addAll(new Label(rs.getString("language")), languageSelect);
	}
}
