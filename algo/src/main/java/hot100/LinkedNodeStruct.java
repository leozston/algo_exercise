package hot100;

import static hot100.HotUtils.*;

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
        ListNode l1 = new ListNode(3);
        ListNode l2 = new ListNode(1);
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
//        ListNode[] lists = {l1, l4};
//        ListNode t1 = mergeKLists(lists);
//        printLinkedNode(t1);

//        test 141
//        ListNode r = detectCycle(l1);
//        System.out.println(r.val);

//        test 148
//        ListNode r1 = sortList(l1);
//        printLinkedNode(r1);

//        test 206
        ListNode t1 = reverseList(l1);
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

    /**
     * LeetCode 141
     * 常见题
     * */
    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode walker = head;
        ListNode runner = head;
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;

            if (walker == runner) {
                return true;
            }
        }
        return false;
    }

    /**
     * LeetCode 142
     * 环形链表升级版
     * */
    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode walker = head;
        ListNode runner = head;

        ListNode meetNode = null;
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;

            if (walker == runner) {
                meetNode = walker;
                break;
            }
        }
        if (meetNode == null) {
            return null;
        }

        walker = head;
        while (walker != meetNode) {
            walker = walker.next;
            meetNode = meetNode.next;
        }
        return walker;
    }


    /**
     * LeetCode 148
     * 链表中常规但需要掌握的题
     * */
    public static ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        ListNode walker = head;
        ListNode runner = head;
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
        }

        ListNode l1 = head;
        ListNode l2 = walker.next;
        walker.next = null;

        ListNode t1 = sortList(l1);
        ListNode t2 = sortList(l2);
        return mergeTwoLists(t1, t2);
    }


    /**
     * LeetCode 160
     * 比较好理解的相交节点题
     * */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = 0;
        int len2 = 0;
        ListNode l1 = headA;
        ListNode l2 = headB;
        while (l1 != null) {
            l1 = l1.next;
            len1++;
        }
        while (l2 != null) {
            l2 = l2.next;
            len2++;
        }

        if (len1 < len2) {
            return getIntersectionNode(headB, headA);
        }

        int temp = len1 - len2;
        l1 = headA;
        l2 = headB;
        while (temp > 0) {
            l1 = l1.next;
            temp--;
        }
        while (l1 != null && l2 != null && l1 != l2) {
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1;
    }


    /**
     * LeetCode 206
     * 链表基础操作题
     * */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = new ListNode(0);
        while (head != null) {
            ListNode next = head.next;

            head.next = pre.next;
            pre.next = head;

            head = next;
        }
        return pre.next;
    }
}

