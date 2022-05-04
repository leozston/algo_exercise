package hot100;

import javax.swing.*;
import java.util.*;

/**
 * @description: 动态规划
 * @author: longlonglv
 * @date: 2022/4/29
 */
public class DynamicProgram {
    public static void main(String[] args) {
        System.out.println("hello world");
//        test5
//        String s = "a";
//        String t = longestPalindrome(s);
//        System.out.println(t);

//        test53
//        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
//        int[] nums = {-2,1};
//        int r = maxSubArray(nums);
//        System.out.println(r);

//        test62
//        int r = uniquePaths(3, 7);
//        System.out.println(r);

//        test64
//        int[][] nums = {{1,3,1},{1,5,1},{4,2,1}};
//        int r = minPathSum(nums);
//        System.out.println(r);

//        test 70
//        int r = climbStairs(3);
//        System.out.println(r);

//        test 72
//        String word1 = "horse";
//        String word2 = "ros";
//        int r = minDistance(word1, word2);
//        System.out.println(r);

//        test 128
//        int[] nums = {100,4,200,1,3,2};
//        int r = longestConsecutive(nums);
//        System.out.println(r);

//        test 136
//        int[] nums = {4,1,2,1,2};
//        int r = singleNumber(nums);
//        System.out.println(r);

//        test 169
//        int[] nums = {2,2,1,1,1,2,2};
//        int r = majorityElement(nums);
//        System.out.println(r);

//        test 300
//        int[] nums = {10,9,2,5,3,7,101,18};
//        int r = lengthOfLIS(nums);
//        System.out.println(r);

//        test 139
//        String s = "leetcode";
//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("leet");
//        wordDict.add("code");
//        boolean r = wordBreak(s, wordDict);
//        System.out.println(r);

//        test 152
//        int[] nums = {2,3,-2,4};
//        int[] nums = {-4, -3, -2};
//        int r = maxProduct(nums);
//        System.out.println(r);

//        test 198
//        int[] nums = {1,2,3,1};
//        int r = rob(nums);
//        System.out.println(r);

//        test 221
//        char[][] nums = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
//        int r = maximalSquare(nums);
//        System.out.println(r);

//         test 322
//        int[] nums = {1,2,5};
//        int r = coinChange(nums, 11);
//        System.out.println(r);

//        test 279
//        int r = numSquares(12);
//        System.out.println(r);

//        test 416
//        int[] nums = {1,5,11,5};
//        boolean r = canPartition(nums);
//        System.out.println(r);

//        test 647
        String s = "aaa";
        int r = countSubstrings(s);
        System.out.println(r);
    }

    /**
     * leetcode5
     * 动态规划，递推关系：nums[i][j] = (s.charAt(i) == s.charAt(j) && nums[i+1][j-1]) || (j - i <= 2 && s.charAt(i) == s.charAt(j))
     * */
    public static String longestPalindrome(String s) {
        boolean[][] nums = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            nums[i][i] = true;
        }

