package hot100;

import java.util.*;

import static hot100.HotUtils.*;

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
    public static int diameterOfBinaryTreeVar = 0;
    public static TreeNode lowestCommonAncestorVar = null;
    public static TreeNode pre = null;

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
//        maxPathSum(root);
//        System.out.println(maxPathSumVar);

//        test 543
//        int r = diameterOfBinaryTree(root);
//        System.out.println(r);

//        test 26
        TreeNode node = invertTree(root);
        preorderTraverse(node);
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
     * 需要对遍历很深刻的理解
     * */
    public static void flatten(TreeNode root) {
        pre = null;
        flattenImpl(root);
    }
    public static void flattenImpl(TreeNode node) {
        if (node == null) {
            return;
        }
        flattenImpl(node.right);
        flattenImpl(node.left);
        node.right = pre;
        node.left = null;
        pre = node;
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


    /**
     * LeetCode 543
     * 很经典的一道二叉树的题，和LeetCode 124 可以进行比较，很相似
     * */
    public static int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreeVar = 0;
        diameterOfBinaryTreeImpl(root);
//        注意，这里是路径长度，并不是节点长度，所以要减1
        return diameterOfBinaryTreeVar - 1;
    }
    public static int diameterOfBinaryTreeImpl(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = diameterOfBinaryTreeImpl(root.left);
        int right = diameterOfBinaryTreeImpl(root.right);

        diameterOfBinaryTreeVar = Math.max(diameterOfBinaryTreeVar, left + right + 1);
        return Math.max(left, right) + 1;
    }


    /**
     * LeetCode 226
     * 二叉树的基本操作
     * */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        invertTreeImpl(root);
        return root;
    }
    public static void invertTreeImpl(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        invertTreeImpl(root.left);
        invertTreeImpl(root.right);
    }


    /**
     * LeetCode 236
     * 这道题总体还是有一定的难度的，需要很清楚的理解遍历过程
     * */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestorVar = null;
        lowestCommonAncestorHasNode(root, p, q);
        return lowestCommonAncestorVar;
    }

    public static boolean lowestCommonAncestorHasNode(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean left = lowestCommonAncestorHasNode(root.left, p, q);
        boolean right = lowestCommonAncestorHasNode(root.right, p, q);

        if ((left && right) || ((root.val == p.val || root.val == q.val) && (left || right))) {
            lowestCommonAncestorVar = root;
        }
        return (left || right) || (root.val == p.val || root.val == q.val);
    }

    /**
     * LeetCode 617
     * 二叉树的遍历题
     * */
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        return mergeTreesImpl(root1, root2);
    }
    public static TreeNode mergeTreesImpl(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return null;
        }
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }

        TreeNode n = new TreeNode(node1.val + node2.val);
        n.left = mergeTreesImpl(node1.left, node2.left);
        n.right = mergeTreesImpl(node1.right, node2.right);
        return n;
    }
}
