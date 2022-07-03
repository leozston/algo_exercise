package codetop;

import java.util.*;

/**
 * @description:
 * @author: longlonglv
 * @date: 2022/7/1
 */

public class AlgorithmExercise {
    public static void main(String[] args) {
        System.out.println("hello world");
        int r = mySqrt(2147395599);
        System.out.println(r);
    }

    /**
     * leetcode 1
     * */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]) && i != map.get(target - nums[i])) {
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                return result;
            }
        }
        return result;
    }

    public int[] twoSumV2(int[] nums, int target) {
        int[] result = new int[2];
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                result[0] = left;
                result[1] = right;
                return result;
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }

    /**
     * leetcode 2
     * */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode helper = pre;

        int carry = 0;
        int num = 0;
        while (l1 != null && l2 != null) {
            num = l1.val + l2.val + carry;
            carry = num / 10;
            ListNode cur = new ListNode(num % 10);
            helper.next = cur;
            helper = cur;

            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            num = l1.val + carry;
            carry = num / 10;
            ListNode cur = new ListNode(num % 10);
            helper.next = cur;
            helper = cur;

            l1 = l1.next;
        }

        while (l2 != null) {
            num = l2.val + carry;
            carry = num / 10;
            ListNode cur = new ListNode(num % 10);
            helper.next = cur;
            helper = cur;

            l2 = l2.next;
        }
        if (carry != 0) {
            ListNode cur = new ListNode(carry);
            helper.next = cur;
        }

        return pre.next;
    }

    /**
     * leetcode 3
     * */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        if (s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        set.add(s.charAt(0));
        int low = 0;
        int high = 1;
        int maxLen = 1;
        while (high < s.length()) {
            if (!set.contains(s.charAt(high))) {
                set.add(s.charAt(high));
                maxLen = Math.max(maxLen, high - low + 1);
                high++;
            } else {
                while (low < high) {
                    if (s.charAt(low) != s.charAt(high)) {
                        set.remove(s.charAt(low));
                        low++;
                    } else {
                        set.remove(s.charAt(low));
                        low++;
                        break;
                    }
                }
            }
        }
        return maxLen;
    }

    /**
     * leetcode 4
     * TODO
     * */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }

    /**
     * leetcode 5
     * */
    public String longestPalindrome(String s) {
        String maxS = "";
        if (s.length() == 0) {
            return maxS;
        }
        maxS = s.substring(0,1);
        int maxLen = 1;
        boolean[][] palindromeDict = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            palindromeDict[i][i] = true;
        }
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    palindromeDict[i][j] = (j-i <2) || palindromeDict[i + 1][j - 1];
                    if (palindromeDict[i][j]) {
                        if (j-i+1>maxLen) {
                            maxS = s.substring(i, j + 1);
                            maxLen = j-i+1;
                        }
                    }
                }
            }
        }
        return maxS;
    }


    /**
     * leetcode 7
     * */
    public int reverse(int x) {
        return 0;
    }


    /**
     * leetcode 8
     * TODO
     * */
    public int myAtoi(String s) {
        return 0;

    }


    /**
     * leetcode 11
     * */
    public int maxArea(int[] height) {
        int low = 0;
        int high = height.length - 1;
        int maxV = 0;
        while (low < high) {
            int minH = Math.min(height[low], height[high]);
            maxV = Math.max(maxV, (high - low) * minH);
            if (height[low] < height[high]) {
                low++;
            } else {
                high--;
            }
        }
        return maxV;
    }

    /**
     * leetcode 14
     * */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length < 2) {
            return strs[0];
        }
        int index = -1;
        while (index + 1 < strs[0].length()) {
            boolean sameFlag = true;
            Character c = strs[0].charAt(index + 1);

            for (int i = 1; i < strs.length; i++) {
                if ((index + 1 >= strs[i].length()) || strs[i].charAt(index + 1) != c) {
                    sameFlag = false;
                    break;
                }
            }
            if (!sameFlag) {
                break;
            }
            index++;
        }
        if (index == -1) {
            return "";
        }
        return strs[0].substring(0, index + 1);
    }



    /**
     * leetcode 15
     * */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        return result;
    }


    /**
     * leetcode 17
     * */
    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return new ArrayList<>();
        }
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
        String s = "";
        letterCombinationsImpl(digits, 0, phoneMap, result, s);
        return result;
    }

    public void letterCombinationsImpl(String digits, int index, Map<Character, String> phoneMap, List<String> result, String s) {
        if (index == digits.length()) {
            result.add(s);
            return;
        }
        String curDigitToStr = phoneMap.get(digits.charAt(index));
        for (int i = 0; i < curDigitToStr.length(); i++) {
            Character c = curDigitToStr.charAt(i);
            letterCombinationsImpl(digits, index + 1, phoneMap, result, s + c);
        }
    }



    /**
     * leetcode 19
     * */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode helper = pre;

        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }

        if (n > len) {
            return null;
        }
        head = pre.next;
        int moveNum = 0;
        while (moveNum < len - n) {
            head = head.next;
            helper = helper.next;
            moveNum++;
        }
        helper.next = helper.next.next;
        return pre.next;
    }


    /**
     * leetcode 20
     * */
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character peek = stack.pop();
                if ((c == ')' && peek == '(') || (c == ']' && peek == '[') || (c == '}' && peek == '{')) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }


    /**
     * leetcode 21
     * */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode pre = new ListNode(0);
        ListNode helper = pre;
        pre.next = list1;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                pre = pre.next;
                list1 = list1.next;
            } else {
                ListNode next = list2.next;
                list2.next = list1;
                pre.next = list2;
                pre = pre.next;

                list2 = next;
            }
        }

        if (list2 != null) {
            pre.next = list2;
        }
        return helper.next;
    }

    /**
     * leetcode 22
     * */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesisImpl(n, 0, 0, result, "");
        return result;
    }
    public void generateParenthesisImpl(int n, int left, int right, List<String> result, String s) {
        if (right > left) {
            return;
        }
        if (left > n || right > n) {
            return;
        }
        if (left == right && left == n) {
            result.add(s);
            return;
        }
        generateParenthesisImpl(n, left + 1, right, result, s + "(");
        generateParenthesisImpl(n, left, right + 1, result, s + ")");
    }



    /**
     * leetcode 23
     * */
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKListsImpl(lists, 0, lists.length - 1);
    }
    public ListNode mergeKListsImpl(ListNode[] lists, int low, int high) {
        if (low == high) {
            return lists[low];
        }
        if (low > high) {
            return null;
        }
        int mid = (low + high) / 2;
        ListNode l1 = mergeKListsImpl(lists, low, mid);
        ListNode l2 = mergeKListsImpl(lists, mid + 1, high);
        return mergeTwoLists(l1, l2);
    }



    /**
     * leetcode 42
     * */
    public int trap(int[] height) {
        int[] l2r = new int[height.length];
        int[] r2l = new int[height.length];
        if (height.length < 2) {
            return 0;
        }
        l2r[0] = 0;
        r2l[height.length - 1] = 0;

        int max1 = height[0];
        for (int i = 1; i < height.length; i++) {
            l2r[i] = max1;
            max1 = Math.max(max1, height[i]);
        }
        int max2 = height[height.length - 1];
        for (int i = height.length -2; i >= 0; i--) {
            r2l[i] = max2;
            max2 = Math.max(height[i], max2);
        }

        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            int min = Math.min(l2r[i], r2l[i]);
            sum += Math.max(0, min - height[i]);
        }
        return sum;
    }


    /**
     * leetcode 46
     * */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        permuteImpl(nums, used, result, cur);
        return result;
    }
    public void permuteImpl(int[] nums, boolean[] used, List<List<Integer>> result, List<Integer> cur) {
        if (cur.size() == nums.length) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                cur.add(nums[i]);
                permuteImpl(nums, used, result, cur);
                cur.remove(cur.size() - 1);
                used[i] = false;
            }
        }
    }


    /**
     * leetcode 48
     * */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int layer = n / 2;
        for (int i = 0; i < layer; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    /**
     * leetcode 49
     * */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            Arrays.sort(cs);
            String t = Arrays.toString(cs);
            if (map.containsKey(t)) {
                List<String> l = map.get(t);
                l.add(strs[i]);
                map.put(t, l);
            } else {
                List<String> l = new ArrayList<>();
                l.add(strs[i]);
                map.put(t, l);
            }
        }
        List<List<String>> lists = new ArrayList<>();
        for (Map.Entry<String, List<String>> e : map.entrySet()) {
            lists.add(e.getValue());
        }
        return lists;
    }


    /**
     * leetcode 50
     * */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1.0 / myPowImpl(x, -n);
        }
        return myPowImpl(x, n);
    }
    public double myPowImpl(double x, int n) {
        if (n == 0) {
            return 1;
        }
        int half = n / 2;
        double v = myPowImpl(x, half);
        if (n % 2 == 0) {
            return v * v;
        } else {
            return v * v * x;
        }
    }



    /**
     * leetcode 54
     * */
    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0;
        int right = matrix[0].length - 1;
        int up = 0;
        int down = matrix.length - 1;

        List<Integer> result = new ArrayList<>();
        int number = matrix.length * matrix[0].length;
        int index = 0;

        while (index < number) {
            for (int i = left; i <= right && index < number; i++) {
                result.add(matrix[up][i]);
                index++;
            }
            up++;

            for (int i = up; i <= down && index < number; i++) {
                result.add(matrix[i][right]);
                index++;
            }
            right--;

            for (int i = right; i >= left && index < number; i--) {
                result.add(matrix[down][i]);
                index++;
            }
            down--;

            for (int i = down; i >= up && index < number; i--) {
                result.add(matrix[i][left]);
                index++;
            }
            left++;
        }
        return result;
    }

    /**
     * leetcode 55
     * */
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxReach < i) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }



    /**
     * leetcode 56
     * */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0] || (o1[0] == o2[0] && o1[1] < o2[1])) {
                    return -1;
                } else if (o1[0] == o2[0] && o1[1] == o2[1]) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > list.get(list.size() - 1)[1]) {
                list.add(intervals[i]);
            } else {
                list.get(list.size() - 1)[1] = Math.max(intervals[i][1], list.get(list.size() - 1)[1]);
            }
        }
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }


    /**
     * leetcode 62
     * */
    public int uniquePaths(int m, int n) {
        int[][] nums = new int[m][n];
        for (int i = 0; i < n; i++) {
            nums[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            nums[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                nums[i][j] = nums[i-1][j] + nums[i][j-1];
            }
        }
        return nums[m-1][n-1];
    }


    /**
     * leetcode 66
     * */
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int num = digits[i] + carry;
            digits[i] = num % 10;
            carry = num / 10;
        }
        if (carry != 1) {
            return digits;
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }


    /**
     * leetcode 69
     * */
    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        int low = 0;
        int high = x/2;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if ((long)mid * mid == x) {
                return mid;
            } else if ((long)mid * mid < x) {
                if ((long)(mid + 1) * (mid + 1) > x) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }


    /**
     * leetcode 70
     * */
    public int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int a = 1;
        int b = 2;
        int c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * leetcode 73
     * */
    public void setZeroes(int[][] matrix) {
        boolean col0 = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                col0 = true;
                break;
            }
        }
        boolean row0 = false;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                row0 = true;
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (col0) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        if (row0) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
    }

    /**
     * leetcode 78
     * */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        subsetsImpl(nums, 0, result, cur);
        return result;
    }
    public void subsetsImpl(int[] nums, int index, List<List<Integer>> result, List<Integer> cur) {
        if (index == nums.length) {
            result.add(new ArrayList<>(cur));
            return;
        }
        subsetsImpl(nums, index+1, result, cur);
        cur.add(nums[index]);
        subsetsImpl(nums, index+1, result, cur);
        cur.remove(cur.size() - 1);
    }


    /**
     * leetcode 79
     * */
    public boolean exist(char[][] board, String word) {
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j]) {
                    used[i][j] = true;
                    boolean flag = existImpl(board, used, word, i, j, 0);
                    if (flag) {
                        return true;
                    }
                    used[i][j] = false;
                }
            }
        }
        return false;
    }
    public boolean existImpl(char[][] board, boolean[][] used, String word, int row, int column, int index) {
        if (index == word.length() - 1) {
            return true;
        }
        boolean down = false;
        if (row > 0 && board[row - 1][column] == word.charAt(index + 1) && !used[row - 1][column]) {
            used[row - 1][column] = true;
            down = existImpl(board, used, word, row - 1, column, index + 1);
            used[row - 1][column] = false;
        }

        boolean up = false;
        if (row < board.length - 1 && board[row + 1][column] == word.charAt(index + 1) && !used[row + 1][column]) {
            used[row + 1][column] = true;
            up = existImpl(board, used, word, row + 1, column, index + 1);
            used[row + 1][column] = false;
        }

        boolean left = false;
        if (column > 0 && board[row][column - 1] == word.charAt(index + 1) && !used[row][column - 1]) {
            used[row][column - 1] = true;
            left = existImpl(board, used, word, row, column - 1, index + 1);
            used[row][column - 1] = false;
        }

        boolean right = false;
        if (column < board[0].length - 1 && board[row][column + 1] == word.charAt(index + 1) && !used[row][column + 1]) {
            used[row][column + 1] = true;
            right = existImpl(board, used, word, row, column + 1, index + 1);
            used[row][column + 1] = false;
        }

        return up || down || left || right;
    }


    /**
     * leetcode 88
     * */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        m = m - 1;
        n = n - 1;
        while (m >= 0 && n >= 0) {
            if (nums1[m] < nums2[n]) {
                nums1[index] = nums2[n];
                n--;
                index--;
            } else {
                nums1[index] = nums1[m];
                m--;
                index--;
            }
        }
        while (n >= 0) {
            nums1[index] = nums2[n];
            n--;
            index--;
        }

    }




    /**
     * leetcode 91
     * */
    public int numDecodings(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        int[] num = new int[s.length() + 1];
        num[0] = 1;
        num[1] = 1;
        for (int i = 1; i < s.length(); i++) {
            int index = i + 1;
            if (s.charAt(i) >= '1' && s.charAt(i) <= '6') {
                if (s.charAt(i-1) == '1' || s.charAt(i-1) == '2') {
                    num[index] = num[index - 1] + num[index - 2];
                } else {
                    num[index] = num[index - 1];
                }
            } else if (s.charAt(i) == '0'){
                if (s.charAt(i - 1) == '1' || s.charAt(i-1) == '2') {
                    num[index] = num[index - 2];
                } else {
                    return 0;
                }
            } else {
                if (s.charAt(i - 1) == '1') {
                    num[index] = num[index - 1] + num[index - 2];
                } else {
                    num[index] = num[index - 1];
                }
            }
        }
        return num[s.length()];
    }


    /**
     * leetcode 98
     * */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTImpl(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValidBSTImpl(TreeNode node, Long min, Long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return isValidBSTImpl(node.left, min, (long)node.val) && isValidBSTImpl(node.right, (long)node.val, max);
    }

    /**
     * leetcode 101
     * */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricImpl(root.left, root.right);
    }
    public boolean isSymmetricImpl(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 ==null) {
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
     * leetcode 102
     * */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        int cur = 1;
        int next = 0;
        List<Integer> lineResult = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode c = queue.poll();
            lineResult.add(c.val);
            cur--;
            if (c.left != null) {
                queue.offer(c.left);
                next++;
            }
            if (c.right != null) {
                queue.offer(c.right);
                next++;
            }

            if (cur == 0) {
                cur = next;
                next = 0;
                result.add(new ArrayList<>(lineResult));
                lineResult = new ArrayList<>();
            }
        }
        return result;
    }


    /**
     * leetcode 103
     * */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> cur = new LinkedList<>();

        queue.offer(root);
        int lineIndex = 0;
        int curNum = 1;
        int nextNum = 0;
        while (!queue.isEmpty()) {
            TreeNode c = queue.poll();
            curNum--;
            if (lineIndex % 2 == 1) {
                cur.offerFirst(c.val);
            } else {
                cur.offerLast(c.val);
            }
            if (c.left != null) {
                queue.offer(c.left);
                nextNum++;
            }
            if (c.right != null) {
                queue.offer(c.right);
                nextNum++;
            }

            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                lineIndex++;
                result.add(new ArrayList<>(cur));
                cur = new LinkedList<>();
            }
        }
        return result;
    }


    /**
     * leetcode 104
     * */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return maxDepthImpl(root);
    }
    public int maxDepthImpl(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxDepthImpl(node.left), maxDepthImpl(node.right)) + 1;
    }


    /**
     * leetcode 118
     * */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        int[][] nums = new int[numRows][numRows];
        nums[0][0] = 1;
        for (int i = 0; i < numRows; i++) {
            nums[i][0] = 1;
            nums[i][i] = 1;
        }

        for (int i = 1; i < numRows; i++) {
            for (int j = 1; j < i; j++) {
                nums[i][j] = nums[i-1][j-1] + nums[i-1][j];
            }
        }
        for (int i = 0; i < numRows; i++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                cur.add(nums[i][j]);
            }
            result.add(new ArrayList<>(cur));
            cur = new ArrayList<>();
        }
        return result;
    }

    /**
     * leetcode 121
     * */
    public int maxProfit(int[] prices) {
        int maxP = 0;
        if (prices.length < 2) {
            return maxP;
        }
        int minV = prices[0];
        for (int i = 1; i < prices.length; i++) {
            maxP = Math.max(maxP, prices[i] - minV);
            minV = Math.min(minV, prices[i]);
        }
        return maxP;
    }

    public int maxProfitV2(int[] prices) {
        int maxP = 0;
        if (prices.length < 2) {
            return maxP;
        }
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i-1] > 0) {
                maxP += prices[i] - prices[i-1];
            }
        }
        return maxP;
    }


    /**
     * leetcode 125
     * */
    public boolean isPalindrome(String s) {
        int low = 0;
        int high = s.length() - 1;
        while (low < high) {
            while ((low < high) && !Character.isLetterOrDigit(s.charAt(low))) {
                low++;
            }
            while ((low < high) && !Character.isLetterOrDigit(s.charAt(high))) {
                high--;
            }
            if (Character.toLowerCase(s.charAt(low)) != Character.toLowerCase(s.charAt(high))) {
                return false;
            } else {
                low++;
                high--;
            }
        }
        return true;
    }


    /**
     * leetcode 128
     * */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                int cur = 1;
                int curNum = nums[i];
                set.remove(curNum);
                while (set.contains(curNum - 1)) {
                    cur++;
                    curNum--;
                    set.remove(curNum);
                }
                curNum = nums[i];
                while (set.contains(curNum + 1)) {
                    cur++;
                    curNum++;
                    set.remove(curNum);
                }
                maxLen = Math.max(maxLen, cur);
            }
        }
        return maxLen;
    }


    /**
     * leetcode 131
     * */
    public List<List<String>> partition(String s) {
        boolean[][] palindromeDict = new boolean[s.length()][s.length()];
        getPalDict(s, palindromeDict);
        List<List<String>> result = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        partitionImpl(s, 0, palindromeDict, result, cur);
        return result;
    }
    public void partitionImpl(String s, int index, boolean[][] palindromeDict, List<List<String>> result, List<String> cur) {
        if (index == s.length()) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (palindromeDict[index][i]) {
                cur.add(s.substring(index, i + 1));
                partitionImpl(s, i + 1, palindromeDict, result, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public void getPalDict(String s, boolean[][] palindromeDict) {
        for (int i = 0; i < s.length(); i++) {
            palindromeDict[i][i] = true;
        }
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    palindromeDict[i][j] = (j-i <2) || palindromeDict[i + 1][j - 1];
                }
            }
        }
    }


    /**
     * leetcode 130
     * */
    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                board[i][0] = 'M';
                solveImpl(board, i, 0);
            }
            if (board[i][board[0].length - 1] == 'O') {
                board[i][board[0].length - 1] = 'M';
                solveImpl(board, i, board[0].length - 1);
            }
        }

        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j] == 'O') {
                board[0][j] = 'M';
                solveImpl(board, 0, j);
            }
            if (board[board.length - 1][j] == 'O') {
                board[board.length - 1][j] = 'M';
                solveImpl(board, board.length - 1, j);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'M') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }

    }
    public void solveImpl(char[][] board, int row, int column) {
        if (row > 0 && board[row - 1][column] == 'O') {
            board[row - 1][column] = 'M';
            solveImpl(board, row - 1, column);
        }
        if (row < board.length - 1 && board[row + 1][column] == 'O') {
            board[row + 1][column] = 'M';
            solveImpl(board, row + 1, column);
        }
        if (column > 0 && board[row][column - 1] == 'O') {
            board[row][column - 1] = 'M';
            solveImpl(board, row, column - 1);
        }
        if (column < board[0].length - 1 && board[row][column + 1] == 'O') {
            board[row][column + 1] = 'M';
            solveImpl(board, row , column + 1);
        }
    }


    /**
     * leetcode 136
     * */
    public int singleNumber(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        return result;
    }

    /**
     * leetcode 139
     * */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] nums = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    nums[i] = nums[i] || set.contains(s.substring(0, i + 1));
                } else {
                    nums[i] = nums[i] || (nums[j] && set.contains(s.substring(j + 1, i + 1)));
                }
            }
        }
        return nums[s.length() - 1];
    }



    /**
     * leetcode 140
     * */
    public List<String> wordBreakV1(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        Set<String> set = new HashSet<>(wordDict);
        List<List<String>> myList = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        wordBreakImpl(s, 0,  myList, cur , set);
        for (int i = 0; i < myList.size(); i++) {
            result.add(String.join(" ", myList.get(i)));
        }
        return result;
    }
    public void wordBreakImpl(String s, int index, List<List<String>> result, List<String> cur, Set<String> wordD) {
        if (index == s.length()) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (wordD.contains(s.substring(index, i + 1))) {
                cur.add(s.substring(index, i + 1));
                wordBreakImpl(s, i + 1, result, cur, wordD);
                cur.remove(cur.size() - 1);
            }
        }
    }



    /**
     * leetcode 141
     * */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode walk = head;
        ListNode runner = head;

        while (runner.next != null && runner.next.next != null) {
            walk = walk.next;
            runner = runner.next.next;
            if (walk == runner) {
                return true;
            }
        }
        return false;
    }



    /**
     * leetcode 148
     * */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        ListNode walker = head;
        ListNode runner = head;
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
        }

        ListNode l1 = head;
        ListNode l2 = walker.next;
        walker.next = null;

        ListNode pL1 = sortList(l1);
        ListNode pL2 = sortList(l2);
        ListNode sL = mergeTwoLists(pL1, pL2);
        return sL;
    }



    /**
     * leetcode 149
     * */
    public int maxPoints(int[][] points) {
        if (points.length < 2) {
            return points.length;
        }
        int maxN = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];

                int temp = 0;
                for (int k = j+1; k < points.length; k++) {
                    int x3 = points[k][0];
                    int y3 = points[k][1];
                    if ((x3 - x2) * (y2 - y1) == (x2 - x1) * (y3 - y2)) {
                        temp++;
                    }
                }
                maxN = Math.max(maxN, temp);
            }
        }
        return Math.max(maxN, 0) + 2;
    }


    /**
     * leetcode 150
     * */
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < tokens.length; i++) {
            String c = tokens[i];
            switch (c) {
                case "+":
                    int num1 = stack.pop();
                    int num2 = stack.pop();
                    int num3 = num1 + num2;
                    stack.push(num3);
                    break;
                case "-":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    num3 = num2 - num1;
                    stack.push(num3);
                    break;
                case "*":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    num3 = num1 * num2;
                    stack.push(num3);
                    break;

                case "/":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    num3 = (int)(num2 / num1);
                    stack.push(num3);
                    break;
                default:
                    int n = Integer.parseInt(c);
                    stack.push(n);
            }

        }
        return stack.pop();
    }


    /**
     * leetcode 171
     * */
    public int titleToNumber(String columnTitle) {
        if (columnTitle.length() == 1) {
            return columnTitle.charAt(0) - 'A' + 1;
        }
        int index = 1;
        int sum = columnTitle.charAt(0) - 'A' + 1;
        while (index < columnTitle.length()) {
            sum = 26 * sum + columnTitle.charAt(index) - 'A' + 1;
            index++;
        }
        return sum;
    }


    /**
     * leetcode 189
     * */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        rotateHelper(nums, 0, nums.length - k - 1);
        rotateHelper(nums, nums.length - k, nums.length - 1);
        rotateHelper(nums, 0, nums.length - 1);
    }
    public void rotateHelper(int[] nums, int low, int high) {
        while (low < high) {
            int temp = nums[low];
            nums[low] = nums[high];
            nums[high] = temp;

            low++;
            high--;
        }
    }

    /**
     * leetcode 198
     * */
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] maxV = new int[nums.length];
        maxV[0] = nums[0];
        maxV[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            maxV[i] = Math.max(maxV[i-2] + nums[i], maxV[i-1]);
        }
        return maxV[nums.length - 1];
    }


    /**
     * leetcode 200
     * */
    public int numIslands(char[][] grid) {
        boolean[][] used = new boolean[grid.length][grid[0].length];
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!used[i][j] && grid[i][j] == '1') {
                    used[i][j] = true;
                    numIslandsImpl(grid, used, i, j);
                    num++;
                }
            }
        }
        return num;
    }
    public void numIslandsImpl(char[][] grid, boolean[][] used, int row, int column) {
        if (row > 0 && grid[row - 1][column] == '1' && !used[row - 1][column]) {
            used[row - 1][column] = true;
            numIslandsImpl(grid, used, row - 1, column);
        }
        if (row < grid.length - 1 && grid[row + 1][column]== '1' && !used[row + 1][column]) {
            used[row + 1][column] = true;
            numIslandsImpl(grid, used, row + 1, column);
        }
        if (column > 0 && grid[row][column - 1] == '1' && !used[row][column - 1]) {
            used[row][column - 1] = true;
            numIslandsImpl(grid, used, row, column - 1);
        }
        if (column < grid[0].length - 1 && grid[row][column + 1] == '1' && !used[row][column + 1]) {
            used[row][column + 1] = true;
            numIslandsImpl(grid, used, row, column + 1);
        }
    }

    /**
     * leetcode 212
     * */
    public List<String> findWords(char[][] board, String[] words) {
        return null;
    }
}

