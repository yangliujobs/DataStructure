// https://leetcode.com/problems/reverse-linked-list-ii/

/**
Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // Like No.25 Reverse Nodes in K Group
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) return head;
        
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        dummy.next = head;
        
        int diff = n - m;
        while (m-- > 1) {
            prev = head;
            head = head.next;
        }

        while (diff-- > 0) {
            ListNode t = head.next.next;
            head.next.next = prev.next;
            prev.next = head.next;
            head.next = t;
        }
        
        return dummy.next;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // 0ms
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return null;
        
        // Move the two pointers until they reach the proper starting point
        ListNode cur = head, prev = null;
        
        while (m-- > 1) {
            prev = cur;
            cur = cur.next;
            n--;
        }
        
        // Two pointers will fix the final connections
        ListNode con = prev, tail = cur;
        
        while (n-- > 0) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }

        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }
        tail.next = cur;
        
        return head;
    }
}
