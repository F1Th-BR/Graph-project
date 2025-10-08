package com.graphproject.domain.characters;
import java.awt.image.BufferedImage;
import java.util.*;

import com.graphproject.domain.graph.Vertex;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * Represents a personage with attributes such as name, age, alignment,
 * description, traits, and an associated image.
 *
 * Each Personage has a unique ID assigned automatically.
 */
public class Personage {

    /* ===== STATIC ATTRIBUTES ===== */

    /** Counter to keep track of how many Personage instances have been created. */
    private static int counter = 0;

    /* ===== INSTANCE ATTRIBUTES ===== */

    /** Unique identifier for this personage. */
    private int id;

    /** The display name of the personage. */
    private String name;

    /** Internal name used for logic and identification. */
    private String internalName;

    /** The age of the personage. */
    private int age;

    /** Description providing details about the personage. */
    private String description;

    /** Alignment or affiliation of the personage. */
    private String alignment;

    /** List of traits characterizing the personage. */
    private List<String> traits;

    /** Image representing the personage visually. */
    private BufferedImage personageImage;

    /* ===== CONSTRUCTORS ===== */

    /**
     * Constructs a new Personage with the specified attributes.
     * The unique ID is auto-incremented.
     *
     * @param name         The display name of the personage
     * @param internalName The internal name used for logic purposes
     * @param age          The age of the personage
     * @param description  Description of the personage
     * @param alignment    Alignment or affiliation of the personage
     */
    public Personage(String name, String internalName, int age, String description, String alignment) {
        this.id = ++counter;
        this.name = name;
        this.internalName = internalName;
        this.age = age;
        this.description = description;
        this.alignment = alignment;
        this.traits = new ArrayList<>();
    }

    /* ===== METHODS ===== */

    /**
     * Adds a trait to the personage if it is not already present.
     *
     * @param trait The trait to add
     */
    public void addTrait(String trait) {
        if (!traits.contains(trait)) {
            traits.add(trait);
        }
    }

    /**
     * Generates a Vertex object that encapsulates this personage.
     *
     * @return A new Vertex containing this personage
     */
    public Vertex genVertex() {
        return new Vertex(this);
    }

    /* ===== GETTERS ===== */

    /** @return The age of the personage */
    public int getAge() {
        return this.age;
    }

    /** @return The alignment of the personage */
    public String getAlignment() {
        return this.alignment;
    }

    /** @return The description of the personage */
    public String getDescription() {
        return this.description;
    }

    /** @return The unique ID of the personage */
    public int getId() {
        return this.id;
    }

    /** @return The internal name used for logic */
    public String getInternalName() {
        return this.internalName;
    }

    /** @return The image representing the personage */
    public BufferedImage getPersonageImage() {
        return this.personageImage;
    }

    /** @return The display name of the personage */
    public String getName() {
        return this.name;
    }

    /**
     * Returns formatted details about the personage, including name,
     * age, alignment, and description.
     *
     * @return A formatted string with the personage details
     */
    public String getPersonageDetails() {
        return String.format(
            "Nome: %s\nIdade: %d\nAlinhamento: %s\nDescrição: %s\n",
            name, age, alignment, description
        );
    }

    /** 
     * Returns an unmodifiable list of the traits associated with this personage.
     *
     * @return An unmodifiable List of traits
     */
    public List<String> getTraits() {
        return Collections.unmodifiableList(traits);
    }

    /* ===== SETTERS ===== */

    /**
     * Sets the description of the personage.
     *
     * @param description The new description to assign
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the image representing the personage.
     *
     * @param personageImage The new image to assign
     */
    public void setPersonageImage(BufferedImage personageImage) {
        this.personageImage = personageImage;
    }

    /* ===== OVERRIDES ===== */

    /**
     * Returns a string representation of the personage,
     * including its ID and name.
     *
     * @return A string in the format "[id] name"
     */
    @Override
    public String toString() {
        return String.format("[%d] %s", id, name);
    }

} // Personage


