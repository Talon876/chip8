package com.nolat.chip8;

/**
 * Class responsible for emulating the CPU.
 */
public class Emulator {

    private CPU cpu;
    private IScreen screen;

    /**
     * 
     * @param cpu
     *            the cpu to be emulated.
     * @param screen
     *            the screen to draw to.
     */
    public Emulator(CPU cpu, IScreen screen) {
        this.cpu = cpu;
        this.screen = screen;
    }

    public void start() {
        screen.clear();

        System.out.println("Starting emulation cycle.");

        while (true) {
            //complete one cycle
            emulateCycle();

            //update screen

            //store key state
        }
    }

    private void emulateCycle() {
        //fetch opcode
        int opcode = cpu.getByte(cpu.getProgramCounter()) << 8 | cpu.getByte(cpu.getProgramCounter() + 1);
        int[] nibbles = new int[4];
        nibbles[0] = (opcode & 0xf000) >> 12;
        nibbles[1] = (opcode & 0x0f00) >> 8;
        nibbles[2] = (opcode & 0x00f0) >> 4;
        nibbles[3] = (opcode & 0x000f) >> 0;

        /* decode opcode
         * NNN: address
         * NN: 8 bit constant
         * N: 4 bit constant
         * X and Y: 4 bit register identifier
         */
        switch (nibbles[0]) {
        case 0x0: //{0NNN, 00E0, 00EE}
            break;
        case 0x1: //1NNN: Jumps to address NNN
            break;
        case 0x2: //2NNN: Calls subroutine NNN
            break;
        case 0x3: //3XNN: Skips the next instruction if VX equals NN
            break;
        case 0x4: //4XNN: Skips the next instruction if VX doesn't equal NN
            break;
        case 0x5: //5XY0: Skips the next instruction if VX equals VY
            break;
        case 0x6: //6XNN: Sets VX to NN
            break;
        case 0x7: //7XNN: Adds NN to VX
            break;
        case 0x8: //{8XY0, 8XY1, 8XY2, 8XY3, 8XY4, 8XY5, 8XY6, 8XY7, 8XYE
            break;
        case 0x9: //9XY0: Skips the next instruction if VX doesn't equal VY
            break;
        case 0xa: //ANNN: Sets I to the address NNN
            break;
        case 0xb: //BNNN: Jumps to the address NNN plus V0
            break;
        case 0xc: //CXNN: Sets VX to a random number and NN
            break;
        case 0xd: //DXYN: Draws a sprite at coordinate (VX, VY) that has a width of 8 pxiels and a height of N pixels
            break;
        case 0xe: //{EX9E, EXA1}
            break;
        case 0xf: //{FX07, FX0A, FX15, FX18, FX1E, FX29, FX33, FX55, FX65}
            break;
        }

        //execute opcode

        //update timers
    }
}
