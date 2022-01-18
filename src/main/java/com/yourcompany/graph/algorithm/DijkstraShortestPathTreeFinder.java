package com.yourcompany.graph.algorithm;

import com.yourcompany.graph.Graph;
import com.yourcompany.graph.ShortestPathTreeBuilder;
import com.yourcompany.graph.WeightFunction;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class DijkstraShortestPathTreeFinder<V> {

    private static final class PriorityQueueNode<V> 
            implements Comparable<PriorityQueueNode<V>> {
        
        V vertex;
        int distance;

        PriorityQueueNode(V vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
        
        @Override
        public int compareTo(PriorityQueueNode<V> o) {
            return Integer.compare(distance, o.distance);
        }
    }
    
    public ShortestPathTreeBuilder<V> 
        findShortestPathTree(Graph<V> graph,
                             WeightFunction<V> weightFunction,
                             V sourceVertex) {
            
        Map<V, V> parentMap = new HashMap<>();
        Map<V, Integer> distanceMap = new HashMap<>();
        Queue<PriorityQueueNode<V>> openQueue = new PriorityQueue<>();
        Set<V> closedSet = new HashSet<>();
        
        parentMap.put(sourceVertex, null);
        distanceMap.put(sourceVertex, 0);
        openQueue.add(new PriorityQueueNode<>(sourceVertex, 0));
        
        while (!openQueue.isEmpty()) {
            PriorityQueueNode<V> priorityQueueNode = openQueue.remove();
            V vertex = priorityQueueNode.vertex;
            closedSet.add(vertex);
            
            for (V childVertex : graph.getChildVerticesOf(vertex)) {
                if (closedSet.contains(childVertex)) {
                    continue;
                }
                
                int tentativeDistance = 
                        distanceMap.get(vertex) + 
                        weightFunction.getWeight(vertex, childVertex);
                
                if (!distanceMap.containsKey(childVertex)
                        || distanceMap.get(childVertex) > tentativeDistance) {
                    
                    distanceMap.put(childVertex, tentativeDistance);
                    parentMap.put(childVertex, vertex);
                    openQueue.add(new PriorityQueueNode<>(childVertex, 
                                                          tentativeDistance));
                }
            } 
        }
        
        return new ShortestPathTreeBuilder<>(parentMap);
    }
}