        int maxLen = -1;
        int startIndex = 0;
        int endIndex = 0;
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                nums[i][j] = (s.charAt(i) == s.charAt(j) && nums[i+1][j-1]) || (j - i <= 2 && s.charAt(i) == s.charAt(j));
                if (nums[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    startIndex = i;
                    endIndex = j;
                }
            }
        }
        return s.substring(startIndex, endIndex + 1);
    }

    /**
     * LeetCode10
     * 动态规划，这题的递推关系稍显复杂一点
     * TODO
     * */
    public static boolean isMatch(String s, String p) {
        return true;
    }


    /**
     * LeetCode 53
     * 比较典型的DP题目
     * */
    public static int maxSubArray(int[] nums) {
        int global = nums[0];
        int local = nums[0];
        for (int i = 1; i < nums.length; i++) {
            global = Math.max(global, Math.max(local + nums[i], nums[i]));
            local = Math.max(local + nums[i], nums[i]);
        }
        return global;
    }

    /**
     * LeetCode 62
     * 很简单的DP
     * */
    public static int uniquePaths(int m, int n) {
        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            nums[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            nums[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                nums[i][j] = nums[i-1][j] + nums[i][j-1];
            }
        }
        return nums[m-1][n-1];
    }

    /**
     * LeetCode 64
     * 与LeetCode 62比较类似，比较基础
     * */
    public static int minPathSum(int[][] grid) {
        int[][] nums = new int[grid.length][grid[0].length];
        nums[0][0] = grid[0][0];
        for (int i = 1; i < grid[0].length; i++) {
            nums[0][i] = nums[0][i - 1] + grid[0][i];
        }
        for (int j = 1; j < grid.length; j++) {
            nums[j][0] = nums[j - 1][0] + grid[j][0];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                nums[i][j] = Math.min(nums[i-1][j], nums[i][j-1]) + grid[i][j];
            }
        }
        return nums[grid.length - 1][grid[0].length - 1];
    }


    /**
     * LeetCode 70
     * 很简单的DP
     * */
    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int i = 1;
        int j = 2;
        int result = 0;
        for (int k = 3; k <= n; k++) {
            result = i + j;
            i = j;
            j = result;
        }
        return result;
    }


    /**
     * LeetCode 72
     * 中等难度的DP，主要是找到递推关系:
     * 相等时：nums[i][j] = nums[i-1][j-1];
     * 不相等时：nums[i][j] = Math.min(Math.min(nums[i-1][j], nums[i][j-1]), nums[i-1][j-1])+ 1;
     * */
    public static int minDistance(String word1, String word2) {
        int[][] nums = new int[word1.length() + 1][word2.length() + 1];
        nums[0][0] = 0;
        for (int i = 1; i <= word1.length(); i++) {
            nums[i][0] = i;
        }
        for (int j = 1; j <= word2.length(); j++) {
            nums[0][j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    nums[i][j] = nums[i-1][j-1];
                } else {
                    nums[i][j] = Math.min(Math.min(nums[i-1][j], nums[i][j-1]), nums[i-1][j-1])+ 1;
                }
            }
        }
        return nums[word1.length()][word2.length()];
    }

    /**
     * LeetCode 300
     * 很常见的一个动态规划问题
     * */
    public static int lengthOfLIS(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        int maxLIS = 1;
        for (int i = 1; i < nums.length; i++) {
            int temp = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    temp = Math.max(result[j] + 1, temp);
                }
            }
            result[i] = temp;
            maxLIS = Math.max(temp, maxLIS);
        }

        return maxLIS;
    }


    /**
     * LeetCode 128
     * 一个技巧题
     * */
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                int curMaxLen = 0;
                curMaxLen++;
                int temp = nums[i];
                set.remove(nums[i]);

                int tempLeft = temp - 1;
                int tempRight = temp + 1;
                while (set.contains(tempLeft)) {
                    curMaxLen++;
                    set.remove(tempLeft);
                    tempLeft--;
                }
                while (set.contains(tempRight)) {
                    curMaxLen++;
                    set.remove(tempRight);
                    tempRight++;
                }
                maxLen = Math.max(maxLen, curMaxLen);
            }
        }
        return maxLen;
    }

    /**
     * LeetCode 169
     * 过半数的常见做法
     * */
    public static int majorityElement(int[] nums) {
        if (nums.length < 2) {
            return nums[0];
        }
        int result = nums[0];
        int times = 1;
        for (int i = 1; i < nums.length; i++) {
            if (result == nums[i]) {
                times++;
            } else {
                if (times == 0) {
                    result = nums[i];
                    times = 1;
                } else {
                    times--;
                }
            }
        }
        return result;
    }

    /**
     * LeetCode 136
     * 判断单个数常见做法，进行亦或
     * */
    public static int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    /**
     * LeetCode 139
     * 这是一个判断是否的问题，所以归纳为DP，如果题目若是输出所有的满足条件的切分，则为回溯问题
     * */
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] nums = new boolean[s.length() + 1];
        nums[0] = true;
        Set<String> wordDictSet = new HashSet<>(wordDict);
        for (int i = 1; i <= s.length(); i++) {
            boolean temp = false;
            for (int j = 0; j < i; j++) {
                temp = nums[j] && wordDictSet.contains(s.substring(j, i));
                if (temp) {
                    break;
                }
            }
            nums[i] = temp;
        }
        return nums[s.length()];
    }


    /**
     * LeetCode 152
     * 典型的动态规划法，和最大连续子数组和一块思考
     * */
    public static int maxProduct(int[] nums) {
        if (nums.length < 2) {
            return nums[0];
        }
        int globalMax = nums[0];
        int localMax = nums[0];
        int localMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            globalMax = Math.max(Math.max(Math.max(nums[i], localMax * nums[i]), localMin * nums[i]), globalMax);
            int tempLocalMax = Math.max(Math.max(nums[i], localMax * nums[i]), localMin * nums[i]);
            localMin = Math.min(Math.min(nums[i], localMax * nums[i]), localMin * nums[i]);
            localMax = tempLocalMax;
        }
        return globalMax;
    }

    /**
     * LeetCode 198
     * LeetCode中至少有三种类型的打家劫舍，都要掌握，这是最简单的一种
     * */
    public static int rob(int[] nums) {
        if (nums.length < 2) {
            return nums[0];
        }
        int[] result = new int[nums.length];
        result[0] = nums[0];
        result[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            result[i] = Math.max(result[i-2] + nums[i], result[i-1]);
        }
        return result[nums.length - 1];
    }


    /**
     * LeetCode 337
     * 打家劫舍题型III，还是树的遍历，只不过遍历的时候，返回的值比一般的树的遍历稍显复杂，这里是一个数组
     * */
    public static int robV3(TreeNode root) {
        int[] result = robV3Impl(root);
        return Math.max(result[0], result[1]);
    }

    public static int[] robV3Impl(TreeNode node) {
        if (node == null) {
            int[] t = new int[2];
            return t;
        }
        int[] left = robV3Impl(node.left);
        int[] right = robV3Impl(node.right);
        int selected = node.val + left[1] + right[1];
        int notSelected = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{selected, notSelected};
    }


    /**
     * LeetCode 221
     * 几何中的DP，主要是找递推关系:
     * Math.min(Math.min(nums[i - 1][j - 1], nums[i - 1][j]), Math.min(nums[i - 1][j - 1], nums[i][j - 1])) + 1
     * */
    public static int maximalSquare(char[][] matrix) {
        int maxValue = 0;
        int[][] nums = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    nums[i][j] = 1;
                    maxValue = 1;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    nums[i][j] = Math.min(Math.min(nums[i - 1][j - 1], nums[i - 1][j]), Math.min(nums[i - 1][j - 1], nums[i][j - 1])) + 1;
                    maxValue = Math.max(maxValue, nums[i][j]);
                }
            }
        }
        return maxValue * maxValue;
    }


    /**
     * LeetCode 279
     * */
    public static int numSquares(int n) {
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = i;
        }
        for (int i = 1; i <= n; i++) {
            int temp = i;
            for (int j = 0; j * j <= i; j++) {
                temp = Math.min(nums[i - j * j] + 1, temp);
            }
            nums[i] = temp;
        }
        return nums[n];
    }

    /**
     * LeetCode 322
     * 比较常见的DP
     * */
    public static int coinChange(int[] coins, int amount) {
        int[] result = new int[amount + 1];
        for (int i = 1; i < amount + 1; i++) {
            result[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= amount; i++) {
            int curMin = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0 && result[i - coins[j]] != Integer.MAX_VALUE) {
                    curMin = Math.min(curMin, result[i-coins[j]] + 1);
                }
            }
            result[i] = curMin;
        }
        return result[amount] == Integer.MAX_VALUE ? -1 : result[amount];
    }


    /**
     * LeetCode 416
     * 和0/1背包问题类似
     * */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }

        boolean[][] result = new boolean[sum/2 + 1][nums.length + 1];
        result[0][0] = true;
        for (int i = 1; i < sum / 2 + 1; i++) {
            result[i][0] = false;
        }
        for (int i = 1; i < nums.length + 1; i++) {
            result[0][i] = true;
        }

        for (int i = 1; i < sum / 2 + 1; i++) {
            for (int j = 1; j < nums.length + 1; j++) {
                if (nums[j - 1] > i) {
                    result[i][j] = result[i][j-1];
                } else {
                    result[i][j] = result[i-nums[j - 1]][j-1] || result[i][j-1];
                }
            }
        }
        return result[sum/2][nums.length];
    }

    /**
     * LeetCode 647
     * LeetCode中关于回文串的题目很多，这是其中一道
     * */
    public static int countSubstrings(String s) {
        boolean[][] result = new boolean[s.length() + 1][s.length() + 1];
        for (int i = 0; i < s.length() + 1; i++) {
            result[i][i] = true;
        }
        int num = 0;
        for (int i = s.length() - 1; i > 0; i--) {
            for (int j = i + 1; j < s.length() + 1; j++) {
                result[i][j] = s.charAt(i-1) == s.charAt(j-1) && (j-i <2 || result[i+1][j-1]);
                if (result[i][j]) {
                    num++;
                }
            }
        }
        return num + s.length();
    }
}
