package hot100;

import java.util.*;

import static hot100.HotUtils.*;

/**
 * @description: 回溯法
 * @author: longlonglv
 * @date: 2022/4/29
 */
public class BackTrack {
    public static int findTargetSumWays = 0;
    public static void main(String[] args) {
        System.out.println("hello world");

//        test17
//        String s = "23";
//        List<String> result = letterCombinations(s);

//        test22
//        List<String> result = generateParenthesis(3);
//        printListString(result);

//        test39
//        int[] nums = {2,3,6,7};
//        List<List<Integer>> result = combinationSum(nums, 7);
//        printArrayList(result);

//        test 46
//        int[] nums = {1,2,3};
//        List<List<Integer>> result = permute(nums);
//        printArrayList(result);

//        test 78
//        int[] nums = {1,2,3};
//        List<List<Integer>> result = subsets(nums);
//        printArrayList(result);

//        test 79
//        char[][] nums = {{'A','B','C','E'},{'S','F','C','S'}, {'A','D','E','E'}};
//        boolean r = exist(nums, "ABCCED");
//        System.out.println(r);

//        test 200
//        char[][] nums = {{'1','1','1','1','0'},
//                {'1','1','0','1','0'},
//                {'1','1','0','0','0'},
//                {'0','0','0','0','0'}};
//        int r = numIslands(nums);
//        System.out.println(r);

//        test 494
//        int[] nums = {1,1,1,1,1};
//        int r = findTargetSumWays(nums, 3);
//        System.out.println(r);

//        test 301
        String s = "(a)())()";
        List<String> r = removeInvalidParentheses(s);
        printListString(r);
    }

    /**
     * LeetCode17
     * 常规的回溯法
     * */
    public static List<String> letterCombinations(String digits) {
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        List<String> result = new ArrayList<>();
        if (digits.equals("")) {
            return result;
        }
        letterCombinationsImpl(digits, 0, result, "", phoneMap);
        printListString(result);
        return result;
    }
    public static void letterCombinationsImpl(String digits, int index, List<String> result, String cur, Map<Character, String> map) {
        if (index == digits.length()) {
            result.add(cur);
            return;
        }

        Character c = digits.charAt(index);
        for (int i = 0; i < map.get(c).length(); i++) {
            letterCombinationsImpl(digits, index+1, result, cur + map.get(c).charAt(i), map);
        }
    }

