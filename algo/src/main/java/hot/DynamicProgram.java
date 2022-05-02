package hot;

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

}
