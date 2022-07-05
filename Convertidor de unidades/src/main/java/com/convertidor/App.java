package com.convertidor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Converter converter = new Converter();
        //String binary = converter.convertDecimalToBinary(13)  ;
        int decimal = converter.convertBinaryToDecimal("1101");
    }
}
