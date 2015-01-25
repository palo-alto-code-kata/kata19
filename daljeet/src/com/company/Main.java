package com.company;


public class Main {

    public static void main(String[] args) {
	// write your code here

    String word1 = "cat";
    String word2 = "dog";
    boolean canWeMakeTheWordChain = wordChain(word1, word2);

    }

    private static boolean wordChain(String startWord, String targetWord) {

        Graph g = generateGraph(startWord, targetWord);


    }


    public Graph generateGraph(String start, String target){
        Graph g = new Graph();
        for(int i = 0; i<start.length(); i++){

        }
    }
}
