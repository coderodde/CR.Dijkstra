package com.yourcompany.graph.algorithm;

import com.yourcompany.graph.Graph;
import com.yourcompany.graph.ShortestPath;
import com.yourcompany.graph.ShortestPathTreeBuilder;
import com.yourcompany.graph.WeightFunction;
import com.yourcompany.graph.impl.DirectedGraph;
import com.yourcompany.graph.impl.DirectedGraphWeightFunction;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DijkstraShortestPathTreeFinderTest {
    
    @Test
    public void testFindShortestPathTree() {
        Integer i1 = Integer.valueOf(1);
        Integer i2 = Integer.valueOf(2);
        Integer i3 = Integer.valueOf(3);
        Integer i4 = Integer.valueOf(4);
        Integer i5 = Integer.valueOf(5);
        
        Graph<Integer> g = new DirectedGraph<>();
        
        g.addVertex(i1);
        g.addVertex(i2);
        g.addVertex(i3);
        g.addVertex(i4);
        g.addVertex(i5);
        
        g.addEdge(i2, i1);
        g.addEdge(i3, i2);
        g.addEdge(i5, i4);
        g.addEdge(i3, i5);
        g.addEdge(i3, i1);
        
        WeightFunction<Integer> w = new DirectedGraphWeightFunction<>();
        
        w.setWeight(i2, i1, 1);
        w.setWeight(i3, i2, 2);
        w.setWeight(i5, i4, 3);
        w.setWeight(i3, i5, 4);
        w.setWeight(i3, i1, 10);
        
        ShortestPathTreeBuilder<Integer> shortestPathTreeBuilder = 
            DijkstraShortestPathTreeFinder.findShortestPathTreeFrom(g, w, i3);
        
        ShortestPath<Integer> path = shortestPathTreeBuilder.buildPathTo(i1);
        List<Integer> nodeList = path.getVertexList();
        
        assertEquals(3, nodeList.size());
        assertEquals(i3, nodeList.get(0));
        assertEquals(i2, nodeList.get(1));
        assertEquals(i1, nodeList.get(2));
        
        assertEquals(3.0, path.getPathWeight(w), 0.0001);
        
        path = shortestPathTreeBuilder.buildPathTo(i4);
        nodeList = path.getVertexList();
        
        assertEquals(3, nodeList.size());
        assertEquals(i3, nodeList.get(0));
        assertEquals(i5, nodeList.get(1));
        assertEquals(i4, nodeList.get(2));
        
        assertEquals(7.0, path.getPathWeight(w), 0.0001);
    }    
}
