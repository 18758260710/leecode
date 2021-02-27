package problems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wengtao
 * @date 2021/2/27
 **/
public class P341_FlattenNestedListIterator {
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    class NestedIterator implements Iterator<Integer> {
        List<Integer> list;
        Iterator<Integer> iterator;

        public NestedIterator(List<NestedInteger> nestedList) {
            list = new ArrayList<>();
            build(nestedList);
            iterator = list.iterator();
        }

        private void build(List<NestedInteger> nestedList) {
            for (NestedInteger nestedInteger : nestedList) {
                if (nestedInteger.isInteger()){
                    list.add(nestedInteger.getInteger());
                }else {
                    build(nestedInteger.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }
    }
}
