package app;

import graph.EdgeArrayGraphImpl;
import graph.Graph;

import java.util.ArrayList;
import java.util.List;

public class Exercises<T>{

    // c) ii)
    public void printGraph(Graph<T> graph){
        List<T> vertices = graph.getVertexes();
        String vertexes = "";
        String edges = "";
        for (T vertex : vertices) {
            vertexes += vertex + ", ";
            for (T adj : graph.getAdjacencyList(vertex)) {
                edges += "(" + vertex + ", " + adj + "), ";
            }
        }
        System.out.println("Vertexes: " + vertexes);
        System.out.println("Edges: " + edges);
    }

    // d) i)
    public int sourceVertexes(Graph<T> graph){
        List<T> vertices = graph.getVertexes();
        int sourceVertexes = 0;
        for (T vertex : vertices) {
            if (graph.getAdjacencyList(vertex).size() == 0) {
                sourceVertexes++;
            }
        }
        return sourceVertexes;
    }

    // d) ii)
    public List<T> sinkVertexes(Graph<T> graph){
        List<T> sinkVertexes = new ArrayList<>();
        for (T sinkVertex:graph.getVertexes()) {
            boolean isSink = true;
            for (T vertex:graph.getVertexes()) {
                if (graph.hasEdge(sinkVertex,vertex)) isSink = false;
            }
            if (isSink) sinkVertexes.add(sinkVertex);
        }
        return sinkVertexes;
    }

    // d) iii)
    public List<T> vertexDistanceTwoOrLess(T v, Graph<T> graph){
        List<T> vertexDistanceTwoOrLess = new ArrayList<>();
        graph.getAdjacencyList(v).forEach(vertex -> {
            if (!vertex.equals(v)) vertexDistanceTwoOrLess.add(vertex);
            graph.getAdjacencyList(vertex).forEach(vertex2 -> {
                if (!vertex2.equals(v)) vertexDistanceTwoOrLess.add(vertex2);
            });
        });
        return vertexDistanceTwoOrLess;
    }

    // d) iv)
    public boolean isStronglyConnected(Graph<T> graph){
        if (graph.order() == 0) return false;
        for (T vertex:graph.getVertexes()) {
            List<T> visited = new ArrayList<>();
            DFS(vertex,visited,graph);
            for (T vertex2:graph.getVertexes()) {
                if (!visited.contains(vertex2) && vertex2 != vertex) return false;
            }
        }
        return true;
    }

    private List<T> DFS(T v, List<T> visited, Graph<T> graph){
        List<T> adjacencyList = graph.getAdjacencyList(v);
        visited.add(v);
        for (int i = 0; i < adjacencyList.size(); i++) {
            T currentV = adjacencyList.get(i);
            if (!visited.contains(currentV)) {
                List<T> list = DFS(currentV, visited, graph);
                for (int j = 0; j < list.size(); j++) {
                    T currentV2 = list.get(i);
                    if (!visited.contains(currentV2)) {
                        visited.add(currentV2);
                    }
                }
            }
        }
        return visited;
    }


    // e)
    public boolean[][] warshall(Graph<T> graph){
        boolean[][] warshall = new boolean[graph.order()][graph.order()];
        for (int i = 0; i < graph.order(); i++) {
            for (int j = 0; j < graph.order(); j++) {
                if (graph.hasEdge(graph.getVertexes().get(i), graph.getVertexes().get(j))) warshall[i][j] = true;
            }
        }
        for (int k = 0; k < graph.order(); k++) {
            for (int i = 0; i < graph.order(); i++) {
                for (int j = 0; j < graph.order(); j++) {
                    warshall[i][j] = warshall[i][j] || (warshall[i][k] && warshall[k][j]);
                }
            }
        }
        return warshall;
    }

    // f)
    public boolean application(int[][] edges, T v, T w){
        Graph<T> graph = new EdgeArrayGraphImpl<>();
        for (int[] edge : edges) {
            graph.addEdge(graph.getVertexes().get(edge[0]), graph.getVertexes().get(edge[1]));
        }
        boolean[][] warshall = warshall(graph);
        return warshall[graph.getVertexes().indexOf(v)][graph.getVertexes().indexOf(w)];
    }



}
