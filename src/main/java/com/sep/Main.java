package com.sep;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
    private static final TemperatureConverter converter = new TemperatureConverter();
    private static ResourceBundle rs;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

		Locale.setDefault(Locale.US);
		Locale locale = Locale.getDefault();

		System.out.println("0 - English, 1 - Suomi, 2 - Svenska, 3 - 日本語");

		int lang = scanner.nextInt();

		switch (lang) {
			case 1:
				locale = new Locale("fi", "FI");
				break;
			case 2:
				locale = new Locale("sv", "SE");
				break;
			case 3:
				locale = new Locale("ja", "JP");
				break;
			default:
				break;
		}

		rs = ResourceBundle.getBundle("i18n.MessagesBundle", locale);
		System.out.println(rs.getString("languageSelected"));

        while (true) {
			System.out.println("\n" + rs.getString("title"));
            System.out.println("1 - " + rs.getString("fahrenheitToCelsius"));
            System.out.println("2 - " + rs.getString("celsiusToFahrenheit"));
            System.out.println("0 - " + rs.getString("exit"));

            int choice = scanner.nextInt();

            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> {
					System.out.println(rs.getString("fahrenheitToCelsius"));
                    System.out.println(rs.getString("enterFahrenheit") + ":");

                    double fahrenheit = scanner.nextDouble();
                    double celsius = converter.fahrenheitToCelsius(fahrenheit);

                    System.out.println(rs.getString("celsius") + ": " + celsius);
					
                    checkIsExtreme(celsius);
                }
                case 2 -> {
					System.out.println(rs.getString("celsiusToFahrenheit"));
                   	System.out.println(rs.getString("enterCelsius") + ":");

                    double celsius = scanner.nextDouble();
					double fahrenheit = converter.celsiusToFahrenheit(celsius);
					
					System.out.println(rs.getString("fahrenheit") + ": " + fahrenheit);

                    checkIsExtreme(celsius);
                }
            }
        }
    }

    private static void checkIsExtreme(double celsius) {
        boolean isExtreme = converter.isExtremeTemperature(celsius);

        System.out.println(isExtreme ? rs.getString("extremeTemperature") : rs.getString("notExtremeTemperature"));
    }
}
