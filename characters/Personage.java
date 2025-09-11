package characters;
import java.util.*;

public class Personage {
    private String name;
    private int age;
    private String description;
    private String alignment;
    private List<String> traits;


    public Personage(String name, int age, String description,String alignment) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.alignment = alignment;
        this.traits = new ArrayList<>();
    }

    public void addTrait(String trait) {
        traits.add(trait);
    }

    // getters
    public int getAge() {
        return this.age;
    }
    
    public String getAlignment() {
        return this.alignment;
    }

    public String getDescription() {
        return this.description;
    }
    
    public String getName() {
        return this.name;
    }

    public List<String> getTraits() {
        return Collections.unmodifiableList(traits);
    }

    // setter
    public void setDescription(String description) {
        this.description = description;
    }


    public String getPersonageDetails() {
       return String.format(
            "Nome: %s\nIdade: %s\nAlinhamento: %s\nDescrição: %s\n",
            name, age, alignment, description
        );
    }

    @Override
    public String toString() {
        return name;
    }

}

