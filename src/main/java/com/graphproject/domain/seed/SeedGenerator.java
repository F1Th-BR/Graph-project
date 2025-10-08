package com.graphproject.domain.seed;

import java.time.Instant;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */


/**
 * Singleton class responsible for generating and managing seeds
 * used for creating graph designs.
 * 
 * <p>Credits to
 * <ul>
 *   <li>hytalo-bassi: https://github.com/hytalo-bassi</li>
 *   <li>Cassiano2003: https://github.com/Cassiano2003</li>
 * </ul>
 * for their help, especially with the generateTimeSeed method.
 */
public class SeedGenerator {

    /* ===== ATTRIBUTES ===== */

    /** Current seed value */
    private Long seed;

    /* ===== STATIC ATTRIBUTES ===== */

    /** Singleton instance of SeedGenerator */
    private static SeedGenerator instance;

    /* ===== CONSTRUCTORS ===== */

    /**
     * Private constructor to enforce singleton pattern.
     * Generates an initial seed upon creation.
     */
    private SeedGenerator() {
        generateSeed();
    }

    /* ===== METHODS ===== */

    /**
     * Returns the singleton instance of SeedGenerator.
     * Creates a new instance if it does not yet exist.
     * 
     * @return Singleton SeedGenerator instance
     */
    public static SeedGenerator getInstance() {
        if (instance == null) {
            instance = new SeedGenerator();
        }
        return instance;
    }

    /**
     * Generates a seed based on the current system time.
     * Combines epoch seconds and nanoseconds with bitwise operations
     * to produce a pseudo-random long value.
     * 
     * @return A time-based generated seed
     */
    private long generateTimeSeed() {
        Instant now = Instant.now();

        long num1 = now.getEpochSecond() ^ (now.getNano() >>> 16);
        long num2 = now.getNano() ^ (now.getEpochSecond() >>> 32);

        // 175891635050972156(16 X 32) -> test seed (K7 graph)
        String num = String.valueOf(num1) + String.valueOf(num2);

        // Uncomment for debugging
        // System.out.println(Long.parseLong(num));

        return Long.parseLong(num);
    }

    /**
     * Generates and sets a new seed using the time-based generator.
     */
    public void generateSeed() {
        setSeed(generateTimeSeed());
    }

    /**
     * Returns the current seed value.
     * 
     * @return The seed as a Long
     */
    public long getSeed() {
        return seed;
    }

    /**
     * Sets the seed value.
     * 
     * @param seed The new seed to set
     */
    public void setSeed(long seed) {
        this.seed = seed;
    }

} // SeedGenerator
