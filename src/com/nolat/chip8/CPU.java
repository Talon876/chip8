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
     * 0x000 - 0x1FF: Chip8 interpreter and font set. <br>
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

        //reset registers
        for (int i = 0; i < 0xF; i++) {
            V[i] = 0;
        }

        programCounter = 0x200; //set program counter to 200
        opcode = 0;
        indexRegister = 0;
        stackPointer = 0;
        delayTimer = 0;
        soundTimer = 0;

    }

    /**
     * 
     * @return the array of integers representing the memory.
     */
    public int[] getMemory() {
        return memory.clone();
    }

    /**
     * Gets the value in the specified register
     * 
     * @param v
     *            the register to get the value of (0-F).
     * @return the value in the register
     */
    public int getRegister(int v) {
        if (v < 0 || v > 15) {
            throw new IllegalArgumentException("Register must be in the range 0 through 15, but was " + v);
        }
        return V[v];
    }

    public int getIndexRegister() {
        return indexRegister;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    /**
     * Load byte from memory locatoin
     * 
     * @param l
     *            The memory address to load the byte from
     * @return the byte at address l
     */
    public int getByte(int l) {
        if (l < 0 || l > memory.length) {
            throw new IllegalArgumentException("Memory location must be between 0 and " + memory.length + " but was: "
                    + l);
        }
        return memory[l];
    }

    public int getOpCode() {
        return opcode;
    }

    public int getSoundTimer() {
        return soundTimer;
    }

    public int getDelayTimer() {
        return delayTimer;
    }

    /**
     * Sets specified register to specified value.
     * 
     * @param reg
     *            the register to set the value of (0-F)
     * @param value
     *            the 8 bit value to set the register to.
     */
    public void setRegister(int reg, int value) {
        if (reg < 0 || reg > 15) {
            throw new IllegalArgumentException("Register must be in the range 0 through 15, but was " + reg);
        }
        V[reg] = value & 0xFF; //AND with 0xFF to ensure only 8 bit values are inserted.
    }

    /**
     * Sets the index register to a value.
     * 
     * @param value
     *            The 16 bit value to set the index register to.
     */
    public void setIndexRegister(int value) {
        indexRegister = value & 0xFFFF; //AND with 0xFFFF to ensure only 16 bit values are inserted.
    }

    /**
     * Sets the program counter to a value.
     * 
     * @param value
     *            The 16 bit value to set the program counter to.
     */
    public void setProgramCounter(int value) {
        programCounter = value & 0xFFFF; //AND with 0xFFFF to ensure only 16 bit values are inserted.
    }

    /**
     * Sets the byte at memory location l to value b.
     * 
     * @param l
     *            the memory location to update
     * @param b
     *            the value the memory location should be set to
     */
    public void setByte(int l, int b) {
        if (l < 0 || l > memory.length) {
            throw new IllegalArgumentException("Memory location must be between 0 and " + memory.length + " but was: "
                    + l);
        }
        memory[l] = b & 0xFF; //AND with 0xFF to ensure only 8 bit values are inserted.
    }

    /**
     * Loads a ROM in to memory at offset 0x200.
     * 
     * @param rom
     *            the rom to load.
     */
    public void loadRom(IRom rom) {
        for (int i = 0; i < rom.getSize(); i++) {
            memory[0x200 + i] = rom.getBytes()[i];
        }
    }

    /**
     * Debug method. Dumps memory contents to console.
     */
    private void dumpMemory() {
        for (int b : getMemory()) {
            Chip8.printValue(b);
        }
    }
}
