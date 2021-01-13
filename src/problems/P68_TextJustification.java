package problems;

import java.util.ArrayList;
import java.util.List;

public class P68_TextJustification {

    //my solution1 2ms slow   but it's a boring problem
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int count = maxWidth;
        List<String> temp = new ArrayList<>();
        for (String word : words) {
            if (count - word.length() < 0) {
                count += temp.size();

                while (count > 0) {
                    if (temp.size() == 1) {
                        temp.set(0, temp.get(0) + " ");
                        count--;
                        continue;
                    }
                    for (int i = 0; i < temp.size() - 1; i++) {
                        if (count <= 0) {
                            break;
                        }
                        temp.set(i, temp.get(i) + " ");
                        count--;
                    }
                }

                result.add(String.join("", temp));

                count = maxWidth;
                temp.clear();

                count -= word.length() + 1;
                temp.add(word);
            } else {
                count -= word.length() + 1;
                temp.add(word);
            }
        }
        count += temp.size();
        for (int i = 0; i < temp.size() - 1; i++) {
            temp.set(i, temp.get(i) + " ");
            count--;
        }
        while (count > 0) {
            temp.set(temp.size() - 1, temp.get(temp.size() - 1) + " ");
            count--;
        }
        result.add(String.join("", temp));

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new P68_TextJustification()
            .fullJustify(new String[]{"What", "must", "be", "acknowledgment", "shall", "be"}, 16));
    }

    //0ms
    public List<String> fullJustify2(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        //当前行长度
        int currentSize = 0;
        int startIndex = 0;
        int endIndex = 0;
        for(int i=0; i<words.length; i++) {
            currentSize += words[i].length();
            if(currentSize > maxWidth) {
                endIndex = i-1;
                output(result, words, startIndex, endIndex, maxWidth);
                startIndex = i;
                endIndex = i;
                currentSize = words[i].length();
            }
            else
                endIndex=i;
            currentSize++;
        }
        //output last line
        output(result, words, startIndex, endIndex, maxWidth);
        return result;
    }

    public void output(List<String> result, String[] words, int startIndex, int endIndex, int maxWidth) {
        int count = endIndex-startIndex+1;
        int original = count;
        int size = 0;
        for (int i=startIndex; i<=endIndex; i++) {
            size += words[i].length();
        }
        //空格数量
        int spaces = maxWidth-size;
        int interval = 0;
        int residual = 0;
        if(count>1) {
            interval = spaces/(count-1);
            residual = spaces%(count-1);
        }
        if(endIndex == words.length-1) {
            interval = 1;
            residual = 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(words[startIndex++]);
        count--;
        while (count>0) {
            int extra = (residual>0) ? 1:0;
            for(int i=0; i<interval+extra;i++)
                sb.append(" ");
            residual--;
            sb.append(words[startIndex++]);
            count--;
        }
        if (interval == 0) {
            for(int i=0; i<maxWidth-size;i++)
                sb.append(" ");
        }

        if(endIndex == words.length-1) {
            for(int i=0; i<maxWidth-(size+original-1);i++)
                sb.append(" ");
        }
        result.add(sb.toString());

    }
}
