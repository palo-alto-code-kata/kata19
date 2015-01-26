package com.company;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
	// write your code here

    String word1 = "cat";
    String word2 = "dog";
    boolean canWeMakeTheWordChain = wordChain(word1, word2);

    }

    private static boolean wordChain(String startWord, String targetWord) {
        if(startWord.length() != targetWord.length()) throw new IllegalArgumentException("Length of the words are not the same");
        HashMap<String, Boolean> dictionary;
        currentDirectory();
        String file = "../words.txt";
        try {
            dictionary = createDictionary(file);
            Graph graph = generateEdges(generateVertices(file, startWord.length()));
            BFS(graph, startWord, targetWord);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;


    }

    private void BFS(Graph G, String start, String target) {
        final int INFINITY = Integer.MAX_VALUE;
        HashMap<Graph.Node, Boolean> = new HashMap<Graph.Node, Boolean>() // marked[v] = is there an s-v path
        String[] edgeTo = new String[G.words.size()]; ;      // edgeTo[v] = previous edge on shortest s-v path
        String[] distTo = new String[G.words.size()];      // distTo[v] = number of edges shortest s-v path


        LinkedList<Graph.Node> link = new LinkedList<Graph.Node>();
        for (int v = 0; v < G.words.size(); v++) distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    private static Graph generateEdges(Graph g) {
        for (Graph.Node w: g.wordsAlreadyInGraph.keySet()) {
            for(Graph.Node l: g.wordsAlreadyInGraph.keySet()){
                if(matchesAlmost(w.string, l.string)){
                    g.addEdges(w.string, l.string);
                    System.out.println(w.string + " " + l.string);
                }
            }
        }
        return g;
    }

    public static boolean matchesAlmost(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;
        int same = 0;
        for (int i = 0; i < str1.length(); ++i) {
            if (str1.charAt(i) == str2.charAt(i))
                same++;
        }
        return same == str1.length() - 1;
    }

    private static void currentDirectory() {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
    }

    public static HashMap<String, Boolean> createDictionary(String file) throws IOException {
        BufferedReader br;
        br = new BufferedReader(new FileReader(file));
        String line;
        HashMap<String, Boolean> dictionary = new HashMap<String, Boolean>();
        while ((line = br.readLine()) != null) {
            dictionary.put(line, true);
            //System.out.println(line);
        }
        return dictionary;
    }

    public static Graph generateVertices(String file, int length) throws IOException {
        Graph g = new Graph();
        BufferedReader br;
        br = new BufferedReader(new FileReader(file));
        String line;
        while (((line = br.readLine()) != null)) {
            if(line.length() == length){
                g.addVertex(line);
                System.out.println(line);
            }
        }
        return g;
    }


}
