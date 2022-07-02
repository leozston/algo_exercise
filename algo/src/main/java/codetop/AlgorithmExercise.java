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
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; i < matrix[0].length; j++) {

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
}

