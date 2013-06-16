package com.nolat.chip8;

/**
 * Represents a ROM that can be loaded in to the memory of the Chip8.
 */
public interface IRom {

    /**
     * 
     * @return array containing the bytes for this rom.
     */
    public int[] getBytes();

    /**
     * 
     * @return the size of the rom in bytes.
     */
    public int getSize();
}
