package it.unibo.generics.graph.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {
    private final Map<N, Set<N>> edges;

    public GraphImpl() {
        edges = new HashMap<>();
    }

    public void addNode(final N node) {
        this.edges.putIfAbsent(node, new HashSet<>());
    }

    public void addEdge(final N source, final N target) {
        Set<N> sourceEdges = this.edges.get(source);
        if (sourceEdges == null) {
            addNode(source);
            sourceEdges = new HashSet<>();
        }
        sourceEdges.add(target);
        this.edges.put(source, sourceEdges);
    }
    
    public Set<N> nodeSet() {
        return this.edges.keySet();
    }
    
    public Set<N> linkedNodes(final N node) {
        return this.edges.get(node);
    }
    
    public List<N> getPath(final N source, final N target) {
        // using BFS
        // visited tracks the already visited nodes
        final Set<N> visited = new HashSet<>();
        // parents track through which node we get to the node inserted as key
        final Map<N, N> parents = new HashMap<>();
        // queue is needed for the BFS to keep track of nodes to discover
        final Queue<N> queue = new LinkedList<>();
        
        // we add the source to the visited nodes and to the queue
        visited.add(source);
        queue.add(source);
        // the source has no parents
        parents.put(source, null);

        // cycles until the queue is empty
        while (queue.size() != 0) {
            // we poll a node and we visit it
            final N current = queue.poll();
            
            // for each neighbour of the current node
            for (final N neighbour : this.linkedNodes(current)) {
                // if the neighbour wasn't visited yet
                if (!visited.contains(neighbour)) {
                    // we insert the current node as parent of the neighbour
                    parents.put(neighbour, current);
                    // we add the neighbour to the queue
                    queue.add(neighbour);
                }
            }
            // we add the current node to the visited nodes
            visited.add(current);
        }

        // LinkedList to output
        LinkedList<N> output = new LinkedList<>();
        // temporary node used to scroll through the parents of the target node
        N tmp = target;
        // cycling until the parent of the current node is null
        // this either means that we got to the source or that there is no path
        while (parents.get(tmp) != null) {
            // adding to the top of the list in order to represent the correct order
            output.addFirst(tmp);
            // scrolling through parents
            tmp = parents.get(tmp);
        }
        
        if (tmp == source) {
            // adding the last node (source)
            output.addFirst(tmp);
        } else {
            // we finished the cycle without getting to the source
            // => there is no path from source to destination
            output = null;
        }
        return output;
    }
}
