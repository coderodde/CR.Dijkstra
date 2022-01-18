package com.yourcompany.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ShortestPathTreeBuilder<V> {

    private final Map<V, V> parentMap;
    
    public ShortestPathTreeBuilder(Map<V, V> parentMap) {
        this.parentMap = 
                Objects.requireNonNull(
                        parentMap, 
                        "The input parent map is null.");
    }
    
    public ShortestPath<V> buildPathTo(V targetVertex) {
        List<V> path = new ArrayList<>();
        V node = targetVertex;
        
        while (node != null) {
            path.add(node);
            node = parentMap.get(node);
        }
        
        Collections.<V>reverse(path);
        return new ShortestPath<>(path);
    }
}
