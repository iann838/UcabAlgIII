package edu.ucab.graph;

import java.util.ArrayList;

import edu.ucab.graph.Graph.GraphException;

public class Vertex<V, W> {

    private V data;
    private Graph<V, W> graph;

    public Vertex(Graph<V, W> graph, V data) {
        this.graph = graph;
        this.data = data;
    }

    public V getData() {
        return data;
    }

    public Graph<V, W> getGraph() {
        return graph;
    }

    public ArrayList<Edge<V, W>> getEdges() throws GraphException {
        return this.getGraph().getEdges(this.getData());
    }
    
    public ArrayList<Vertex<V, W>> getAdjacentVetices() throws GraphException {
        ArrayList<Vertex<V, W>> vertices = new ArrayList<Vertex<V, W>>();
        ArrayList<Edge<V, W>> edges = this.getEdges();
        for (Edge<V, W> edge: edges) {
            vertices.add(edge.getDestination());
        }
        return vertices;
    }

    @Override
    public int hashCode() {
        return this.getData().hashCode();
    }

    @Override
    public String toString() {
        return this.getData().toString();
    }

}
