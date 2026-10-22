package com.graphproject.domain.characters;

import java.awt.image.BufferedImage;
import java.util.*;

import com.graphproject.domain.graph.Vertex;

/**
 *
 * @author Thiago Feijó de Albuquerque
 */

/**
 * Represents a personage (character) in the RPG graph.
 *
 * <p>A Personage holds all attributes that describe a character: core fields such as
 * name, age, race, class and alignment, plus an open-ended map of extra attributes
 * ({@code extraAttributes}) that allows new fields to be added in the future without
 * modifying this class or its rendering code (Open/Closed Principle).
 *
 * <h2>Construction — Builder pattern</h2>
 * <p>Personage objects must be created through the nested {@link Builder} class, never
 * directly. The Builder enforces that only {@code name} and {@code internalName} are
 * mandatory; every other attribute is optional and can be set in any order, making it
 * impossible to accidentally swap positional arguments in a long constructor call:
 *
 * <pre>{@code
 * // Minimal construction — only the two required fields
 * Personage p = new Personage.Builder("Gandalf", "gandalf").build();
 *
 * // Full construction — any subset of optional fields, in any order
 * Personage p = new Personage.Builder("Gandalf", "gandalf")
 *     .age(2019)
 *     .race("Maia")
 *     .sex("M")
 *     .characterClass("Mago")
 *     .subclass("Mago Cinzento")
 *     .alignment("Neutro e Bom")
 *     .description("Um dos Istari enviados à Terra-média.")
 *     .addTrait("Sábio")
 *     .addTrait("Misterioso")
 *     .extra("nivel", 20)
 *     .build();
 * }</pre>
 *
 * <h2>Extensibility</h2>
 * <p>New character attributes can be added in two ways:
 * <ul>
 *   <li><b>Typed field</b> — add a field + Builder method when the attribute is
 *       well-known and used in logic (e.g. {@code class}, {@code alignment}).</li>
 *   <li><b>Extra attribute</b> — call {@code .extra("key", value)} on the Builder
 *       for future or campaign-specific attributes. These are stored in
 *       {@code extraAttributes} and rendered automatically by the character panel
 *       without any code change.</li>
 * </ul>
 */
public class Personage {

    /* ===== STATIC ATTRIBUTES ===== */

    /**
     * Auto-incremented counter used to assign unique IDs.
     *
     * <p>NOTE (design issue, preserved intentionally): this counter is static and
     * never reset, so IDs keep growing across multiple graph loads in the same JVM
     * session. This is acceptable for the current single-session use case. If the
     * application ever supports loading multiple graphs sequentially, this should be
     * replaced with a UUID or a session-scoped counter.
     */
    private static int counter = 0;

    /* ===== CORE ATTRIBUTES ===== */

    /** Unique identifier, auto-assigned at construction time. */
    private final int id;

    /** Display name shown in the graph and in the character panel. */
    private final String name;

    /**
     * Internal name used to locate the personage's image file.
     * The image loader looks for a file named {@code <internalName>.<ext>} in the
     * configured image folder (e.g. {@code "gandalf.png"}).
     */
    private final String internalName;

    /** Age of the personage in years. {@code 0} when not set. */
    private final int age;

    /** Free-text description of the personage. Empty string when not set. */
    private final String description;

    /**
     * Moral and ethical alignment (e.g. "Leal e Bom", "Caótico e Neutro").
     * Empty string when not set.
     */
    private final String alignment;

    /**
     * Race of the personage (e.g. "Humano", "Elfo", "Anão").
     * Empty string when not set.
     */
    private final String race;

    /**
     * Biological sex of the personage. Accepted values: {@code "M"} or {@code "F"}.
     * Empty string when not set.
     */
    private final String sex;

    /**
     * Primary class of the personage (e.g. "Guerreiro", "Mago", "Paladino").
     * Empty string when not set.
     */
    private final String characterClass;

    /**
     * Subclass or specialisation within the primary class
     * (e.g. "Mago de Batalha", "Paladino da Vingança").
     * Empty string when not set.
     */
    private final String subclass;

    /**
     * Personality traits of the personage.
     * Stored as an unmodifiable list after construction.
     */
    private final List<String> traits;

    /**
     * Open-ended map for attributes that are not part of the core schema.
     *
     * <p>Keys are human-readable attribute names (e.g. {@code "nivel"},
     * {@code "pontosMagia"}); values can be any type. The character panel iterates
     * this map and renders each entry as a row in the attribute table, so new
     * attributes are displayed automatically without touching rendering code.
     */
    private final Map<String, Object> extraAttributes;

    /** Image representing the personage visually. May be {@code null} if not loaded. */
    private BufferedImage personageImage;

    /* ===== PRIVATE CONSTRUCTOR (only Builder may call this) ===== */

