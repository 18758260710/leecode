package problems;

import java.util.Iterator;

/**
 * Created by Administrator on 2020/3/14.
 */
public class PeekingIterator_284 {
    class PeekingIterator implements Iterator<Integer> {
        Integer current;
        Iterator<Integer> iterator;
        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator=iterator;
            if (iterator.hasNext())current=iterator.next();
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return current;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            int res = current;
            if (iterator.hasNext())current=iterator.next();
            else current=null;
            return res;
        }

        @Override
        public boolean hasNext() {
            return current!=null;
        }
    }
}
