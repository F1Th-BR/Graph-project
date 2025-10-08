package com.graphproject.loaders;


import com.graphproject.domain.characters.Personage;
import com.graphproject.domain.graph.Graph;
import com.graphproject.domain.graph.Vertex;

import com.google.gson.Gson;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * This class is responsible for reading a graph structure from a JSON file
 * and constructing the corresponding Graph object.
 * 
 * <p>The graph consists of vertices, each representing a personage, and edges
 * that define relationships between them. The personages are associated with images,
 * and the edges define directed or undirected relationships.
 * 
 * <p>The JSON file should contain personage data (name, internalName, age, etc.) 
 * and edge data (from, to, relation, bidirectional).
 */
public class GraphLoader {

    /* ===== INTERNAL CLASSES ===== */

    /**
     * Represents data for a personage.
     * <p>Used to deserialize personage data from the JSON file.
     */
    static class PersonageData {
        String name;          // The name of the personage
        String internalName;  // The internal name of the personage (for image matching)
        int age;              // The age of the personage
        String description;   // A brief description of the personage
        String alignment;     // The alignment of the personage (e.g., good, evil)
    }

    /**
     * Represents data for an edge in the graph.
     * <p>Used to deserialize edge data from the JSON file.
     */
    static class EdgeData {
        int from;            // Index of the "from" vertex
        int to;              // Index of the "to" vertex
        String relation;     // The relationship type between the two vertices
        boolean bidirectional; // Whether the edge is bidirectional or directed
    }

    /**
     * Contains the graph data including personages and edges.
     */
    static class GraphData {
        List<PersonageData> personages; // List of personages in the graph
        List<EdgeData> edges;           // List of edges connecting the personages
    }

    /* ===== STATIC METHODS ===== */

    /**
     * Loads a graph from a JSON file and associates personages with images from a folder.
     * <p>The graph is constructed by reading personage and edge data from the JSON file.
     * Personages are then associated with image files based on their internal name.
     * 
     * @param jsonPath         The path to the JSON file containing graph data
     * @param imageFolderPath  The folder path where personage images are stored
     * @return The constructed Graph object
     * @throws Exception If any error occurs during file reading, image loading, or graph creation
     */
    public static Graph loadGraphFromJson(String jsonPath, String imageFolderPath) throws Exception {

        Gson gson = new Gson();            // Gson object for parsing JSON
        Reader reader = new FileReader(jsonPath); // Reader to read the JSON file
        GraphData data = gson.fromJson(reader, GraphData.class); // Deserialize JSON into GraphData

        Graph graph = new Graph(); // Create an empty graph
        List<Vertex> vertices = new ArrayList<>(); // List to hold all vertices

        // Supported image file extensions for personage images
        List<String> extensions = Arrays.asList(".png", ".jpg", ".jpeg");

        // Step 1: Create Personages, set image by internalName, and create Vertices
        for (PersonageData pd : data.personages) {
            // Create a Personage object using the data from JSON
            Personage p = new Personage(pd.name, pd.internalName, pd.age, pd.description, pd.alignment);

            // Attempt to find and load the image file corresponding to the personage's internal name
            for (String ext : extensions) {
                File imgFile = new File(imageFolderPath + "/" + pd.internalName + ext);
                if (imgFile.exists()) {
                    BufferedImage image = ImageIO.read(imgFile); // Read the image file
                    p.setPersonageImage(image); // Set the image for the personage
                    break; // Exit after finding the first valid image
                }
            }

            // Generate a Vertex for this Personage and add it to the graph
            Vertex v = p.genVertex(); // Generate vertex for personage
            vertices.add(v);           // Add the vertex to the list
            graph.addVertex(v);        // Add the vertex to the graph
        }

        // Step 2: Create and add Edges between Vertices based on the edge data
        for (EdgeData ed : data.edges) {
            // Retrieve the 'from' and 'to' vertices from the list
            Vertex from = vertices.get(ed.from);
            Vertex to = vertices.get(ed.to);
            
            // Add the edge to the graph with the given relation and directionality
            graph.addEdge(from, to, ed.relation, ed.bidirectional);
        }

        return graph; // Return the constructed graph
    }

} // GraphLoader
