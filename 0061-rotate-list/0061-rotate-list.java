class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode curr = head;
        int length = 1;

        while (curr.next != null) {
            curr = curr.next;
            length++;
        }

        k %= length;
        if (k == 0) return head;

        curr.next = head;

        int steps = length - k;
        ListNode newTail = curr;

        while (steps > 0) {
            newTail = newTail.next;
            steps--;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}