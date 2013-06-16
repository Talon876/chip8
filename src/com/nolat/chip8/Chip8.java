package com.nolat.chip8;

public class Chip8 {

    private CPU cpu;

    public Chip8() {
        cpu = new CPU();

        //        printValue(cpu.getRegister(0));
        //        cpu.setRegister(0, 38);
        //        printValue(cpu.getRegister(0));
        //        printValue(cpu.getProgramCounter());
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
