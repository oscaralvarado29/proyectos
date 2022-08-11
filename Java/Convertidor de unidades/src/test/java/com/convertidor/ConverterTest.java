package com.convertidor;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.provider.NullEnum;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;

import static org.junit.Assert.*;

import org.junit.Before;

public class ConverterTest {
    public static Converter converter;
    String binary = "";
    int decimal;

    @Before
    public void setupClass() {
        converter = new Converter();
    }

    @Test
    public void convertDecimalToBinary() {
        binary = converter.convertDecimalToBinary(13);
        assertEquals("1101", binary);

    }

    @Test
    public void convertBinaryToDecimal() {
        decimal = converter.convertBinaryToDecimal("1101");
        assertEquals(13, decimal);
    }
}
