package com.yourcompany.graph;

import java.util.Collection;
import java.util.Set;

/**
 * This interface defines the API for a graph.
 * 
 * @param <V> the actual graph vertex type.
 */
public interface Graph<V> {
    
    boolean addEdge(V sourceVertex, V targetVertex);
    boolean hasEdge(V sourceVertex, V targetVertex);
    boolean removeEdge(V sourceVertex, V targetVertex);
    
    boolean addVertex(V vertex);
    boolean hasVertex(V vertex);
    boolean removeVertex(V vertex);
    
    Set<V> getVertices();
    Collection<V> getParentVerticesOf(V vertex);
    Collection<V> getChildVerticesOf(V vertex);
    
    int numberOfNodes();
    int numberOfEdges();
}
