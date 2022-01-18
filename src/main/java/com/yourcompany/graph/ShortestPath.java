package com.yourcompany.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortestPath<V> {
    
    private final List<V> path = new ArrayList<>();
    
    public ShortestPath(List<V> path) {
        this.path.addAll(path);
    }
    
    public List<V> getVertexList() {
        return Collections.<V>unmodifiableList(path);
    }
    
    public int getPathWeight(WeightFunction<V> weightFunction) {
        int pathWeight = 0;
        
        for (int i = 0; i < path.size() - 1; i++) {
            pathWeight = pathWeight + weightFunction.getWeight(path.get(i),
                                                               path.get(i + 1));
        }
        
        return pathWeight;
    }
}
