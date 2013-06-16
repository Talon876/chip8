package com.nolat.chip8;

/**
 * Implementation of the Chip-8 CPU internals. http://en.wikipedia.org/wiki/CHIP-8#Virtual_machine_description <br>
 * Internal representation of one and two byte values are all represented using java integers.
 */
public class CPU {

    /**
     * The current opcode (http://en.wikipedia.org/wiki/CHIP-8#Opcode_table)
     */
    private int opcode;

    /**
     * Array of 4096 bytes which serves as the machine's memory. <br>
     * 0x000 - 0x1FF: Chip8 interprete and font set. <br>
     * 0x050 - 0x0A0: Built in 4x5 pixel font set (0-F) <br>
     * 0x200 - 0xFFF: Program ROM and work RAM.
     */
    private int[] memory;

    /**
     * The 16, 8 bit general purpose CPU registers (V0, V1, ..., VF).
     */
    private int[] V;

    /**
     * Address/Index register used by some opcodes involving memory operations. 16 bits.
     */
    private int indexRegister;

    /**
     * Program Counter, 16 bits.
     */
    private int programCounter;

    /**
     * Graphics Memory containing state (on/off) information for 2048 pixels (64 x 32).
     */
    private boolean[] graphics;

    /**
     * Timer registers that count at 60Hz, when set above 0 they count down to 0.<br>
     * The system will make a sound when the sound timer reaches 0.
     */
    private int delayTimer; //8 bit
    private int soundTimer;

    /**
     * Stack used in the interpreter to remember the current location before performing a jump. <br>
     * The stack pointer is used to remember which level of the stack is used. 16 bits, 16 levels.
     */
    private int[] stack;
    private int stackPointer;

    /**
     * Array of 16 keys (0-F) arranged in a 4x4 grid, storing their current state.
     */
    private boolean[] keys = new boolean[16];

    public CPU() {
        initialize();
    }

    /**
     * Resets all CPU memory values and loads essential data in to memory.
     */
    private void initialize() {
        memory = new int[4096]; //4096 bytes of memory
        V = new int[16]; //16 general purpose registers
        graphics = new boolean[128 * 64]; //128 x 64 resolution screen
        stack = new int[16]; //16 level stack
        keys = new boolean[16]; //16 keys

        programCounter = 0x200; //set program counter to 200
        opcode = 0;
        indexRegister = 0;
        stackPointer = 0;

    }
}
