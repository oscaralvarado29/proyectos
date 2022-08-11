package com.convertidor;

public class Converter {
    int decimal;
    String binary = "";
    String octal;
    String hexadecimal;

    public String convertDecimalToBinary(int decimalNumber) {

        String binaryNumber = "";
        int residue;
        int position = 0;
        int number = decimalNumber;
        int quotient = 0;

        do {
            position++;
            quotient = number / 2;
            residue = (number % 2);
            binaryNumber += residue;
            number = quotient;
        } while (number > 0);
        for (int i = position - 1; i >= 0; i--) {
            binary +=binaryNumber.charAt(i);
        }
        return binary;
    }

    public int convertBinaryToDecimal(String binaryNumber) {
        int decimalNumber = 0;
        int tamano = binaryNumber.length();
        int valorByte = 0;


        for (int i = tamano - 1; i >= 0; i--) {
            valorByte = Integer.parseInt(String.valueOf(binaryNumber.charAt(Math.abs(i-3))));
            System.out.println(valorByte);
            decimalNumber += valorByte * Math.pow(2, i);
        }
        return decimalNumber;
    }
}
