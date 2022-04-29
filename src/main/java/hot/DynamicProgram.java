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

}
