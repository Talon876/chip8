package com.nolat.chip8;

public class Chip8 {

    private CPU cpu;

    public Chip8() {
        ROM maze = new ROM("roms/maze.ch8");

        cpu = new CPU();
        cpu.loadRom(maze);

        ConsoleScreen screen = new ConsoleScreen();

        Emulator emulator = new Emulator(cpu, screen);
        emulator.start();
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
