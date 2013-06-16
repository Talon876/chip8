package com.nolat.chip8;

public class Chip8 {

    private CPU cpu;

    public Chip8() {

    }

    public static void main(String[] args) {
        new Chip8();
    }

    /**
     * Prints a value in decimal, hex, and binary.
     * 
     * @param value
     *            the value to print.
     */
    public static void printValue(int value) {
        System.out.println("D: " + value + "; H: 0x" + Integer.toHexString(value) + "; B: 0b"
                + Integer.toBinaryString(value));
    }
}
