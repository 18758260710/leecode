package problems;

public class P2_AddTwoNumbers {

      //Definition for singly-linked list.
      public static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }

      public ListNode mysolution(ListNode l1, ListNode l2) {
          ListNode result = new ListNode(0);
          ListNode temp = result;
          int jinwei=0;
          while (true) {
              if (l1!=null&&l2!=null) {
                  int sum = l1.val + l2.val+jinwei;
                  int val = sum % 10;
                  jinwei= sum / 10;
                  temp.next = new ListNode(val);
                  temp = temp.next;
                  l1=l1.next;
                  l2=l2.next;
              }else if (l1!=null){
                  int sum = l1.val+jinwei;
                  int val = sum % 10;
                  jinwei= sum / 10;
                  temp.next = new ListNode(val);
                  temp = temp.next;
                  l1=l1.next;
              }else if (l2!=null){
                  int sum = l2.val+jinwei;
                  int val = sum % 10;
                  jinwei= sum / 10;
                  temp.next = new ListNode(val);
                  temp = temp.next;
                  l2=l2.next;
              }else {
                  if (jinwei>0){
                      temp.next = new ListNode(jinwei);
                  }
                  return result.next;
              }

          }

      }

      // Official solution
      // don't know why it's slower than my solution
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
