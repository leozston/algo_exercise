package offer;

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
}