    /**
     * LeetCode 22
     * 比较常见的回溯法提醒
     * */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesis(n, 0, 0, result, "");
        return result;
    }

    public static void generateParenthesis(int n, int left, int right, List<String> result, String cur) {
        if (left == n && right == n) {
            result.add(cur);
            return;
        }
        if (left > n || right > n) {
            return;
        }
        if (left < right) {
            return;
        }

        generateParenthesis(n, left + 1, right, result, cur + "(");
        generateParenthesis(n, left, right + 1, result, cur + ")");
    }

    /**
     * LeetCode 39
     * 常见的回溯法
     * */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        combinationSumImpl(candidates, 0, result, cur, target);
        return result;
    }

    public static void combinationSumImpl(int[] candidates, int index, List<List<Integer>> result, List<Integer> cur, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(cur));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            cur.add(candidates[i]);
            combinationSumImpl(candidates, i, result, cur, target - candidates[i]);
            cur.remove(cur.size() - 1);
        }
    }

    /**
     * LeetCode 46
     * 很基础的回溯问题
     * */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        permuteImpl(nums, 0, used, result, cur);
        return result;
    }

    public static void permuteImpl(int[] nums, int index, boolean[] used, List<List<Integer>> result, List<Integer> cur) {
        if (index == nums.length) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                cur.add(nums[i]);
                permuteImpl(nums, index + 1, used, result, cur);
                cur.remove(cur.size() - 1);
                used[i] = false;
            }
        }

    }

    /**
     * LeetCode 78
     * 回溯法基础题
     * */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        subsetsImpl(nums, 0, result, cur);
        return result;
    }

    public static void subsetsImpl(int[] nums, int index, List<List<Integer>> result, List<Integer> cur) {
        if (index == nums.length) {
            result.add(new ArrayList<>(cur));
            return;
        }
        subsetsImpl(nums, index+1, result, cur);
        cur.add(nums[index]);
        subsetsImpl(nums, index + 1, result, cur);
        cur.remove(cur.size() - 1);
    }



    /**
     * LeetCode 79
     * 比较常见的回溯法，需要掌握
     * */
    public static boolean exist(char[][] board, String word) {
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j]) {
                    used[i][j] = true;
                    boolean r = existImpl(board, used, 1, i, j, word);
                    used[i][j] = false;
                    if (r) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean existImpl(char[][] board, boolean[][] used, int index, int row, int column, String word) {
        if (index == word.length()) {
            return true;
        }
        // up
        boolean up = false;
        if (row > 0 && !used[row - 1][column] && board[row - 1][column] == word.charAt(index)) {
            used[row - 1][column] = true;
            up = existImpl(board, used, index + 1, row - 1, column, word);
            used[row - 1][column] = false;
        }
        // down
        boolean down = false;
        if (row < board.length - 1 && !used[row + 1][column] && board[row + 1][column] == word.charAt(index)) {
            used[row + 1][column] = true;
            down = existImpl(board, used, index + 1, row + 1, column, word);
            used[row + 1][column] = false;
        }
        // left
        boolean left = false;
        if (column > 0 && !used[row][column - 1] && board[row][column - 1] == word.charAt(index)) {
            used[row][column - 1] = true;
            left = existImpl(board, used, index + 1, row, column - 1, word);
            used[row][column - 1] = false;
        }
        boolean right = false;
        if (column < board[0].length - 1 && !used[row][column + 1] && board[row][column + 1] == word.charAt(index)) {
            used[row][column + 1] = true;
            right = existImpl(board, used, index + 1, row, column + 1, word);
            used[row][column + 1] = false;
        }
        return up || down || left || right;
    }


    /**
     * LeetCode 200
     * 比较经典的具有代表性的一道回溯法的题目
     * */
    public static int numIslands(char[][] grid) {
        int numIslandsVar = 0;
        boolean[][] used = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!used[i][j] && grid[i][j] == '1') {
                    used[i][j] = true;
                    numIslandsImpl(grid, used, i, j);
                    numIslandsVar++;
                }
            }
        }
        return numIslandsVar;
    }
    public static void numIslandsImpl(char[][] grid, boolean[][] used, int i, int j) {
        if (i > 0 && !used[i-1][j] && grid[i-1][j] == '1') {
            used[i-1][j] = true;
            numIslandsImpl(grid, used, i - 1, j);
        }
        if (i < grid.length - 1 && !used[i + 1][j] && grid[i+1][j] == '1') {
            used[i+1][j] = true;
            numIslandsImpl(grid, used, i+1, j);
        }
        if (j > 0 && !used[i][j-1] && grid[i][j-1] == '1') {
            used[i][j-1] = true;
            numIslandsImpl(grid, used, i, j-1);
        }
        if (j < grid[0].length - 1 && !used[i][j+1] && grid[i][j+1] == '1') {
            used[i][j+1] = true;
            numIslandsImpl(grid, used, i, j + 1);
        }
    }


    /**
     * LeetCode 494
     * 回溯法
     * */
    public static int findTargetSumWays(int[] nums, int target) {
        findTargetSumWays = 0;
        findTargetSumWaysImpl(nums, target, 0);
        return findTargetSumWays;
    }
    public static void findTargetSumWaysImpl(int[] nums, int target, int index) {
        if (index == nums.length) {
            if (target == 0) {
                findTargetSumWays++;
            }
            return;
        }
        findTargetSumWaysImpl(nums, target + (-nums[index]), index+1);
        findTargetSumWaysImpl(nums, target + nums[index], index+1);
    }

    /**
     * LeetCode 301
     * 这是一道稍难一点的回溯法，LeetCode中属于hard
     * */
    public static List<String> removeInvalidParentheses(String s) {
//        首先计算出几个要删掉几个'('与几个')'，判断字符串中有几对括号要保留
        int len = 0;
        int delLen1 = 0;
        int delLen2 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                len++;
            } else if (s.charAt(i) == ')'){
                if (len > 0) {
                    len--;
                } else {
                    delLen2++;
                }
            }
        }
        delLen1 = len;

        List<String> result = new ArrayList<>();
        removeInvalidParenthesesImpl(s, 0, 0, 0, delLen1, delLen2, 0, 0, result, "");
        return result;
    }

    public static void removeInvalidParenthesesImpl(String s, int index, int hasLen1, int hasLen2, int targeLen1, int targetLen2, int len1, int len2, List<String> result, String cur) {
        /**
         * hasLen1:当前字符串中已经有的'('、hasLen2:当前字符串中已经有的')'
         * targeLen1:需要删除的'('，取值不变、targeLen2:需要删除的')'，取值不变
         * len1:当前字符串中已经删除的'(、len2:当前字符串中已经删除的')'
         * */
        if (len1 == targeLen1 && len2 == targetLen2 && index == s.length()) {
//          这里不是很完美，ugly
            if (!result.contains(cur)) {
                result.add(cur);
            }
            return;
        }
        if (index >= s.length()) {
            return;
        }
        if (len1 > targeLen1 || len2 > targetLen2) {
            return;
        }
        if (hasLen1 < hasLen2) {
            return;
        }
        if (s.charAt(index) == '(') {
            removeInvalidParenthesesImpl(s, index + 1, hasLen1 + 1, hasLen2, targeLen1, targetLen2, len1, len2, result, cur + "(");
            removeInvalidParenthesesImpl(s, index + 1, hasLen1, hasLen2, targeLen1, targetLen2, len1 + 1, len2, result, cur);
        } else if (s.charAt(index) == ')') {
            removeInvalidParenthesesImpl(s, index + 1, hasLen1, hasLen2 + 1, targeLen1, targetLen2, len1, len2, result, cur + ")");
            removeInvalidParenthesesImpl(s, index + 1, hasLen1, hasLen2, targeLen1, targetLen2, len1, len2 + 1, result, cur);
        } else {
            removeInvalidParenthesesImpl(s, index + 1, hasLen1, hasLen2, targeLen1, targetLen2, len1, len2, result, cur + s.charAt(index));
        }
    }
}
