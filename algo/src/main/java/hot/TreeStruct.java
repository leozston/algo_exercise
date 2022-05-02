package hot;

import java.util.*;

import static hot.HotUtils.*;

/**
 * @description: 树结构
 * @author: longlonglv
 * @date: 2022/4/29
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class TreeStruct {
    public static int maxPathSumVar = Integer.MIN_VALUE;

    public static void main(String[] args) {
        System.out.println("hello world");
        int[] pre = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

//      test 105
        TreeNode root = constructByPreInorder(pre, inorder);
//        preorderTraverse(root);

//      test 94
//        List<Integer> result = new ArrayList<>();
//        result = inorderTraversal(root);
//        printListInteger(result);

//        test 96
//        int r = numTrees(4);
//        System.out.println(r);

//        test 101
//        boolean r = isSymmetric(root);
//        System.out.println(r);

//        test 102
//        List<List<Integer>> result = levelOrder(root);
//        printArrayList(result);

//        test 124

        maxPathSum(root);
        System.out.println(maxPathSumVar);
    }

    /**
     * LeetCode 94
     * 基础题
     * */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversalImpl(root, result);
        return result;
    }
    public static void inorderTraversalImpl(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorderTraversalImpl(node.left, result);
        result.add(node.val);
        inorderTraversalImpl(node.right, result);
    }

    /**
     * LeetCode 96
     * 这是一个动态规划的问题，统计搜索二叉树的数量
     * */
    public static int numTrees(int n) {
        if (n == 1 || n == 2) {
            return n;
        }

        int[] nums= new int[n + 1];
        nums[0] = 1;
        nums[1] = 1;
        nums[2] = 2;

        for (int i = 3; i <= n; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += nums[j] * nums[i - j - 1];
            }
            nums[i] = sum;
        }
        return nums[n];
    }

    /**
     * LeetCode 98
     * */
    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBSTImpl(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean isValidBSTImpl(TreeNode node, Long min, Long max) {
        if (node == null) {
            return true;
        }
//      根绝LeetCode测试样例，节点值相等的时候，返回false
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return isValidBSTImpl(node.left, min, (long)node.val) && isValidBSTImpl(node.right, (long)node.val, max);
    }

    /**
     * LeetCode 101
     * */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricImpl(root.left, root.right);
    }
    public static boolean isSymmetricImpl(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        }
        return isSymmetricImpl(node1.left, node2.right) && isSymmetricImpl(node1.right, node2.left);
    }


    /**
     * LeetCode 102
     * 这是必须要掌握的层次遍历题，而且要能很快地写出，可能会有一些变种题，如一层正向，下一层反向遍历等
     * */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return result;
        }

        queue.offer(root);
        int cur = 1;
        int next = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curList.add(node.val);
            cur--;

            if (node.left != null) {
                queue.offer(node.left);
                next++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                next++;
            }

            if (cur == 0) {
                result.add(new ArrayList<>(curList));
                curList = new ArrayList<>();
                cur = next;
                next = 0;
            }
        }
        return result;
    }

    /**
     * LeetCode 104
     * easy题
     * */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * LeetCode 114
     * 技巧题，需要对遍历很深刻的理解
     * TODO
     * */
    public static void flatten(TreeNode root) {

    }


    /**
     * LeetCode 124
     * 这是一道稍微难一点的二叉树遍历的题目，在LeetCode中是hard题，但掌握方法后也不难
     * */
    public static int maxPathSum(TreeNode root) {
        maxPathSumVar = Integer.MIN_VALUE;
        maxPathSumImpl(root);
        return maxPathSumVar;
    }
    public static int maxPathSumImpl(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(maxPathSumImpl(node.left), 0);
        int right = Math.max(maxPathSumImpl(node.right), 0);

        maxPathSumVar = Math.max(maxPathSumVar, left + right + node.val);

        return Math.max(left, right) + node.val;
    }

}
