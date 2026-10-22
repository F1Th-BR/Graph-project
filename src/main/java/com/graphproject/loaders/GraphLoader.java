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
 * Loads a {@link Graph} from a JSON file and associates personage images from a folder.
 *
 * <p>The JSON file must follow the schema below. Every field except {@code name} and
 * {@code internalName} is optional — missing fields are silently ignored and the
 * corresponding Personage attribute keeps its default value (empty string / zero).
 * The {@code extras} object accepts any key-value pairs and is forwarded to
 * {@link Personage.Builder#extra(String, Object)}, so new attributes can be added to
 * the JSON without touching any Java code.
 *
 * <pre>{@code
 * {
 *   "personages": [
 *     {
 *       "name":           "Gandalf",        // required
 *       "internalName":   "gandalf",        // required  (used to find gandalf.png)
 *       "age":            2019,             // optional
 *       "race":           "Maia",           // optional
 *       "sex":            "M",              // optional  ("M" or "F")
 *       "characterClass": "Mago",           // optional
 *       "subclass":       "Mago Cinzento",  // optional
 *       "alignment":      "Neutro e Bom",   // optional
 *       "description":    "Um dos Istari.", // optional
 *       "traits":         ["Sábio"],        // optional
 *       "extras": {                         // optional — any key-value pairs
 *         "nivel": 20,
 *         "pontosMagia": 500
 *       }
 *     }
 *   ],
 *   "edges": [
 *     { "from": 0, "to": 1, "relation": "amigos", "bidirectional": true }
 *   ]
 * }
 * }</pre>
 */
public class GraphLoader {

    /* ===== INTERNAL DTO CLASSES ===== */

    /**
     * Data Transfer Object that mirrors the JSON structure for a personage.
     *
     * <p>Gson maps JSON field names directly to Java field names; fields absent in
     * the JSON are left as {@code null}/zero, which the builder treats as "not set".
     * {@code extras} uses a raw {@code Map<String, Object>} so Gson deserialises any
     * JSON object without needing to know the keys in advance.
     */
    static class PersonageData {
        String name;                        // required
        String internalName;               // required
        int age;                           // optional (default 0)
        String description;                // optional
        String alignment;                  // optional
        String race;                       // optional
        String sex;                        // optional ("M" or "F")
        String characterClass;             // optional
        String subclass;                   // optional
        List<String> traits;               // optional
        Map<String, Object> extras;        // optional — arbitrary extra attributes
    }

    /**
     * Data Transfer Object that mirrors the JSON structure for an edge.
     */
    static class EdgeData {
        int from;              // index of the "from" vertex in the personages array
        int to;                // index of the "to" vertex
        String relation;       // label describing the relationship
        boolean bidirectional; // true = u ↔ v, false = u → v
    }

    /**
     * Root wrapper that holds the full graph data from JSON.
     */
    static class GraphData {
        List<PersonageData> personages;
        List<EdgeData> edges;
    }

    /* ===== SUPPORTED IMAGE EXTENSIONS ===== */

    /** File extensions tried in order when looking for a personage image. */
    private static final List<String> IMAGE_EXTENSIONS =
        Collections.unmodifiableList(Arrays.asList(".png", ".jpg", ".jpeg"));

    /* ===== STATIC FACTORY ===== */

    /**
     * Loads a graph from a JSON file and resolves personage images from an image folder.
     *
     * <p>Building steps:
     * <ol>
     *   <li>Parse the JSON into {@link GraphData}.</li>
     *   <li>For each personage entry, build a {@link Personage} via the Builder,
     *       feeding only the fields that are present in the JSON.</li>
     *   <li>Attempt to load an image file named {@code <internalName>.<ext>} from
     *       {@code imageFolderPath}. If found, attach it via
     *       {@link Personage#setPersonageImage}. If not found, the vertex is rendered
     *       with the blue fallback circle (see VertexCircle2).</li>
     *   <li>Add each vertex to the graph, then add all edges.</li>
     * </ol>
     *
     * @param jsonPath        Path to the JSON file (relative or absolute)
     * @param imageFolderPath Path to the folder containing personage image files
     * @return The fully constructed {@link Graph}
     * @throws Exception if the JSON file cannot be read or is malformed
     */
    public static Graph loadGraphFromJson(String jsonPath, String imageFolderPath) throws Exception {

        // Parse the JSON file into the DTO hierarchy
        Gson gson = new Gson();
        Reader reader = new FileReader(jsonPath);
        GraphData data = gson.fromJson(reader, GraphData.class);
        reader.close();

        Graph graph    = new Graph();
        List<Vertex> vertices = new ArrayList<>();

        // ── Step 1: create each Personage via Builder, then its Vertex ──────────
        for (PersonageData pd : data.personages) {

            // Start with the two mandatory fields
            Personage.Builder builder = new Personage.Builder(pd.name, pd.internalName);

            // Feed optional core fields only when present in the JSON
            // (Gson leaves numeric fields at 0 and String fields at null when absent)
            if (pd.age > 0)                         builder.age(pd.age);
            if (pd.description != null)             builder.description(pd.description);
            if (pd.alignment != null)               builder.alignment(pd.alignment);
            if (pd.race != null)                    builder.race(pd.race);
            if (pd.sex != null)                     builder.sex(pd.sex);
            if (pd.characterClass != null)          builder.characterClass(pd.characterClass);
            if (pd.subclass != null)                builder.subclass(pd.subclass);
            if (pd.traits != null)                  builder.traits(pd.traits);

            // Forward any extra attributes — new JSON keys are picked up automatically
            // here without touching the Builder or Personage source code
            if (pd.extras != null) {
                for (Map.Entry<String, Object> entry : pd.extras.entrySet()) {
                    builder.extra(entry.getKey(), entry.getValue());
                }
            }

            Personage personage = builder.build();

            // ── Step 2: try to load the image for this personage ─────────────────
            for (String ext : IMAGE_EXTENSIONS) {
                File imgFile = new File(imageFolderPath + "/" + pd.internalName + ext);
                if (imgFile.exists()) {
                    BufferedImage image = ImageIO.read(imgFile);
                    personage.setPersonageImage(image);
                    break; // stop at the first match
                }
            }
            // If no image file is found, personageImage stays null and the renderer
            // will draw the blue fallback circle instead (RF-08).

            // ── Step 3: create the vertex and register it ────────────────────────
            Vertex v = personage.genVertex();
            vertices.add(v);
            graph.addVertex(v);
        }

        // ── Step 4: create all edges using the vertex indices from the JSON ──────
        for (EdgeData ed : data.edges) {
            Vertex from = vertices.get(ed.from);
            Vertex to   = vertices.get(ed.to);
            graph.addEdge(from, to, ed.relation, ed.bidirectional);
        }

        return graph;
    }

} // GraphLoader
