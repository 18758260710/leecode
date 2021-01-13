package problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2020/1/14.
 */
public class P212_WordSearchII {
    //循环dfs slow
    int length,width;
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> temp = new HashSet<>();
        length = board.length;
        width = board[0].length;
        for (String word:words){
            int index = 0;
            for (int i=0;i<length;i++){
                for (int j=0;j<width;j++){
                    if (word.charAt(index)==board[i][j]){
                        board[i][j]='#';
                        if (dfs(word,1,board,i,j))temp.add(word);
                        board[i][j]=word.charAt(index);
                    }
                }
            }
        }
        result.addAll(temp);
        return result;
    }

    private boolean dfs(String word, int index, char[][] board,int i,int j) {
        if (index == word.length())return true;
        if (i>0 && board[i-1][j]==word.charAt(index)){
            board[i-1][j]='#';
            if (dfs(word,index+1,board,i-1,j)){
                board[i-1][j]=word.charAt(index);
                return true;
            }
            board[i-1][j]=word.charAt(index);
        }
        if (j>0 && board[i][j-1]==word.charAt(index)){
            board[i][j-1]='#';
            if (dfs(word,index+1,board,i,j-1)){
                board[i][j-1]=word.charAt(index);
                return true;
            }
            board[i][j-1]=word.charAt(index);
        }
        if (i<length-1 && board[i+1][j]==word.charAt(index)){
            board[i+1][j]='#';
            if (dfs(word,index+1,board,i+1,j)){
                board[i+1][j]=word.charAt(index);
                return true;
            }
            board[i+1][j]=word.charAt(index);
        }
        if (j<width-1 && board[i][j+1]==word.charAt(index)){
            board[i][j+1]='#';
            if (dfs(word,index+1,board,i,j+1)){
                board[i][j+1]=word.charAt(index);
                return true;
            }
            board[i][j+1]=word.charAt(index);
        }
        return false;
    }

    public int[] dx = {0, 0, -1, 1};
    public int[] dy = {1, -1, 0, 0};

    //使用字典树优化
    public List<String> findWords2(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();

        //build tree
        TrieTree trieTree = new TrieTree();
        for (String word : words) {
            trieTree.insert(word);
        }

        //遍历board上每个点
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //剪枝如果这个字符是words中某个单词的前缀
                dfs(board, i, j, trieTree.root, res);
            }
        }
        return res;
    }

    private void dfs(char[][] board, int x, int y, TrieNode cur, List<String> res) {
        char c = board[x][y];
        //如果是访问过的(我们设定为数字'0')，或者是不在字典内的，直接return
        if (c == '0' || cur.children[c - 'a'] == null) {
            return;
        }
        //如果是单词了，直接压入
        TrieNode child = cur.children[c - 'a'];
        if (child.word != null) {
            if (!res.contains(child.word)) {
                res.add(child.word);
            }
            //在这里不要找到了就return，而是继续走，因为这个单词可能也是某个单词的前缀，比如snow 是snowman的前缀，我们还要继续找snowman
        }
        //标记为被访问过
        board[x][y] = '0';    //只要不是字母都行～
        //遍历四个方向
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isValid(board, nx, ny)) {
                dfs(board, nx, ny, child, res);
            }
        }
        //状态重置
        board[x][y] = c;
    }

    private boolean isValid(char[][] board, int x, int y) {
        int n = board.length;
        int m = board[0].length;
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        }
        return false;
    }

    //这个题用hashmap实现更好把,这里简写了
    class TrieNode{
        public TrieNode[] children = new TrieNode[26];
        public String word = null;     //加了一维，方便添加到结果集
    }

    class TrieTree{
        private TrieNode root;

        public TrieTree() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            char[] chs = word.toCharArray();

            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new TrieNode();
                }
                cur = cur.children[index];
            }
            cur.word = word;
        }
    }
}
