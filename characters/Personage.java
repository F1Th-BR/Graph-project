package characters;
import java.util.*;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/*
 * This class defines the personages
 */

public class Personage {
    /* ATRIBUTES */
    private String name; // Stores the name of the personage
    private int age; // Stores the age of the personage
    private String description; // Stores the description of the personage
    private String alignment; // Stores the alignment of the personage
    private List<String> traits; // List of traits of the personage

    /* METHODS */
    public Personage(String name, int age, String description,String alignment) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.alignment = alignment;
        this.traits = new ArrayList<>();
    } // Constructor

    // add method
    public void addTrait(String trait) {
        if(!traits.contains(trait))
            traits.add(trait);
    } // add traits if not exists

    // getters
    public int getAge() {
        return this.age;
    } // returns the personage's age
    
    public String getAlignment() {
        return this.alignment;
    } // returns the personage's alignment

    public String getDescription() {
        return this.description;
    } // returns the personage's description
    
    public String getName() {
        return this.name;
    } // returns the personage's name
    
    public String getPersonageDetails() {
       return String.format(
            "Nome: %s\nIdade: %s\nAlinhamento: %s\nDescrição: %s\n",
            name, age, alignment, description
        );
    } // returns the personage's details

    public List<String> getTraits() {
        return Collections.unmodifiableList(traits);
    } // returns an unmodifiable list containing the personage's traits

    // setter
    public void setDescription(String description) {
        this.description = description;
    } // sets the personage's description

    @Override
    public String toString() {
        return name;
    } // toString

}

