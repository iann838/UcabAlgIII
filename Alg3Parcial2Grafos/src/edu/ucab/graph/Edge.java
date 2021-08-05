package edu.ucab.graph;

public class Edge<V, W> {

    private W weight;
    private Vertex<V, W> source;
    private Vertex<V, W> destination;
    private Graph<V, W> graph;

    public Edge(Graph<V, W> graph, Vertex<V, W> source, Vertex<V, W> destination, W weight) {
        this.setWeight(weight);
    }

    public W getWeight() {
        return weight;
    }

    public void setWeight(W weight) {
        this.weight = weight;
    }

    public Graph<V, W> getGraph() {
        return graph;
    }

    public void setGraph(Graph<V, W> graph) {
        this.graph = graph;
    }
    
    public Vertex<V, W> getSource() {
        return source;
    }
    
    public void setSource(Vertex<V, W> source) {
        this.source = source;
    }

    public Vertex<V, W> getDestination() {
        return destination;
    }

    public void setDestination(Vertex<V, W> destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return this.getSource() + ":" + this.getDestination() + " (" + this.getWeight().toString() + ")";
    }

}
