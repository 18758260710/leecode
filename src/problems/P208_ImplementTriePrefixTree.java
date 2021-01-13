package problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/1/13.
 */
public class P208_ImplementTriePrefixTree {
    //solution1 63ms
    class Trie {
        TrieNode root;
        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode(1);
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            char[] chars = word.toCharArray();
            TrieNode current = root;
            for (int c:chars){
                if (!current.next.containsKey(c)) {
                    current.next.put(c,new TrieNode(c));
                }
                current = current.next.get(c);
            }
            current.end=true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            char[] chars = word.toCharArray();
            TrieNode current = root;
            for (int c:chars){
                if (!current.next.containsKey(c)) {
                    return false;
                }
                current = current.next.get(c);
            }
            return current.end;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();
            TrieNode current = root;
            for (int c:chars){
                if (!current.next.containsKey(c)) {
                    return false;
                }
                current = current.next.get(c);
            }
            return true;
        }

        class TrieNode{
            Integer val;
            Map<Integer,TrieNode> next = new HashMap<>();
            Boolean end = false;

            public TrieNode( Integer val) {
                this.val =val;
            }
        }
    }

    //数组代替map
    class Trie2{
        class TrieNode{
            public TrieNode[] children = new TrieNode[26];
            public String item = "";
            public TrieNode(){

            }
        }

        private TrieNode root;
        public Trie2(){
            root = new TrieNode();
        }
        /** Inserts a word into the trie. */
        public void insert(String word){
            TrieNode node = root;
            int index = 0;
            while(index < word.length()){
                if(node.children[word.charAt(index) - 'a'] == null){
                    node.children[word.charAt(index) - 'a'] = new TrieNode();
                }
                node = node.children[word.charAt(index) - 'a'];
                index ++;

            }
            node.item = word;
        }
        /** Returns if the word is in the trie. */
        public boolean search(String word){
            TrieNode node = root;
            int index = 0;
            while(index < word.length()){
                if(node.children[word.charAt(index) - 'a'] == null){
                    return false;
                }
                node = node.children[word.charAt(index) - 'a'];
                index ++;

            }
            return node.item.equals(word);
        }
        /** Returns if the word is in the trie. */
        public boolean startsWith(String prefix){
            TrieNode node = root;
            int index = 0;
            while(index < prefix.length()){
                if(node.children[prefix.charAt(index) - 'a'] == null){
                    return false;
                }
                node = node.children[prefix.charAt(index) - 'a'];
                index ++;

            }
            return true;
        }
    }
}
