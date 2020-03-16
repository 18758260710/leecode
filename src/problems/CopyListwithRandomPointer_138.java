package problems;


import java.util.HashMap;
import java.util.Map;

public class CopyListwithRandomPointer_138 {
    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    //my solution1 1ms
    Map<Node,Node> pair = new HashMap<>();
    public Node copyRandomList(Node head) {
        if (head==null)return null;
        if (pair.containsKey(head))return pair.get(head);
        Node result = new Node(head.val,null,null);
        pair.put(head,result);
        result.next = copyRandomList(head.next);
        result.random = copyRandomList(head.random);
        return result;
    }

    //0ms 但这个的前提是不成环
    public Node copyRandomList2(Node head) {

        Node curr = head;
        Node next;
        // clone except for random pointers. Interweave alongside
        while (curr != null) {
            next = curr.next;
            Node copy = new Node(curr.val, null, null);
            curr.next = copy;
            copy.next = next;
            curr = next;
        }

        // link random
        curr = head;
        while (curr != null) {
            if (curr.random != null)
                curr.next.random = curr.random.next;
            curr = curr.next.next;
        }

        // split list into original and clone
        curr = head;
        Node dummyHead = new Node();
        Node cloneTail = dummyHead; // always points to tail of clone
        Node cloneCurr;
        while (curr != null) {
            // save nodes before breaking links
            next = curr.next.next; // orig's next
            cloneCurr = curr.next; // clone's current

            // extract clone list
            cloneTail.next = cloneCurr;
            cloneTail = cloneCurr;

            // extract original
            curr.next = next;
            curr = next;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        Node a = new Node(1,null,null);
        Node b = new Node(2,null,null);
        Node c = new Node(3,null,null);
        a.next=b;
        b.next=c;
        c.random=a;
        Node re = new CopyListwithRandomPointer_138().copyRandomList2(a);
        System.out.println(re);
    }
}
