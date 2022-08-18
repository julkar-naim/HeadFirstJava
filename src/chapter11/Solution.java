/*
Learned a lot about java data structures
- forEach loop
- HashMap, TreeMap
- HashSet, TreeSet
- Lambda, Streams, predicate, function pointer
- Mutability
 */

package chapter11;

import java.util.*;

class NodeList {
    TreeSet<String> parent;
    TreeSet<String> child;

    NodeList() {
        parent = new TreeSet<String>();
        child = new TreeSet<String>();
    }

    @Override
    public String toString() {
        return "NodeList{" +
                "parent=" + parent +
                ", child=" + child +
                '}';
    }
}

class Graph {
    Map<String, NodeList> adjList;
    Map<String, Integer> depth;

    Graph () {
        adjList = new TreeMap<String, NodeList>();
        depth = new TreeMap<String, Integer>();
    }

    void addNewNode(String node) {
        adjList.put(node, new NodeList());
    }

    void makeConnection(String u, String v, boolean bidirectional) {
        if (!adjList.containsKey(u))
            addNewNode(u);
        if (!adjList.containsKey(v))
            addNewNode(v);
        adjList.get(u).child.add(v);
        if (bidirectional)
            adjList.get(v).child.add(u);

    }

    void allShortestPath(String source, String destination) {
        Set<String> visited = new TreeSet<>();

        Queue<String> Q = new LinkedList<>();
        Q.add(source);
        setDepth(source, 0);

        while(!Q.isEmpty()) {
            String curNode = Q.poll();

            visited.add(curNode);

            if (curNode.equals(destination))
                continue;

            adjList.get(curNode).child.forEach(childNode -> {
                int d = getDepth(childNode);
                int curDepth = getDepth(curNode) + 1;
                if (curDepth < d) {
                    setDepth(childNode, curDepth);
                    adjList.get(childNode).parent.clear(); // so this is the culprit
                    adjList.get(childNode).parent.add(curNode);
                }
                else if (curDepth == d) {
                    adjList.get(childNode).parent.add(curNode);
                }
                if (!visited.contains(childNode))
                    Q.add(childNode);
            });
        }
    }

    void buildPath(List<List<String>> paths, LinkedList<String> path, String node, String source) {
        path.addFirst(node);
        if (node.equals(source)) {
            paths.add(new LinkedList<String>(path));
            path.removeFirst();
            return;
        }
        adjList.get(node).parent.forEach(p -> buildPath(paths, path, p, source));
        path.removeFirst();
    }

    int getDepth(String node) {
        if (depth.containsKey(node))
            return depth.get(node);
        return Integer.MAX_VALUE;
    }

    void setDepth(String node, int d) {
        depth.put(node, d);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "adjList=" + adjList +
                '}';
    }

}

public class Solution {

    // constant
    boolean connectable(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                count++;
        }
        return count <= 1;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> paths = new LinkedList<>();
        Graph graph = new Graph();

        // O(n2)
        wordList.forEach(word -> {
            wordList.forEach(check -> {
                if (word.equals(check)) return;
                if (connectable(word, check))
                    graph.makeConnection(word, check, true);
            });
        });

        // O(n)
        wordList.forEach(word -> {
            if (connectable(beginWord, word))
                graph.makeConnection(beginWord, word, false);
        });

        if (!graph.adjList.containsKey(endWord)) return paths;

        graph.allShortestPath(beginWord, endWord);

        LinkedList<String> path = new LinkedList<>();

        graph.buildPath(paths, path, endWord, beginWord);

        return paths;
    }
}