    /**
     * Private constructor — use {@link Builder} to create instances.
     *
     * <p>All mutable collections are defensively copied so that later changes to
     * the Builder's internal state cannot affect the constructed Personage.
     *
     * @param builder The fully configured Builder
     */
    private Personage(Builder builder) {
        this.id              = ++counter;
        this.name            = builder.name;
        this.internalName    = builder.internalName;
        this.age             = builder.age;
        this.description     = builder.description;
        this.alignment       = builder.alignment;
        this.race            = builder.race;
        this.sex             = builder.sex;
        this.characterClass  = builder.characterClass;
        this.subclass        = builder.subclass;

        // Defensive copies — Builder lists must not be shared with the Personage
        this.traits          = Collections.unmodifiableList(new ArrayList<>(builder.traits));
        this.extraAttributes = Collections.unmodifiableMap(new LinkedHashMap<>(builder.extraAttributes));
    }

    /* ===== BUILDER ===== */

    /**
     * Fluent builder for {@link Personage}.
     *
     * <p>Only {@code name} and {@code internalName} are required; all other fields
     * default to empty/zero when omitted. Methods return {@code this} so calls can
     * be chained. Call {@link #build()} to produce the immutable Personage.
     */
    public static class Builder {

        /* Required fields */
        private final String name;
        private final String internalName;

        /* Optional fields — defaults set here */
        private int age                      = 0;
        private String description           = "";
        private String alignment             = "";
        private String race                  = "";
        private String sex                   = "";
        private String characterClass        = "";
        private String subclass              = "";
        private List<String> traits          = new ArrayList<>();
        private Map<String, Object> extraAttributes = new LinkedHashMap<>();

        /**
         * Starts building a Personage with the two mandatory fields.
         *
         * @param name         Display name (must not be null or blank)
         * @param internalName Internal ID used for image lookup (must not be null or blank)
         * @throws IllegalArgumentException if either argument is null or blank
         */
        public Builder(String name, String internalName) {
            if (name == null || name.isBlank())
                throw new IllegalArgumentException("Personage name must not be null or blank.");
            if (internalName == null || internalName.isBlank())
                throw new IllegalArgumentException("Personage internalName must not be null or blank.");

            this.name         = name;
            this.internalName = internalName;
        }

        /**
         * Sets the age of the personage.
         *
         * @param age Age in years (non-negative)
         * @return this Builder
         */
        public Builder age(int age) {
            this.age = age;
            return this;
        }

        /**
         * Sets the free-text description.
         *
         * @param description Description text
         * @return this Builder
         */
        public Builder description(String description) {
            this.description = description != null ? description : "";
            return this;
        }

        /**
         * Sets the moral and ethical alignment.
         *
         * @param alignment Alignment string (e.g. "Leal e Bom")
         * @return this Builder
         */
        public Builder alignment(String alignment) {
            this.alignment = alignment != null ? alignment : "";
            return this;
        }

        /**
         * Sets the race of the personage.
         *
         * @param race Race string (e.g. "Humano", "Elfo")
         * @return this Builder
         */
        public Builder race(String race) {
            this.race = race != null ? race : "";
            return this;
        }

        /**
         * Sets the biological sex. Accepted values: {@code "M"} or {@code "F"}.
         *
         * @param sex Sex string
         * @return this Builder
         * @throws IllegalArgumentException if the value is not "M", "F", or empty/null
         */
        public Builder sex(String sex) {
            if (sex != null && !sex.isBlank() && !sex.equals("M") && !sex.equals("F"))
                throw new IllegalArgumentException("Sex must be \"M\", \"F\", or omitted. Got: " + sex);
            this.sex = sex != null ? sex : "";
            return this;
        }

        /**
         * Sets the primary character class.
         *
         * @param characterClass Class string (e.g. "Guerreiro", "Mago")
         * @return this Builder
         */
        public Builder characterClass(String characterClass) {
            this.characterClass = characterClass != null ? characterClass : "";
            return this;
        }

        /**
         * Sets the subclass or specialisation.
         *
         * @param subclass Subclass string
         * @return this Builder
         */
        public Builder subclass(String subclass) {
            this.subclass = subclass != null ? subclass : "";
            return this;
        }

        /**
         * Appends a personality trait to the list.
         * Duplicate traits (case-sensitive) are silently ignored.
         *
         * @param trait Trait string
         * @return this Builder
         */
        public Builder addTrait(String trait) {
            if (trait != null && !trait.isBlank() && !traits.contains(trait))
                traits.add(trait);
            return this;
        }

        /**
         * Sets the full list of traits at once, replacing any previously added traits.
         * Null entries and duplicates in the provided list are ignored.
         *
         * @param traits List of trait strings
         * @return this Builder
         */
        public Builder traits(List<String> traits) {
            this.traits = new ArrayList<>();
            if (traits != null)
                traits.forEach(this::addTrait);
            return this;
        }

