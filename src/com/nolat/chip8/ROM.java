package com.nolat.chip8;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * A ROM that is loaded from the filesystem.
 */
public class ROM implements IRom {

    private int[] rom;
    private String fileName;

    /**
     * Loads a ROM from the filesystem
     * 
     * @param fileName
     *            the path to the ROM
     */
    public ROM(String fileName) {
        //first 512 bytes are reserved for internal use so ROMs start at 0x200 and can't be more than 3584 bytes
        int[] trom = new int[3584];
        this.fileName = fileName;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            int nextByte;
            int i = 0;
            while ((nextByte = fis.read()) != -1) {
                trom[i++] = nextByte;
            }
            fis.close();

            //load actual number of bytes in to rom array
            rom = new int[i];
            for (int j = 0; j < i; j++) {
                rom[j] = trom[j];
            }
            System.out.println("Loaded " + rom.length + " bytes from " + fileName + ".");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
        }
    }

    /**
     * 
     * @return a copy of the array containing the bytes for this rom.
     */
    @Override
    public int[] getBytes() {
        return rom.clone();
    }

    /**
     * 
     * @return the size of the rom in bytes.
     */
    @Override
    public int getSize() {
        return rom.length;
    }

    public String getFileName() {
        return fileName;
    }
}
