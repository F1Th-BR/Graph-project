package graphviewer;
import graph.Graph;
import javax.swing.JFrame;

public class GraphViewer {
   public GraphViewer() {
   }

   public static void display(Graph var0) {
      JFrame var1 = new JFrame("Graph Viewer");
      var1.setDefaultCloseOperation(3);
      var1.setSize(1000, 800);
      var1.add(new GraphPanel(var0));
      var1.setVisible(true);
   }
}
