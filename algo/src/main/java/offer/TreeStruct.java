package offer;

import java.util.*;

import static offer.OfferUtils.*;

/**
 * @description:
 * @author: longlonglv
 * @date: 2022/5/22
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
    public static void main(String[] args) {
        int[] pre = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = constructByPreInorder(pre, inorder);

//        List<Integer> c = new ArrayList<>();
//        c.add(4);
//        c.add(6);
//        c.remove(c.size() - 1);
//        System.out.println(c);
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }
        if ((A == null && B != null)) {
            return false;
        }
        boolean cur = false;
        boolean sub = false;
        cur = isSubStructureImpl(A, B);
        if (cur) {
            return true;
        }
        sub = isSubStructure(A.left, B) || isSubStructure(A.right, B);
        return sub;
    }
    public boolean isSubStructureImpl(TreeNode A, TreeNode B) {
        /**
         * 注意B是A的子结构要如何表达
         * */
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }

        if (A.val != B.val) {
            return false;
        }

        return isSubStructureImpl(A.left, B.left) && isSubStructureImpl(A.right, B.right);
    }

    /**
     * offer 28
     * */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricImpl(root.left, root.right);
    }

    public boolean isSymmetricImpl(TreeNode node1, TreeNode node2) {
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
     * offer 27
     * */
    public TreeNode mirrorTree(TreeNode root) {
        return mirrorTreeImpl(root);
    }
    public TreeNode mirrorTreeImpl(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode n = new TreeNode(node.val);
        n.left = mirrorTreeImpl(node.right);
        n.right = mirrorTreeImpl(node.left);
        return n;
    }

    /**
     * offer 32-I
     * */
    public int[] levelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return new int[0];
        }
        queue.offer(root);
        int cur = 1;
        int next = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            list.add(temp.val);
            cur--;
            if (temp.left != null) {
                queue.offer(temp.left);
                next++;
            }
            if (temp.right != null) {
                queue.offer(temp.right);
                next++;
            }

            if (cur == 0) {
                cur = next;
                next = 0;
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * offer 32-II
     * */
    public List<List<Integer>> levelOrderV2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return list;
        }
        queue.offer(root);
        int cur = 1;
        int next = 0;

        List<Integer> curLevelList = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            curLevelList.add(temp.val);
            cur--;
            if (temp.left != null) {
                queue.offer(temp.left);
                next++;
            }
            if (temp.right != null) {
                queue.offer(temp.right);
                next++;
            }

            if (cur == 0) {
                cur = next;
                next = 0;
                list.add(new ArrayList<>(curLevelList));
                curLevelList = new ArrayList<>();
            }
        }

        return list;
    }

    /**
     * offer 32-III
     * */
    public List<List<Integer>> levelOrderV3(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return list;
        }
        queue.offer(root);
        int cur = 1;
        int next = 0;

        boolean leftToRight = true;

        LinkedList<Integer> curLevel = new LinkedList<>();
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (leftToRight) {
                curLevel.offer(temp.val);
            } else {
                curLevel.offerFirst(temp.val);
            }
            cur--;
            if (temp.left != null) {
                queue.offer(temp.left);
                next++;
            }
            if (temp.right != null) {
                queue.offer(temp.right);
                next++;
            }

            if (cur == 0) {
                cur = next;
                next = 0;
                list.add(new ArrayList<>(curLevel));
                curLevel = new LinkedList<>();
                leftToRight = !leftToRight;
            }
        }

        return list;
    }

    /**
     * offer 33
     * */
    public boolean verifyPostorder(int[] postorder) {
        boolean result = verifyPostorderImpl(postorder, 0, postorder.length - 1);
        return result;
    }
    public boolean verifyPostorderImpl(int[] postorder, int low, int high) {
        if (low >= high) {
            return true;
        }

        int index = high;
        while (index >= 0 && postorder[index] >= postorder[high]) {
            index--;
        }
        int tempLow = low;
        while (low <= index && postorder[low] < postorder[high]) {
            low++;
        }
        if (low < index) {
            return false;
        }
        return verifyPostorderImpl(postorder, tempLow, index) && verifyPostorderImpl(postorder, index + 1, high -1 );
    }

    /**
     * offer 34
     * */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        pathSumImp(root, result, cur, target);
        return result;
    }

    public void pathSumImp(TreeNode node, List<List<Integer>> result, List<Integer> cur, int target) {
        if (node == null) {
            return;
        }
        cur.add(node.val);
        target = target - node.val;
        if (node.left == null && node.right == null && target == 0) {
            result.add(new ArrayList<>(cur));
//            注意：这里一定不要有return，否则，没有执行删除当前节点path的操作
//            return;
        }
        if (node.left != null) {
            pathSumImp(node.left, result, cur, target);
        }
        if (node.right != null) {
            pathSumImp(node.right, result, cur, target);
        }
        target = target + node.val;
        cur.remove(cur.size() - 1);
    }

    /**
     * offer 54
     * 注意：这里是第k大，不是第k小
     * */
    public static int kthLargestVar = 0;
    public static TreeNode kthLargestResult = null;
    public int kthLargest(TreeNode root, int k) {
        kthLargestVar = k;
        kthLargestResult = null;
        kthLargestImpl(root);
        return kthLargestResult.val;
    }
    public void kthLargestImpl(TreeNode node) {
        if (node == null) {
            return;
        }

        kthLargestImpl(node.right);
        kthLargestVar--;
        if (kthLargestVar == 0) {
            kthLargestResult = node;
            return;
        }
        kthLargestImpl(node.left);
    }

    /**
     * offer 55-I
     * */
    public int maxDepth(TreeNode root) {
        int r = maxDepthImpl(root);
        return r;
    }
    public int maxDepthImpl(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = maxDepthImpl(node.left);
        int right = maxDepthImpl(node.right);
        return Math.max(left, right) + 1;
    }

    /**
     * offer 55-II
     * */
    public boolean isBalanced(TreeNode root) {
        return isBalancedImpl(root) >= 0;
    }
    public int isBalancedImpl(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = isBalancedImpl(node.left);
        int right = isBalancedImpl(node.right);
        if (left < 0 || right < 0) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }


    /**
     * 二叉搜索树
     * */
    public boolean isBalancedV1(TreeNode root) {
        return isBalancedImplV1(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public boolean isBalancedImplV1(TreeNode node, int low, int high) {
        if (node == null) {
            return true;
        }
        if (node.val < low || node.val > high) {
            return false;
        }
        return isBalancedImplV1(node.left, low, node.val) && isBalancedImplV1(node.right, node.val, high);
    }

    /**
     * offer 68-I
     * */
    public static TreeNode lowestCommonAncestorVar = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestorVar = null;
        lowestCommonAncestorImpl(root, p.val, q.val);
        return lowestCommonAncestorVar;
    }
    public void lowestCommonAncestorImpl(TreeNode node, int v1, int v2) {
        if (node == null) {
            return;
        }
        if ((node.val >= v1 && node.val <= v2) || (node.val <= v1 && node.val >= v2)) {
            lowestCommonAncestorVar = node;
            return;
        }
        if (lowestCommonAncestorVar == null) {
            lowestCommonAncestorImpl(node.left, v1, v2);
        }
        if (lowestCommonAncestorVar == null) {
            lowestCommonAncestorImpl(node.right, v1, v2);
        }
    }


    /**
     * offer 68-II
     * */
    public static TreeNode lowestCommonAncestorV2Var = null;
    public TreeNode lowestCommonAncestorV2(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestorV2Var = null;
        lowestCommonAncestorV2Impl(root, p, q);
        return lowestCommonAncestorV2Var;
    }

    public boolean lowestCommonAncestorV2Impl(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return false;
        }
        boolean left = lowestCommonAncestorV2Impl(node.left, p, q);
        boolean right = lowestCommonAncestorV2Impl(node.right, p, q);

        if ((left && right) || ((left || right) && (node.val == p.val || node.val == q.val))) {
            lowestCommonAncestorV2Var = node;
        }

        return (left || right || (node.val == p.val || node.val == q.val));
    }
}
