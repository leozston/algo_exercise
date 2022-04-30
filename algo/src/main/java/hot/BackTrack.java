package hot;

import java.util.*;

import static hot.HotUtils.*;

/**
 * @description: 回溯法
 * @author: longlonglv
 * @date: 2022/4/29
 */
public class BackTrack {
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
        char[][] nums = {{'A','B','C','E'},{'S','F','C','S'}, {'A','D','E','E'}};
        boolean r = exist(nums, "ABCCED");
        System.out.println(r);
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
}
