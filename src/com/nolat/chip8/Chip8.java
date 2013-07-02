package com.nolat.chip8;

public class Chip8 {

    private CPU cpu;

    public Chip8() {
        cpu = new CPU();
        ROM maze = new ROM("roms/maze.ch8");
        //        for (int b : maze.getBytes()) {
        //            printValue(b);
        //        }
        cpu.loadRom(maze);
        for (int b : cpu.getMemory()) {
            printValue(b);
        }
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
        String dec = Integer.toString(value);
        String hex = "0x" + Integer.toHexString(value);
        String bin = "0b" + Integer.toBinaryString(value);
        System.out.println(String.format("0x%04X | %04d", value, value));
    }
}
