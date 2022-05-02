package hot;

import java.util.*;

/**
 * @description:
 * @author: longlonglv
 * @date: 2022/4/30
 */
public class HotUtils {
    public static void printArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void printLinkedNode(ListNode l1) {
        while (l1 != null) {
            System.out.println(l1.val);
            l1 = l1.next;
        }
    }

    public static void printArrayList(List<List<Integer>> lists) {
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }
    }

    public static void printListString(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void printListInteger(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void printArrayListString(List<List<String>> lists) {
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }
    }

    public static void printTwoDimensionArray(int[][] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                System.out.println(nums[i][j]);
            }
        }
    }


    /**
     * LeetCode 105
     * 基础题，构造树结构
     * */
    public static TreeNode constructByPreInorder(int[] preorder, int[] inorder) {
        TreeNode node = constructByPreInorderImpl(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        return node;
    }

    public static TreeNode constructByPreInorderImpl(int[] preOrder, int low1, int high1, int[] inOrder, int low2, int high2) {
        if (low1 > high1 || low2 > high2) {
            return null;
        }

        int cur = preOrder[low1];

        int tempIndex = low2;
        while (tempIndex <= high2 && cur != inOrder[tempIndex]) {
            tempIndex++;
        }
        TreeNode node = new TreeNode(cur);

        node.left = constructByPreInorderImpl(preOrder, low1 + 1, tempIndex - low2 + low1, inOrder, low2, tempIndex - 1);
        node.right = constructByPreInorderImpl(preOrder, tempIndex - low2 + low1 + 1, high1, inOrder, tempIndex + 1, high2);
        return node;
    }

    public static void preorderTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        preorderTraverse(node.left);
        preorderTraverse(node.right);
    }
}
