package com.graphproject.ui.graphviewer;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

import com.graphproject.domain.graph.Graph;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * A viewer for displaying a Graph object in a graphical user interface.
 * 
 * This class provides a method to display a given Graph instance in a full-screen window,
 * utilizing a JFrame for rendering. It uses the default screen dimensions and displays the
 * Graph within a custom panel.
 */
public class GraphViewer {

   /* ===== CONSTRUCTORS ===== */

   /**
    * Constructs a GraphViewer object.
    * 
    * This constructor initializes the GraphViewer but doesn't perform any specific
    * setup or configuration.
    */
   public GraphViewer() {
   }

   /* ===== METHODS ===== */

   /**
    * Displays the provided Graph object in a new window.
    * 
    * This method creates a JFrame with the dimensions of the user's screen and adds
    * a custom GraphPanel to display the graph.
    * 
    * @param var0 The Graph object to be displayed
    */
   public static void display(Graph var0) {
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       int screenWidth = screenSize.width;
       int screenHeight = screenSize.height;

       // Create a new JFrame to display the graph
       JFrame var1 = new JFrame("Graph Viewer");
       
       // Set default close operation and size
       var1.setDefaultCloseOperation(3); // CLOSE_ON_EXIT
       var1.setSize(screenWidth, screenHeight);

       // Add the GraphPanel to the JFrame for rendering the graph
       var1.add(new GraphPanel(var0));

       // Make the window visible
       var1.setVisible(true);
   }
} // GraphViewer
