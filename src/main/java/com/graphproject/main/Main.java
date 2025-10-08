package com.graphproject.main;

import com.graphproject.domain.graph.Graph;
import com.graphproject.loaders.GraphLoader;
import com.graphproject.ui.graphviewer.GraphViewer;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * Main class to load a graph from a JSON file and display it using the GraphViewer.
 * <p>This class is responsible for initializing the graph, loading data from a JSON file,
 * associating images with personages, and then passing the graph to a viewer to be displayed.
 */
public class Main {
    public static void main(String[] args) {
        // Paths to the resources (JSON and images)
        String jsonPath = "src/resources/graph_data.json";  // Path to the JSON file
        String imageFolderPath = "src/resources/images/";  // Path to the folder with personage images

        try {
            // Step 1: Load the graph from JSON file
            Graph graph = GraphLoader.loadGraphFromJson(jsonPath, imageFolderPath);
            
            // Step 2: Output success message with the number of vertices in the graph
            System.out.println("Graph loaded successfully with " + graph.getVertices().size() + " vertices.");
            
            // Step 3: Display the graph using the GraphViewer
            GraphViewer.display(graph);

        } catch (Exception e) {
            // Step 4: Handle any exceptions during the graph loading process
            System.err.println("Error loading graph: " + e.getMessage());
            e.printStackTrace();
        }
    }
} // Main

