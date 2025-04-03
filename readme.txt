
QUESTIONS

1.	Does the solution work for larger graphs? 
	Yes, the approach scales well as it is linear in time complexity.
	
2.	Can you think of any optimizations? 
	If the graph is very large, using parallel processing for topological sort or longest path updates could speed it up.
	
3.	What’s the computational complexity of your solution? 
	O(V+E) due to topological sorting and single-pass DP updates.
	
4.	Are there any unusual cases that aren't handled? 
	The solution assumes that the input is a valid DAG (not checking for cycles). We can add cycle detection if needed.
	
	
Solution Approach: 

1) Graph Representation: The DAG is represented using an adjacency list.

2) Topological Sorting: The graph is sorted using Kahn’s Algorithm to process vertices in order.

3) Dynamic Programming: A longest path array is updated as vertices are processed in topological order.

4) Time Complexity: The solution runs in O(V + E) time, making it efficient for large graphs.

Implementation:

The solution is implemented in Java, using classes for Vertex, Edge, and DAG. The algorithm first performs topological sorting and then computes the longest path using a dynamic approach.



Manual Testing : 

public class Main {
    public static void main(String[] args) {
        DAG graph = new DAG();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        graph.addEdge(v1, v2);
        graph.addEdge(v2, v3);
        graph.addEdge(v3, v4);

        System.out.println("Longest Path from 1: " + graph.findLongestPath(v1)); // Expected: 3
    }
}