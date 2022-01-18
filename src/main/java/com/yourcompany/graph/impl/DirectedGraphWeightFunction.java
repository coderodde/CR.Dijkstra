package com.yourcompany.graph.impl;

import com.yourcompany.graph.WeightFunction;
import java.util.HashMap;
import java.util.Map;

public class DirectedGraphWeightFunction<V> 
implements WeightFunction<V> {

    private final Map<V, Map<V, Integer>> map = new HashMap<>();
    
    @Override
    public void setWeight(V sourceVertex, V targetVertex, int weight) {
        if (!map.containsKey(sourceVertex)) {
            map.put(sourceVertex, new HashMap<>());
        }
        
        map.get(sourceVertex).put(targetVertex, weight);
    }
    
    @Override
    public int getWeight(V sourceVertex, V targetVertex) {
        return map.get(sourceVertex).get(targetVertex);
    }
}
