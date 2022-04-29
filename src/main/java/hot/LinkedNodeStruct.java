package hot;

/**
 * @description: 链表结构
 * @author: longlonglv
 * @date: 2022/4/29
 */

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class LinkedNodeStruct {
    public static void main(String[] args) {
        System.out.println("hello world");
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(3);
        l1.next = l2;
        ListNode l3 = new ListNode(5);
        l2.next = l3;

        ListNode l4 = new ListNode(2);
        ListNode l5 = new ListNode(4);
        l4.next = l5;
        ListNode l6 = new ListNode(6);
        l5.next = l6;

        // test t2
//        ListNode t1 = addTwoNumbers(l1, l4);
//        HotUtils.printLinkedNode(t1);


    }

    /**
     * leetcode2
     * 直接进行相加即可
     * */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode helper = pre;

        int carry = 0;
        while (l1 != null && l2 != null) {
            int temp = l1.val + l2.val + carry;
            int v1 = temp % 10;
            carry = temp / 10;

            ListNode cur = new ListNode(v1);
            helper.next = cur;
            helper = helper.next;

            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int temp = l1.val + carry;
            int v1 = temp % 10;
            carry = temp / 10;

            ListNode cur = new ListNode(v1);
            helper.next = cur;
            helper = helper.next;

            l1 = l1.next;
        }

        while (l2 != null) {
            int temp = l2.val + carry;
            int v1 = temp % 10;
            carry = temp / 10;

            ListNode cur = new ListNode(v1);
            helper.next = cur;
            helper = helper.next;

            l2 = l2.next;
        }

        if (carry > 0) {
            ListNode cur = new ListNode(carry);
            helper.next = cur;
            helper = helper.next;
        }

        return pre.next;
    }
}
