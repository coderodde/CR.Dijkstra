package com.yourcompany.graph.impl;

import java.util.Collection;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class DirectedGraphTest {

    private  DirectedGraph<Integer> graph = new DirectedGraph<>();
    
    private final Integer i1 = Integer.valueOf(1);
    private final Integer i2 = Integer.valueOf(2);
    private final Integer i3 = Integer.valueOf(3);
    private final Integer i4 = Integer.valueOf(4);
    
    @Before
    public void before() {
        graph = new DirectedGraph<>();
    }
    
    @Test
    public void testAddEdge() {
        assertTrue(graph.addEdge(i1, i2));
        assertFalse(graph.addEdge(i1, i2));
        
        assertTrue(graph.addEdge(i2, i1));
        assertFalse(graph.addEdge(i2, i1));
        
        assertTrue(graph.addEdge(i2, i3));
        assertFalse(graph.addEdge(i2, i3));
        
        assertTrue(graph.addEdge(i4, i1));
        assertFalse(graph.addEdge(i4, i1));
    }

    @Test
    public void testRemoveEdge() {
        assertTrue(graph.addEdge(i1, i2));
        assertTrue(graph.removeEdge(i1, i2));
        assertFalse(graph.removeEdge(i1, i2));
        assertFalse(graph.removeEdge(i1, i3));
    }

    @Test
    public void testAddVertex() {
        assertTrue(graph.addVertex(i4));
        assertTrue(graph.hasVertex(i4));
        assertFalse(graph.addVertex(i4));
        assertTrue(graph.hasVertex(i4));
    }

    @Test
    public void testRemoveVertex() {
        assertFalse(graph.hasVertex(i2));
        assertTrue(graph.addVertex(i2));
        assertTrue(graph.hasVertex(i2));
        assertTrue(graph.removeVertex(i2));
        assertFalse(graph.removeVertex(i2));
        assertFalse(graph.hasVertex(i2));
    }

    @Test
    public void testGetParentVerticesOf() {
        graph.addEdge(i1, i2);
        graph.addEdge(i1, i3);
        
        graph.addEdge(i4, i1);
        graph.addEdge(i3, i1);
        
        Collection<Integer> parents = graph.getParentVerticesOf(i1);
        
        assertEquals(2, parents.size());
        assertTrue(parents.contains(i4));
        assertTrue(parents.contains(i3));
    }

    @Test
    public void testGetChildVerticesOf() {
        graph.addEdge(i1, i2);
        graph.addEdge(i1, i3);
        
        graph.addEdge(i4, i1);
        graph.addEdge(i3, i1);
        
        Collection<Integer> children = graph.getChildVerticesOf(i1);
        
        assertEquals(2, children.size());
        assertTrue(children.contains(i2));
        assertTrue(children.contains(i3));
    }

    @Test
    public void testHasEdge() {
        assertFalse(graph.hasEdge(i1, i2));
        assertTrue(graph.addEdge(i1, i2));
        assertTrue(graph.hasEdge(i1, i2));
        assertTrue(graph.removeEdge(i1, i2));
        assertFalse(graph.hasEdge(i1, i2));
    }

    @Test
    public void testGetVertices() {
        graph.addVertex(i1);
        graph.addVertex(i2);
        graph.addVertex(i4);
        
        Collection<Integer> vertices = graph.getVertices();
        
        assertEquals(3, vertices.size());
        
        assertTrue(vertices.contains(i1));
        assertTrue(vertices.contains(i2));
        assertTrue(vertices.contains(i4));
        
        assertFalse(vertices.contains(i3));
    }    
}
