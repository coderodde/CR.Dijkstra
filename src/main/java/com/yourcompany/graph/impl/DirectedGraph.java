package com.yourcompany.graph.impl;

import com.yourcompany.graph.Graph;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DirectedGraph<V> implements Graph<V> {

    private final Map<V, Set<V>> childMap = new HashMap<>();
    private final Map<V, Set<V>> parentMap = new HashMap<>();
    private final Set<V> vertices = new HashSet<>();
    private int numberOfEdges;
    
    @Override
    public boolean addEdge(V sourceVertex, V targetVertex) {
        checkInputNotNull(sourceVertex);
        checkInputNotNull(targetVertex);
        
        boolean stateChanged = false;
        
        if (!hasVertex(sourceVertex)) {
            addVertex(sourceVertex);
            stateChanged = true;
        }
        
        if (!hasVertex(targetVertex)) {
            addVertex(targetVertex);
            stateChanged = true;
        }
        
        if (!childMap.get(sourceVertex).contains(targetVertex)) {
            childMap.get(sourceVertex).add(targetVertex);
            stateChanged = true;
        }
        
        if (!parentMap.get(targetVertex).contains(sourceVertex)) {
            parentMap.get(targetVertex).add(sourceVertex);
            stateChanged = true;
        }
        
        if (stateChanged) {
            numberOfEdges++;
        }
        
        return stateChanged;
    }

    @Override
    public boolean removeEdge(V sourceVertex, V targetVertex) {
        checkInputNotNull(sourceVertex);
        checkInputNotNull(targetVertex);
        
        if (!childMap.containsKey(sourceVertex)) {
            return false;
        }
        
        if (!childMap.get(sourceVertex).contains(targetVertex)) {
            return false;
        }
        
        childMap.get(sourceVertex).remove(targetVertex);
        parentMap.get(targetVertex).remove(sourceVertex);
        numberOfEdges--;
        return true;
    }

    @Override
    public boolean addVertex(V vertex) {
        checkInputNotNull(vertex);
        
        if (!childMap.containsKey(vertex)) {
            childMap.put(vertex, new HashSet<>());
            parentMap.put(vertex, new HashSet<>());
            vertices.add(vertex);
            return true;
        }
        
        return false;
    }

    @Override
    public boolean hasVertex(V vertex) {
        checkInputNotNull(vertex);
        return vertices.contains(vertex);
    }

    @Override
    public boolean removeVertex(V vertex) {
        checkInputNotNull(vertex);
        
        if (!hasVertex(vertex)) {
            return false;
        }
        
        numberOfEdges -= 
                (childMap.get(vertex).size() + parentMap.get(vertex).size());
        
        childMap.remove(vertex);
        parentMap.remove(vertex);
        vertices.remove(vertex);
        return true;
    }

    @Override
    public Collection<V> getParentVerticesOf(V vertex) {
        checkInputNotNull(vertex);
        
        if (!parentMap.containsKey(vertex)) {
            return Collections.<V>emptySet();
        }
        
        return Collections.<V>unmodifiableSet(parentMap.get(vertex));
    }

    @Override
    public Collection<V> getChildVerticesOf(V vertex) {
        checkInputNotNull(vertex);
        
        if (!childMap.containsKey(vertex)) {
            return Collections.<V>emptySet();
        }
        
        return Collections.<V>unmodifiableSet(childMap.get(vertex));
    }

    @Override
    public boolean hasEdge(V sourceVertex, V targetVertex) {
        checkInputNotNull(sourceVertex);
        checkInputNotNull(targetVertex);
        
        if (!childMap.containsKey(sourceVertex) ||
            !childMap.containsKey(targetVertex)) {
            return false;
        }
        
        return childMap.get(sourceVertex).contains(targetVertex);
    }

    @Override
    public Set<V> getVertices() {
        return Collections.<V>unmodifiableSet(vertices);
    }

    @Override
    public int numberOfNodes() {
        return vertices.size();
    }

    @Override
    public int numberOfEdges() {
        return numberOfEdges;
    }

    private static <V> void checkInputNotNull(V node) {
        Objects.requireNonNull(node, "The input graph vertex is null.");
    }
}
