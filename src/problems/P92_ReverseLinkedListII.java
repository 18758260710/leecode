package problems;

import java.util.Stack;
import problems.P2_AddTwoNumbers.ListNode;

public class P92_ReverseLinkedListII {

    //my solution1 1ms
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode temp = head;
        while (m > 1) {
            temp = temp.next;
            m--;
            n--;
        }
        ListNode temp2 = temp;
        Stack<Integer> re = new Stack<>();
        while (n > 0) {
            re.push(temp2.val);
            temp2 = temp2.next;
            n--;
        }
        while (!re.isEmpty()) {
            temp.val = re.pop();
            temp = temp.next;
        }

        return head;
    }

    //my solution2 0ms
    // 1 2 3 4 5 到 1 3 2 4 5 需要 1next变为3 2next变为4 3next变为2 三步
    // 1 3 2 4 5 到 1 4 3 2 5 需要 1next变为4 2next变为5 4next变为3 三步
    public ListNode reverseBetween4(ListNode head, int m, int n) {
        // Empty list
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode cur = head, prev = dummy;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        ListNode third = null;
        while (n > 1) {
            third = cur.next;
            cur.next = third.next;
            third.next = prev.next;
            prev.next = third;
            n--;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode temp = head;
        head.next = new ListNode(2);
        head = head.next;
        head.next = new ListNode(3);
        head = head.next;
        head.next = new ListNode(4);
        head = head.next;
        head.next = new ListNode(5);
        ListNode result = new P92_ReverseLinkedListII().reverseBetween4(temp, 2, 4);
        System.out.println(result);
    }

    // Object level variables since we need the changes
    // to persist across recursive calls and Java is pass by value.
    private boolean stop;
    private ListNode left;

    public void recurseAndReverse(ListNode right, int m, int n) {

        // base case. Don't proceed any further
        if (n == 1) {
            return;
        }

        // Keep moving the right pointer one step forward until (n == 1)
        right = right.next;

        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (m > 1) {
            this.left = this.left.next;
        }

        // Recurse with m and n reduced.
        this.recurseAndReverse(right, m - 1, n - 1);

        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this
        // point.
        if (this.left == right || right.next == this.left) {
            this.stop = true;
        }

        // Until the boolean stop is false, swap data between the two pointers
        if (!this.stop) {
            int t = this.left.val;
            this.left.val = right.val;
            right.val = t;

            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            this.left = this.left.next;
        }
    }
    //official solution1 递归 局部引用改变上层是不会变的 只扫描了一次
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        this.left = head;
        this.stop = false;
        this.recurseAndReverse(head, m, n);
        return head;
    }

    //official solution2 依次遍历顺序替换 有点类似与my solution2
    public ListNode reverseBetween3(ListNode head, int m, int n) {

        // Empty list
        if (head == null) {
            return null;
        }

        // Move the two pointers until they reach the proper starting point
        // in the list.
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;

        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        // Adjust the final connections as explained in the algorithm
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }

        tail.next = cur;
        return head;
    }
}
