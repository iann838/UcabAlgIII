package edu.ucab.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Graph<V, W> {

    private HashMap<Vertex<V, W>, ArrayList<Edge<V, W>>> index;
    private WeightValueGetter<W> weightValueGetter = null;
    
    @SuppressWarnings("serial")
    public static class GraphException extends Exception {
        public GraphException(String errorMessage) {
            super(errorMessage);
        }
    }

    public Graph() {
        this.index = new HashMap<Vertex<V, W>, ArrayList<Edge<V, W>>>();
    }

    public Graph(WeightValueGetter<W> weightValueGetter) { 
        super();
        this.setWeightValueGetter(weightValueGetter);
    }

    public void setWeightValueGetter(WeightValueGetter<W> weightValueGetter) {
        this.weightValueGetter = weightValueGetter;
    }
    
    public WeightValueGetter<W> getWeightValueGetter() {
        return weightValueGetter;
    }
    
    public void addVertex(Vertex<V, W> vertex) throws GraphException {
        if (this.index.get(vertex) != null) {
            throw new GraphException("Vertex already exists");
        }
        this.index.put(vertex, new ArrayList<Edge<V, W>>());
    }
    
    public void addVertex(V vertexData) throws GraphException {
        Vertex<V, W> vertex = new Vertex<V, W>(this, vertexData);
        this.addVertex(vertex);
    }
    
    public Vertex<V, W> getVertex(Vertex<V, W> vertex) throws GraphException {
        if (this.index.get(vertex) != null) {
            return vertex;
        }
        throw new GraphException("Vertex does not exists");
    }

    public Vertex<V, W> getVertex(V vertexData) throws GraphException {
        Vertex<V, W> vertex = new Vertex<V, W>(this, vertexData);
        return this.getVertex(vertex);
    }
    
    public ArrayList<Edge<V, W>> getEdges(Vertex<V, W> vertex) throws GraphException {
        ArrayList<Edge<V, W>> edges = this.index.get(vertex);
        if (edges == null)
            throw new GraphException("Vertex does not exists");
        return edges;
    }

    public ArrayList<Edge<V, W>> getEdges(V vertexData) throws GraphException {
        Vertex<V, W> vertex = new Vertex<V, W>(this, vertexData);
        return this.getEdges(vertex);
    }
    
    public void addEdge(Vertex<V, W> sourceVertex, Vertex<V, W> destinationVertex, W weight) throws GraphException {
        if (this.index.get(sourceVertex) == null || this.index.get(destinationVertex) == null) {
            throw new GraphException("Vertex does not exists");
        }
        this.getEdges(sourceVertex).add(new Edge<V, W>(this, sourceVertex, destinationVertex, weight));
    }

    public void addEdge(V sourceVertexData, V destinationVertexData, W weight) throws GraphException {
        Vertex<V, W> sourceVertex = new Vertex<V, W>(this, sourceVertexData);
        Vertex<V, W> destinationVertex = new Vertex<V, W>(this, destinationVertexData);
        this.addEdge(sourceVertex, destinationVertex, weight);
    }

    public HashMap<V, HashMap<V, W>> getAdjacencyMatrix() {
        HashMap<V, HashMap<V, W>> matrix = new HashMap<V, HashMap<V, W>>();
        for (Entry<Vertex<V, W>, ArrayList<Edge<V, W>>> entry : this.index.entrySet()) {
            V vertexData = entry.getKey().getData();
            matrix.put(vertexData, new HashMap<V, W>());
            for (Edge<V, W> edge: entry.getValue()) {
                matrix.get(vertexData).put(edge.getDestination().getData(), edge.getWeight());
            }
        }
        return matrix;
    }

    public Graph<V, W> getPrimMST(Vertex<V, W> startVertex) throws GraphException {
        if (this.getWeightValueGetter() == null)
            throw new GraphException("Weight value getter missing");
        Graph<V, W> mst = new Graph<V, W>();
        ArrayList<Vertex<V, W>> visitedVertices = new ArrayList<Vertex<V, W>>();
        visitedVertices.add(startVertex);
        mst.addVertex(startVertex);
        while (visitedVertices.size() < this.index.size()) {
            Double minWeight = Double.MAX_VALUE;
            Edge<V, W> minEdge = null;
            for (Vertex<V, W> visitedVertex: visitedVertices) {
                for (Edge<V, W> edge: visitedVertex.getEdges()) {
                    Double edgeWeight = this.getWeightValueGetter().get(edge.getWeight()).doubleValue();
                    if (edgeWeight < minWeight && !visitedVertices.contains(edge.getDestination())) {
                        minWeight = edgeWeight;
                        minEdge = edge;
                    }
                }
            }
            if (minEdge == null)
                break;
            visitedVertices.add(minEdge.getDestination());
            mst.addVertex(minEdge.getDestination());
            mst.addEdge(minEdge.getSource(), minEdge.getDestination(), minEdge.getWeight());
        }
        return mst;
    }

    public void print() {
        for (Entry<Vertex<V, W>, ArrayList<Edge<V, W>>> entry : this.index.entrySet()) {
            Vertex<V, W> key = entry.getKey();
            ArrayList<Edge<V, W>> edges = entry.getValue();
            String output = "Vertex: " + key.getData().toString() + " Edges: ";
            for (Edge<V, W> edge: edges) {
                output = output + edge.toString() + " | ";
            }
            System.out.println(output);
        }
    }

}
