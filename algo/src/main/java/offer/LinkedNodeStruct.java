package offer;

import java.util.ArrayList;
import static offer.OfferUtils.*;
/**
 * @description:
 * @author: longlonglv
 * @date: 2022/5/22
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

        ListNode t1 = mergeTwoLists(l1, l4);
        printLinkedNode(t1);
    }

    /**
     * offer 06
     * */
    public static int[] reversePrint(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        reversePrintImp(list, head);
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }
    public static void reversePrintImp(ArrayList<Integer> nums, ListNode node) {
        if (node == null) {
            return;
        }
        reversePrintImp(nums, node.next);
        nums.add(node.val);
    }

    /**
     * offer 24
     * */
    public static ListNode reverseList(ListNode head) {
        ListNode pre = new ListNode(0);

        while (head != null) {
            ListNode next = head.next;
            head.next = pre.next;
            pre.next = head;

            head = next;
        }
        return pre.next;
    }

    /**
     * offer 25
     * */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        pre.next = l1;
        ListNode helper = pre;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                ListNode next = l2.next;

                l2.next = l1;
                helper.next = l2;

                helper = l2;
                l2 = next;
            } else {
                l1 = l1.next;
                helper = helper.next;
            }
        }
        if (l1 == null) {
            helper.next = l2;
        }
        return pre.next;
    }

    /**
     * offer 18
     * */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode helper = pre;

        while (head != null && head.val != val) {
            head = head.next;
            pre = pre.next;
        }

        if (head == null) {
            return null;
        }
        pre.next = head.next;
        return helper.next;
    }


    /**
     * offer 22
     * */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode helper = head;
        while (k > 0) {
            head = head.next;
            k--;
        }
        while (head != null) {
            head = head.next;
            helper = helper.next;
        }
        return helper;
    }


    /**
     * offer 52
     * */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = 0;
        int len2 = 0;

        ListNode l1 = headA;
        ListNode l2 = headB;
        while (headA != null) {
            headA = headA.next;
            len1++;
        }
        while (headB != null) {
            headB = headB.next;
            len2++;
        }
        headA = l1;
        headB = l2;
        if (len1 > len2) {
            while (len1 > len2) {
                headA = headA.next;
                len1--;
            }
        } else {
            while (len2 > len1) {
                headB = headB.next;
                len2--;
            }
        }
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }
}

