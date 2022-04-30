package hot;

import static hot.HotUtils.*;

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

//        test 19
//        ListNode t1 = removeNthFromEnd(l1, 3);
//        HotUtils.printLinkedNode(t1);

//        test21
//        ListNode t1 = mergeTwoLists(l1, l4);
//        printLinkedNode(t1);

//        test23
        ListNode[] lists = {l1, l4};
        ListNode t1 = mergeKLists(lists);
        printLinkedNode(t1);
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

    /**
     * LeetCode 19
     * 删除倒数节点
     * */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode helper = pre;

        ListNode first = head;
        ListNode second = head;

        while (n > 0 && first != null) {
            first = first.next;
            n--;
        }

        while (first != null && second != null) {
            first = first.next;
            helper = helper.next;
            second = second.next;
        }

        helper.next = helper.next.next;

        return pre.next;
    }


    /**
     * LeetCode 21
     * 这是一个很常见的链表基础题，必须掌握并且很快地写出来
     * */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode pre = new ListNode(0);
        ListNode helper = pre;
        pre.next = list1;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                helper = list1;
                list1 = list1.next;
            } else {
                ListNode next = list2.next;
                list2.next = list1;
                helper.next = list2;

                helper = list2;
                list2 = next;
            }
        }

        if (list1 == null) {
            helper.next = list2;
        }

        return pre.next;
    }


    /**
     * LeetCode 23
     * merge ListNode升级版，可以使用mergeTwoLists作为base
     * */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return mergeKListsImpl(lists, 0, lists.length - 1);
    }

    public static ListNode mergeKListsImpl(ListNode[] lists, int low, int high) {
        if (low == high) {
            return lists[low];
        }
        int mid = (low + high) / 2;
        ListNode l1 = mergeKListsImpl(lists, low, mid);
        ListNode l2 = mergeKListsImpl(lists, mid + 1, high);
        return mergeTwoLists(l1, l2);
    }
}

