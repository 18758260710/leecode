package problems;

/**
 * Created by Administrator on 2020/1/13.
 */
public class P211_AddandSearchWordDatastructuredesign {
    //50ms
    class WordDictionary {
        private TrieNode root;
        /** Initialize your data structure here. */
        public WordDictionary() {
            root = new TrieNode();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            TrieNode node = root;
            int index = 0;
            while(index < word.length()){
                if(node.children[word.charAt(index) - 'a'] == null){
                    node.children[word.charAt(index) - 'a'] = new TrieNode();
                }
                node = node.children[word.charAt(index) - 'a'];
                index ++;
            }
            node.end=true;
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return dfs(word,root,0);
        }

        public boolean dfs(String word,TrieNode node,int index){
            if (node==null)return false;
            if(index == word.length())return node.end;
            if (word.charAt(index)=='.'){
                for (TrieNode aa: node.children){
                    if (dfs(word,aa,index+1))return true;
                }
                return false;
            }
            return dfs(word,node.children[word.charAt(index) - 'a'],index+1);
        }

        class TrieNode{
            TrieNode[] children = new TrieNode[26];
            Boolean end = false;
        }
    }
}
