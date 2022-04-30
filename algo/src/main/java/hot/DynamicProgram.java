package hot;

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
        String word1 = "horse";
        String word2 = "ros";
        int r = minDistance(word1, word2);
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
}
