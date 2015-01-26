package com.company;

import java.util.LinkedList;
import java.util.HashMap;

/**
 * Created by daljeetv on 1/25/15.
 */
public class Graph {

    LinkedList<Node> words;
    HashMap<Node, Boolean> wordsAlreadyInGraph;

    public class Node{
        String string;
        Node next;

        public Node(String str){
            string = str;
            next = null;
        }
    }



    public Graph()
    {
        words = new LinkedList<Node>();
        wordsAlreadyInGraph = new HashMap<Node, Boolean>();
    }

    public boolean containsWord(String word){
        return wordsAlreadyInGraph.containsKey(word);
    }

    public void addVertex(String word){
        if(!containsWord(word)){
            words.add(new Node(word));
            wordsAlreadyInGraph.put(new Node(word), true);
        }
    }

    public void addEdges(String word1, String word2){
        boolean isWord1InGraph = false;
        boolean isWord2InGraph = false;
        for(Node w: words){
            if(w.string == word1){
                isWord1InGraph = true;
                while(w.next != null){
                    w = w.next;
                }
                Node newWordNode = new Node(word2);
                w.next = newWordNode;
            }
        }
        for(Node w: words){
            if(w.string == word2){
                while(w.next != null){
                    isWord2InGraph = true;
                    w = w.next;
                }
                Node newWordNode = new Node(word2);
                w.next = newWordNode;
            }
        }
        if(isWord1InGraph == false){
            words.add(new Node(word1));
        }
        if(isWord2InGraph == false){
            words.add(new Node(word2));
        }
    }


}