        /**
         * Adds an extra attribute under an arbitrary key.
         *
         * <p>Use this for any attribute not covered by the typed fields above.
         * The character panel will render each entry automatically as a row in the
         * attribute table, so no rendering code needs to change when new extras are added.
         *
         * <p>Example: {@code .extra("nivel", 20).extra("pontosMagia", 500)}
         *
         * @param key   Attribute name (must not be null or blank)
         * @param value Attribute value (any type; {@code toString()} is used for display)
         * @return this Builder
         */
        public Builder extra(String key, Object value) {
            if (key != null && !key.isBlank())
                this.extraAttributes.put(key, value);
            return this;
        }

        /**
         * Constructs and returns the immutable {@link Personage} instance.
         *
         * @return A new Personage with all the configured attributes
         */
        public Personage build() {
            return new Personage(this);
        }

    } // Builder

    /* ===== METHODS ===== */

    /**
     * Generates a Vertex that encapsulates this personage.
     *
     * @return A new Vertex referencing this Personage
     */
    public Vertex genVertex() {
        return new Vertex(this);
    }

    /**
     * Returns a ordered map of all attributes suitable for display in the character
     * panel table. Core fields appear first in a fixed, human-readable order;
     * extra attributes follow in the order they were added.
     *
     * <p>This method is the single source of truth for the attribute table —
     * the CharacterPanel iterates this map and renders each entry as a row,
     * so no rendering code needs to change when new attributes are added.
     *
     * @return An ordered, unmodifiable map of attribute label → display value
     */
    public Map<String, String> getDisplayAttributes() {
        // LinkedHashMap preserves insertion order (core fields first, extras after)
        Map<String, String> attrs = new LinkedHashMap<>();

        attrs.put("Nome",         name);
        attrs.put("Nome interno", internalName);
        if (age > 0)                       attrs.put("Idade",      String.valueOf(age));
        if (!race.isBlank())               attrs.put("Raça",       race);
        if (!sex.isBlank())                attrs.put("Sexo",       sex);
        if (!characterClass.isBlank())     attrs.put("Classe",     characterClass);
        if (!subclass.isBlank())           attrs.put("Subclasse",  subclass);
        if (!alignment.isBlank())          attrs.put("Alinhamento",alignment);
        if (!description.isBlank())        attrs.put("Descrição",  description);
        if (!traits.isEmpty())             attrs.put("Traços",     String.join(", ", traits));

        // Extra attributes follow in insertion order — no code change needed here
        // when new extras are added via the Builder
        for (Map.Entry<String, Object> entry : extraAttributes.entrySet()) {
            attrs.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : "");
        }

        return Collections.unmodifiableMap(attrs);
    }

    /* ===== GETTERS ===== */

    /** @return Auto-assigned unique ID */
    public int getId()              { return id; }

    /** @return Display name */
    public String getName()         { return name; }

    /** @return Internal name (used for image lookup) */
    public String getInternalName() { return internalName; }

    /** @return Age in years ({@code 0} if not set) */
    public int getAge()             { return age; }

    /** @return Description text (empty string if not set) */
    public String getDescription()  { return description; }

    /** @return Alignment string (empty string if not set) */
    public String getAlignment()    { return alignment; }

    /** @return Race string (empty string if not set) */
    public String getRace()         { return race; }

    /** @return Sex string — {@code "M"}, {@code "F"}, or empty string */
    public String getSex()          { return sex; }

    /** @return Primary class string (empty string if not set) */
    public String getCharacterClass() { return characterClass; }

    /** @return Subclass string (empty string if not set) */
    public String getSubclass()     { return subclass; }

    /**
     * Returns an unmodifiable view of the traits list.
     *
     * @return Unmodifiable list of personality traits
     */
    public List<String> getTraits() { return traits; }

    /**
     * Returns an unmodifiable view of the extra attributes map.
     *
     * @return Unmodifiable map of extra attribute key → value
     */
    public Map<String, Object> getExtraAttributes() { return extraAttributes; }

    /**
     * Returns the personage image, or {@code null} if no image has been loaded.
     *
     * @return The image, or {@code null}
     */
    public BufferedImage getPersonageImage() { return personageImage; }

    /* ===== SETTERS ===== */

    /**
     * Sets the personage image after construction.
     * This is called by {@link com.graphproject.loaders.GraphLoader} after loading
     * the image file from disk — it cannot be part of the Builder because the image
     * is loaded separately from the JSON data.
     *
     * @param personageImage The loaded image, or {@code null} to clear it
     */
    public void setPersonageImage(BufferedImage personageImage) {
        this.personageImage = personageImage;
    }

    /* ===== OVERRIDES ===== */

    /**
     * Returns the display name of the personage.
     * Used by {@link com.graphproject.domain.graph.Vertex#toString()} to label
     * the vertex on the graph canvas.
     *
     * @return The personage's display name
     */
    @Override
    public String toString() {
        return name;
    }

} // Personage
