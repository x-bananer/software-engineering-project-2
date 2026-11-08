package com.sep.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureTypeDAOTest {
	@Test
	void usesEnglishForUnsupportedLanguage() {
		assertEquals("en", TemperatureTypeDAO.getLang("de"));
	}

	@Test
	void usesSelectedSupportedLanguage() {
		assertEquals("ja", TemperatureTypeDAO.getLang("ja"));
	}
}
