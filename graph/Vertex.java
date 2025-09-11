package graph;
import characters.Personage;


public class Vertex {
    private final Personage personage;
    private int degree;

    public Vertex(Personage personage) {
        this.personage = personage;
        this.degree = 0;
    }

    public Personage getPersonage() {
        return personage;
    }

    public int getDegree() {
        return degree;
    }

    public void incrementDegree() {
        degree++;
    }

    public void decrementDegree() {
        degree--;
    }
    
    @Override
    public String toString() {
        return personage.getName();
    }
}

