package com.yourcompany.graph;

public interface WeightFunction<V> {

    void setWeight(V sourceVertex, V targetVertex, int weight);
    int getWeight(V sourceVertex, V targetVertex);
}
