package com.nolat.chip8;

public class ConsoleScreen implements IScreen {

    @Override
    public void draw() {
        System.out.println("Drawing");
    }

    @Override
    public void clear() {
        System.out.println("Clearing screen");
    }
}
