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
        int[] nums = {2,0,2,1,1,0};
//        sortColors(nums);
//        System.out.println(r);
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
//        System.out.println(stack.peekFirst());
//        System.out.println(stack.peekLast());

//        PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });
//        q.offer(0);
//        q.offer(3);
//        q.offer(1);
//        q.offer(2);
//        q.offer(4);
//        while (!q.isEmpty()) {
//            System.out.println(q.poll());
//        }

//        System.out.println(Integer.valueOf('4'));
        String s = "(1+(4+5+2)-3)+(6+8)";
        int r = calculateV1_1(s);

        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(2);
        l1.remove(1);
        System.out.println(l1.size());
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
        PriorityQueue<Integer> queue1 = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

//        PriorityQueue<Integer> queue1 = new PriorityQueue<Integer>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return 0;
//            }
//        })
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
     * leetcode 10
     * */
    public boolean isMatch(String s, String p) {
        boolean[][] result = new boolean[s.length() + 1][p.length() + 1];
        result[0][0] = true;
        for (int i = 1; i < s.length() + 1; i++) {
            result[i][0] = false;
        }
        for (int i = 1; i < p.length() + 1; i++) {
            if (p.charAt(i - 1) == '*') {
                result[0][i] = result[0][i-1] || (i>1 && result[0][i-2]);
            } else {
                result[0][i] = false;
            }
        }


        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (s.charAt(i-1) == p.charAt(j-1)) {
                    result[i][j] = result[i-1][j-1];
                } else if (p.charAt(j-1) == '.') {
                    result[i][j] = result[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    result[i][j] = result[i][j-2] || result[i][j-1] || ((s.charAt(i-1) == p.charAt(j-2)|| p.charAt(j-2) == '.') && result[i-1][j]);
                }
            }
        }
        return result[s.length()][p.length()];
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
     * leetcode 13
     * */
    public int romanToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            switch (c) {
                case 'I':
                    if (i < s.length() - 1 && (s.charAt(i + 1) == 'V' || s.charAt(i+1) == 'X')) {
                        result -= 1;
                    } else {
                        result += 1;
                    }
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'X':
                    if (i < s.length() - 1 && (s.charAt(i + 1) == 'L' || s.charAt(i+1) == 'C')) {
                        result -= 10;
                    } else {
                        result += 10;
                    }
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'C':
                    if (i < s.length() - 1 && (s.charAt(i + 1) == 'D' || s.charAt(i+1) == 'M')) {
                        result -= 100;
                    } else {
                        result += 100;
                    }
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'M':
                    result += 1000;
                    break;
            }
        }
        return result;
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
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int cur = -nums[i];
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                if (low > i + 1 && nums[low] == nums[low - 1]) {
                    low++;
                    continue;
                }
                if (nums[low] + nums[high] == cur) {
                    List<Integer> curL = new ArrayList<>();
                    curL.add(-cur);
                    curL.add(nums[low]);
                    curL.add(nums[high]);
                    result.add(curL);
                } else if (nums[low] + nums[high] > cur) {
                    high--;
                } else {
                    low++;
                }
            }
        }
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
     * leetcode 26
     * */
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int low = 0;
        int high = 1;
        while (high < nums.length) {
            if (nums[high] == nums[low]) {
                high++;
            } else {
                low++;
                swap(nums, low, high);
                high++;
            }
        }
        return low+ 1;
    }

    public static void swap(int[] nums, int low, int high) {
        int temp = nums[low];
        nums[low] = nums[high];
        nums[high] = temp;
    }


    /**
     * leetcode 29
     * */
    public int divide(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }

        boolean flag1 = true;
        boolean flag2 = true;
        if (dividend < 0) {
            flag1 = false;
            dividend = -dividend;
        }
        if (divisor < 0) {
            flag2 = false;
            divisor = - divisor;
        }

        int sum = 0;
        while (dividend >= divisor) {
            int cur = divisor;
            int sum1 = 1;
            while (dividend >= (cur + cur)) {
                sum1 += sum1;
                cur += cur;
            }
            dividend = dividend - cur;
            sum = sum + sum1;
        }
        return flag1 == flag2 ? sum : -sum;
    }


    /**
     * leetcode 33
     * */
    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] >= nums[low]) {
                if (nums[low] <= target && nums[mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] < target && nums[high] >= target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }


    /**
     * leetcode 38
     * */
    public String countAndSay(int n) {
        String first = "1";
        StringBuilder second = new StringBuilder();
        for (int i = 2; i <=n; i++) {
            Character c = first.charAt(0);
            int c_n = 1;
            for (int j = 1; j < first.length(); j++) {
                Character c1 = first.charAt(j);
                if (c1 == c) {
                    c_n++;
                } else {
                    second.append(c_n);
                    second.append(c);

                    c_n = 1;
                    c = c1;
                }
            }
            second.append(c_n);
            second.append(c);

            first = second.toString();
            second = new StringBuilder();
        }
        return first;
    }


    /**
     * leetcode 40
     * */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        int low = 0;
        int high = nums.length - 1;
        int index = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                index = mid;
                break;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (index == -1) {
            return result;
        }
        int left = index - 1;
        low = 0;
        int leftIndex = -1;
        while (low <= left) {
            int mid = (low + left) / 2;
            if (nums[mid] == target) {
                leftIndex = mid;
                left = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        int right = index + 1;
        high = nums.length - 1;
        int rightIndex = -1;
        while (right <= high) {
            int mid = (right + high) / 2;
            if (nums[mid] == target) {
                rightIndex = mid;
                right = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (leftIndex != -1) {
            result[0] = leftIndex;
        } else {
            result[0] = index;
        }
        if (rightIndex != -1) {
            result[1] = rightIndex;
        } else {
            result[1] = index;
        }
        return result;
    }



    /**
     * leetcode 41
     * */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                continue;
            }
            if (nums[i] != i+1 && nums[i] < nums.length && nums[nums[i] - 1] != nums[i] ) {
                swap(nums, i, nums[i] - 1);
                i--;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }
        return nums.length + 1;
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
     * leetcode 44
     * */
    public boolean isMatchV2(String s, String p) {
        boolean[][] result = new boolean[s.length() + 1][p.length() + 1];
        result[0][0] = true;
        for (int i = 1; i < s.length() + 1; i++) {
            result[i][0] = false;
        }
        for (int i = 1; i < p.length() + 1; i++) {
            if (p.charAt(i - 1) == '*') {
                result[0][i] = result[0][i-1];
            } else {
                result[0][i] = false;
            }
        }


        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (s.charAt(i-1) == p.charAt(j-1)) {
                    result[i][j] = result[i-1][j-1];
                } else if (p.charAt(j-1) == '?') {
                    result[i][j] = result[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    result[i][j] = result[i][j-1] || result[i-1][j];
                }
            }
        }
        return result[s.length()][p.length()];
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
     * leetcode 53
     * */
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int localMax = nums[0];
        int globalMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            localMax = Math.max(localMax + nums[i], nums[i]);
            globalMax = Math.max(globalMax, localMax);
        }
        return globalMax;
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
     * leetcode 75
     * */
    public static void sortColors(int[] nums) {
        int low = -1;
        int high = nums.length;

        int index = 0;
        while (index < high) {
            if (nums[index] == 0) {
                low++;
                swap(nums, low, index);
                if (low >= index) {
                    index++;
                }
            } else if (nums[index] == 1) {
                index++;
            } else if (nums[index] == 2) {
                high--;
                swap(nums, index, high);
            }
        }
        CodeTopUtils.printArray(nums);
    }



    /**
     * leetcode 76
     * */
    public String minWindow(String s, String t) {
        String result = "";
        int low = 0;
        int high = 0;
        int numbers = 0;
        int minLen = Integer.MAX_VALUE;

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
            } else {
                map.put(t.charAt(i), 1);
            }
        }

        while (high < s.length()) {
            Character c = s.charAt(high);
            if (map.containsKey(c)) {
                if (map.get(c) > 0) {
                    numbers++;
                }
                map.put(c, map.get(c) - 1);
            }
            while (numbers == t.length()) {
                if (high - low + 1 < minLen) {
                    minLen = high - low + 1;
                    result = s.substring(low, high + 1);
                }
                Character c_low = s.charAt(low);
                if (map.containsKey(c_low)) {
                    map.put(c_low, map.get(c_low) + 1);
                    if (map.get(c_low) > 0) {
                        numbers--;
                    }
                }
                low++;
            }
            high++;
        }
        return result;
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
     * leetcode 94
     * */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode cur = stack.pop();
                result.add(cur.val);
                root = cur.right;
            }
        }
        return result;
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
     * leetcode 108
     * */
    public TreeNode sortedArrayToBST(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        return sortedArrayToBSTImpl(nums, low, high);
    }

    public TreeNode sortedArrayToBSTImpl(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBSTImpl(nums, low, mid - 1);
        node.right = sortedArrayToBSTImpl(nums, mid + 1, high);
        return node;
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
     * leetcode 134
     * */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length <= 0) {
            return 0;
        }
        int[] gap = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            gap[i] = gas[i] - cost[i];
        }

        int sum = 0;
        int curSum = 0;
        int index = -1;
        for (int i = 0; i < gas.length; i++) {
            sum += gap[i];

            curSum += gap[i];
            if (curSum < 0) {
                index = i;
                curSum = 0;
            }
        }
        if (sum >= 0) {
            return (index + 1) % gas.length;
        }
        return -1;
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
     * leetcode 152
     * */
    public int maxProduct(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        int localMax = nums[0];
        int localMin = nums[0];
        int globalMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int localMaxTemp = Math.max(Math.max(localMax * nums[i], nums[i]), localMin * nums[i]);
            localMin = Math.min(Math.min(localMin * nums[i], nums[i]), localMax * nums[i]);

            localMax = localMaxTemp;
            globalMax = Math.max(globalMax, localMax);

        }
        return globalMax;
    }

    /**
     * leetcode 160
     * */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != null) {
            tempA = tempA.next;
            lenA++;
        }
        while (tempB != null) {
            tempB = tempB.next;
            lenB++;
        }
        if (lenA > lenB) {
            return getIntersectionNode(headB, headA);
        }

        int gap = lenB - lenA;
        tempA = headA;
        tempB = headB;
        while (gap > 0) {
            tempB = tempB.next;
            gap--;
        }
        while (tempA != tempB && tempA != null) {
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return tempA;

    }


    /**
     * leetcode 162
     * */
    public int findPeakElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        if (nums.length == 1) {
            return 0;
        }

        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }



    /**
     * leetcode 166
     * */
    public String fractionToDecimal(int numerator, int denominator) {
        boolean flag = true;
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
            flag = false;
        }
        Long numeratorL = Math.abs((long)numerator);
        Long denominatorL = Math.abs((long)denominator);
        String re = String.valueOf(numeratorL / denominatorL);
        if (!flag) {
            re = "-" + re;
        }
        long carry = numeratorL % denominatorL;
        if (carry == 0) {
            return re;
        }

        List<String> list = new ArrayList<>();
        Map<Long, Integer> map = new HashMap<>();
        int index = 0;
        int startIndex = -1;

        carry = (carry % denominatorL) * 10;
        while (carry != 0) {
            if (map.containsKey(carry)) {
                startIndex = map.get(carry);
                break;
            }
            map.put(carry, index);
            index++;
            long v = carry / denominatorL;
            list.add(String.valueOf(v));
            carry = (carry % denominatorL) * 10;
        }

        if (startIndex == -1) {
            re += ".";
            for (int i = 0; i < list.size(); i++) {
                re += list.get(i);
            }
        } else {
            re += ".";
            list.add(startIndex, "(");
            list.add(")");
            for (int i = 0; i < list.size(); i++) {
                re += list.get(i);
            }
        }
        return re;
    }


    /**
     * leetcode 169
     * */
    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int res = nums[0];
        int times = 1;
        for (int i = 1; i < nums.length; i++) {
            if (res == nums[i]) {
                times++;
            } else {
                if (times > 0) {
                    times--;
                } else {
                    res = nums[i];
                    times = 1;
                }
            }
        }
        return res;
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
     * leetcode 172
     * */
    public int trailingZeroes(int n) {
        int sum = 0;
        for (int i = 5; i <= n; i=i+5) {
            int t = i;
            while (t % 5 == 0) {
                sum++;
                t = t /5;
            }
        }
        return sum;
    }

    /**
     * leetcode 179
     * */
    public String largestNumber(int[] nums) {
        Integer[] numsT = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsT[i] = nums[i];
        }

        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (String.valueOf(o2) + String.valueOf(o1)).compareTo(String.valueOf(o1) + String.valueOf(o2));
            }
        };
        Arrays.sort(numsT, c);
        if (numsT[0] == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numsT.length; i++) {
            sb.append(numsT[i]);
        }
        return sb.toString();
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
     * leetcode 190
     * */
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int a = n & 1;
            result = (result << 1) + a;
            n = n >> 1;
        }
        return result;
    }


    /**
     * leetcode 191
     * */
    public int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
//            result += (n & 1);
//            n = n >> 1;
            // opt
            result++;
            n = n & (n-1);
        }
        return result;
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
     * leetcode 202
     * */
    public boolean isHappy(int n) {
        int cur = 0;
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            if (set.contains(n)) {
                return false;
            }
            set.add(n);

            while (n > 0) {
                int a = n % 10;
                n = n / 10;
                cur += a * a;
            }

            System.out.println(cur);
            n = cur;
            cur = 0;
        }
        return true;
    }

    /**
     * leetcode 204
     * */
    public int countPrimes(int n) {
        int re = 0;
        for (int i = 2; i < n; i++) {
            if (countPrimesImpl(i)) {
                re++;
            }
        }
        return re;
    }
    public boolean countPrimesImpl(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * leetcode 206
     * */
    public ListNode reverseList(ListNode head) {
        ListNode helper = null;
        if (head == null) {
            return null;
        }
        while (head != null) {
            ListNode next = head.next;
            head.next = helper;
            helper = head;

            head = next;
        }
        return helper;
    }

    /**
     * leetcode 207
     * */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int src = prerequisites[i][0];
            int depend = prerequisites[i][1];

            if (map.containsKey(src)) {
                map.put(src, map.get(src) + 1);
            } else {
                map.put(src, 1);
            }
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!map.containsKey(i)) {
                queue.offer(i);
            }
        }

        int num = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            num++;

            for (int i = 0; i < prerequisites.length; i++) {
                int src = prerequisites[i][0];
                int depend = prerequisites[i][1];

                if (cur == depend) {
                    map.put(src, map.get(src) - 1);
                    if (map.get(src) == 0) {
                        queue.offer(src);
                    }
                }
            }
        }

        return num == numCourses;
    }



    /**
     * leetcode 210
     * */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int src = prerequisites[i][0];
            int depend = prerequisites[i][1];

            if (map.containsKey(src)) {
                map.put(src, map.get(src) + 1);
            } else {
                map.put(src, 1);
            }
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!map.containsKey(i)) {
                queue.offer(i);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        int num = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            num++;
            list.add(cur);

            for (int i = 0; i < prerequisites.length; i++) {
                int src = prerequisites[i][0];
                int depend = prerequisites[i][1];

                if (cur == depend) {
                    map.put(src, map.get(src) - 1);
                    if (map.get(src) == 0) {
                        queue.offer(src);
                    }
                }
            }
        }
        if (num != numCourses) {
            return new int[0];
        }
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }


    /**
     * leetcode 212
     * */
    public List<String> findWords(char[][] board, String[] words) {
        return null;
    }

    /**
     * leetcode 215
     * */
    public int findKthLargest(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }
        k = nums.length - k;
        return findKthLargestImpl(nums, 0, nums.length - 1, k);
    }
    public int findKthLargestImpl(int[] nums, int low, int high, int k) {
        int index = partition(nums, low, high);
        if (index == k) {
            return nums[index];
        }
        if (index > k) {
            return findKthLargestImpl(nums, low, index - 1, k);
        } else {
            return findKthLargestImpl(nums, index + 1, high, k);
        }
    }

    public int partition(int[] nums, int low, int high) {
        int temp = nums[high];
        while (low < high) {
            while (low < high && nums[low] <= temp) {
                low++;
            }
            swap(nums, low, high);

            while (low < high && nums[high] >= temp) {
                high--;
            }
            swap(nums, low, high);
        }
        return low;
    }

    /**
     * leetcode 217
     * */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }


    /**
     * leetcode 224
     * */
    public int calculate(String s) {
        LinkedList<String> stack = new LinkedList<>();
        LinkedList<Character> stackFlag = new LinkedList<>();
        char preS = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            } else if (c == '(') {
                stack.push("(");
                stackFlag.push(preS);
            } else if (c == ')') {
                int curN1 = 0;
                char c2 = '+';
                while (!stack.isEmpty()) {
                    String s1 = stack.pop();
                    if (s1 == "(") {
                        c2 = stackFlag.pop();
                        break;
                    }
                    curN1 += Integer.valueOf(s1);
                }
                if (c2 == '-') {
                    curN1 = -curN1;
                }
                stack.push(String.valueOf(curN1));
            } else {
                int num = 0;
                if (Character.isDigit(c)) {
                    while ((i + 1) < s.length() && Character.isDigit(s.charAt((i + 1)))) {
                        num = num * 10 + s.charAt(i + 1) - '0';
                        i++;
                    }
                }

                switch (preS) {
                    case '+':
                        stack.push(String.valueOf(num));
                        break;
                    case '-':
                        stack.push(String.valueOf(-num));
                        break;
                }
                if (i < s.length()) {
                    preS = s.charAt(i);
                }
            }
        }
        int result = 0;
        while(!stack.isEmpty()) {
            result += Integer.valueOf(stack.pop());
        }
        return result;
    }


    /**
     * leetcode 227
     * */
    public int calculateV2(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        char preS = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            int num = 0;
            if (Character.isDigit(c)) {
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
            }
            switch (preS) {
                case '+':
                    stack.push(num);
                    break;
                case '-':
                    stack.push(-num);
                    break;
                case '*':
                    int t1 = stack.pop();
                    stack.push(t1 * num);
                    break;
                case '/':
                    int t2 = stack.pop();
                    stack.push(t2/num);
                    break;
                case ' ':
                    break;
            }
            if (i < s.length()) {
                preS = s.charAt(i);
            }
        }
        int result = 0;
        while(!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }


    /**
     * leetcode 224 字符串统一处理方式：两个栈
     * */
    public static LinkedList<Integer> calculateV1Nums = new LinkedList<>();
    public static LinkedList<Character> calculateV1Ops = new LinkedList<>();
    public static int calculateV1_1(String s) {
        StringBuilder sb = new StringBuilder("0");
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                sb.append("(0");
            } else if (s.charAt(i) == ' '){

            } else {
                sb.append(s.charAt(i));
            }
        }
        String s_1 = sb.toString();
        calculateV1Nums = new LinkedList<>();
        calculateV1Ops = new LinkedList<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        for (int i = 0; i < s_1.length(); i++) {
            char c = s_1.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                while ((i+1) < s_1.length() && Character.isDigit(s_1.charAt(i+1))) {
                    num = num * 10 + s_1.charAt(i+1) - '0';
                    i++;
                }

                calculateV1Nums.push(num);
            } else if (c == ' ') {
                continue;
            } else if (c == '(') {
                calculateV1Ops.push(c);
            } else if (c == ')') {
                while (!calculateV1Ops.isEmpty()) {
                    int r = evalV1();
                    if (r == 0) {
                        break;
                    }
                }
            } else if (c == '+' || c == '-') {
                while (!calculateV1Ops.isEmpty() && calculateV1Ops.peek() != '(') {
                    evalV1();
                }
                calculateV1Ops.push(c);
            }
        }

        while (!calculateV1Ops.isEmpty()) {
            evalV1();
        }
        return calculateV1Nums.pop();
    }

    public static int evalV1() {
        char c1 = calculateV1Ops.pop();
        if (c1 == '(') {
            return 0;
        } else if (c1 == '+') {
            int b = calculateV1Nums.pop();
            int a = calculateV1Nums.pop();
            calculateV1Nums.push(a+b);
        } else if (c1 == '-') {
            int b = calculateV1Nums.pop();
            int a = calculateV1Nums.pop();
            calculateV1Nums.push(a-b);
        }
        return 1;
    }


    /**
     * leetcode 227字符串统一处理方式版本：两个栈
     * */
    public static LinkedList<Integer> calculateNums = new LinkedList<>();
    public static LinkedList<Character> calculateOps = new LinkedList<>();
    public int calculateV2_1(String s) {
        StringBuilder sb = new StringBuilder("0");
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' '){

            } else {
                sb.append(s.charAt(i));
            }
        }
        String s_1 = sb.toString();
        calculateNums = new LinkedList<>();
        calculateOps = new LinkedList<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('+', 1);   //定义运算符的优先级
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);

        for (int i = 0; i < s_1.length(); i++) {
            char c = s_1.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                while ((i + 1) < s_1.length() && Character.isDigit(s_1.charAt(i + 1))) {
                    num = num * 10 + s_1.charAt(i + 1) - '0';
                    i++;
                }
                calculateNums.push(num);
            } else if (c == ' ') {
                continue;
            } else {
                while ((!calculateOps.isEmpty()) && (map.get(calculateOps.peek())) >= map.get(c)) {
                    eval();
                }
                calculateOps.push(c);
            }
        }
        while (!calculateOps.isEmpty()) {
            eval();
        }
        return calculateNums.pop();
    }
    public void eval() {
        int b = calculateNums.pop();
        int a = calculateNums.pop();
        char c = calculateOps.pop();
        int r = 0;
        if(c == '+') r = a + b;
        else if(c == '-') r = a - b;
        else if(c == '*') r = a * b;
        else r = a / b;
        calculateNums.push(r);
    }



    /**
     * leetcode 230
     * */
    public static int kthSmallestVar = 0;
    public static int kthSmallestVarRes = 0;
    public int kthSmallest(TreeNode root, int k) {
        kthSmallestVar = k;
        kthSmallestImpl(root);
        return kthSmallestVarRes;
    }

    public void kthSmallestImpl(TreeNode node) {
        if (node == null) {
            return;
        }
        kthSmallestImpl(node.left);
        kthSmallestVar--;
        if (kthSmallestVar == 0) {
            kthSmallestVarRes = node.val;
            return;
        }
        kthSmallestImpl(node.right);
    }


    /**
     * leetcode 234
     * */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode walker = head;
        ListNode runner = head.next;
        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
        }
        ListNode first = head;
        ListNode second = walker.next;
        ListNode helper = null;
        while (second != null) {
            ListNode next = second.next;
            second.next = helper;
            helper = second;
            second = next;
        }

        second = helper;
        while (second != null) {
            if (first.val != second.val) {
                return false;
            }
            first = first.next;
            second = second.next;
        }
        return true;
    }


    /**
     * leetcode 236
     * */
    public static TreeNode lowestCommonAncestorVar = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestorVar = null;
        lowestCommonAncestorImpl(root, p, q);
        return lowestCommonAncestorVar;
    }
    public boolean lowestCommonAncestorImpl(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return false;
        }

        boolean left = lowestCommonAncestorImpl(node.left, p, q);
        boolean right = lowestCommonAncestorImpl(node.right, p, q);
        if ((left && right) || ((left || right) && (node.val == p.val || node.val == q.val))) {
            lowestCommonAncestorVar = node;
        }
        return (left || right || node.val == p.val || node.val == q.val);
    }



    /**
     * leetcode 237
     * */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * leetcode 238
     * */
    public int[] productExceptSelf(int[] nums) {
        int[] l2r = new int[nums.length];
        int[] r2l = new int[nums.length];
        l2r[0] = 1;
        r2l[nums.length - 1] = 1;
        for (int i = 1; i < nums.length; i++) {
            l2r[i] = l2r[i-1] * nums[i - 1];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            r2l[i] = r2l[i + 1] * nums[i + 1];
        }

        int[] res = new int[nums.length];
        for (int i  = 0; i < nums.length; i++) {
            res[i] = l2r[i] * r2l[i];
        }
        return res;
    }


    /**
     * leetcode 239
     * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        if (nums.length < k) {
            return new int[0];
        }

        List<Integer> list = new ArrayList<>();
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(0);
        for (int i = 1; i < k; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            stack.push(i);
        }
        list.add(stack.peekLast());

        for (int i = k; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            if ((!stack.isEmpty()) && (i - stack.peekLast() >= k)) {
                stack.pollLast();
            }
            stack.push(i);
            list.add(stack.peekLast());
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = nums[list.get(i)];
        }
        return res;
    }



    /**
     * leetcode 240
     * */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int column = matrix[0].length - 1;
        while (row < matrix.length && column >= 0) {
            if (target == matrix[row][column]) {
                return true;
            } else if (target > matrix[row][column]) {
                row++;
            } else {
                column--;
            }
        }
        return false;
    }


    /**
     * leetcode 242
     * */
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            if (!map.containsKey(t.charAt(i))) {
                return false;
            }
            if (map.get(t.charAt(i)) < 1) {
                return false;
            }
            map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
        }
        return true;
    }


    /**
     * leetcode 268
     * */
    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i || nums[i] < 0 || nums[i] >= nums.length) {
                continue;
            }
            if (nums[i] == nums[nums[i]]) {
                continue;
            }
            swap(nums, i, nums[i]);
            i--;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }


    /**
     * leetcode 279
     * */
    public int numSquares(int n) {
        int[] res = new int[n+1];
        res[1] = 1;
        for (int i = 2; i <= n; i++) {
            int curMin = i;
            if (((int)Math.sqrt(i)) * ((int)Math.sqrt(i)) == i) {
                res[i] = 1;
                continue;
            }
            for (int j = 1; j * j < i; j++) {
//                这里的递推关系，可以利用j*j的性质
//                curMin = Math.min(res[j] + res[i - j], curMin);
                curMin = Math.min(curMin, res[i - j * j] + 1);
            }
            res[i] = curMin;
        }
        return res[n];
    }


    /**
     * leetcode 283
     * */
    public void moveZeroes(int[] nums) {
        int low = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, low + 1, i);
                low++;
            }
        }
    }


    /**
     * leetcode 287
     * */
    public int findDuplicate(int[] nums) {
        int low = 1;
        int high = nums.length;
        while (low < high) {
            int mid = (low + high) / 2;
            int num = findNums(nums, low, mid);
            if (num == mid - low + 1) {
                low = mid + 1;
            } else if (num > (mid - low + 1)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    public int findNums(int[] nums, int low, int high) {
        int re = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= low && nums[i] <= high) {
                re++;
            }
        }
        return re;
    }


    /**
     * leetcode 300
     * */
    public int lengthOfLIS(int[] nums) {
        int[] res = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            res[i + 1] = 1;
        }
        int maxLen = 1;
        for (int i = 0; i < nums.length; i++) {
            int curMax = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    curMax = Math.max(curMax, res[j + 1] + 1);
                }
            }
            res[i+1] = curMax;
            maxLen = Math.max(maxLen, curMax);
        }
        return maxLen;
    }



    /**
     * leetcode 322
     * */
    public int coinChange(int[] coins, int amount) {
        int[] res = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            int temp = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i == coins[j]) {
                    temp = 1;
                    break;
                } else if (i < coins[j]) {
                    continue;
                } else {
                    if (res[i - coins[j]] != Integer.MAX_VALUE) {
                        temp = Math.min(res[i - coins[j]] + 1, temp);
                    }
                }
            }
            res[i] = temp;
        }
        return res[amount] == Integer.MAX_VALUE? -1 : res[amount];
    }


    /**
     * leetcode 324
     * */
    public void wiggleSort(int[] nums) {

    }


    /**
     * leetcode 326
     * */
    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }
        int ex = 0;
        while (n != 1) {
            ex = n % 3;
            n = n / 3;
            if (ex != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * leetcode 328
     * */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode h1 = head;
        ListNode h2 = head.next;
        ListNode helper = h2;
        while (h2 != null && h2.next != null) {
            h1.next = h2.next;
            h1 = h1.next;

            h2.next = h1.next;
            h2 = h2.next;
        }
        h1.next = helper;
        return head;
    }


    /**
     * leetcode 344
     * */
    public void reverseString(char[] s) {
        int low = 0;
        int high = s.length - 1;
        while (low < high) {
            char t = s[low];
            s[low] = s[high];
            s[high] = t;
            low++;
            high--;
        }
    }

    /**
     * leetcode 347
     * */
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            queue.offer(e);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] res = new int[queue.size()];
        int index = 0;
        while (!queue.isEmpty()) {
            res[index] = queue.poll().getKey();
            index++;
        }
        return res;
    }

    /**
     * leetcode 350
     * */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map1.put(nums1[i], map1.getOrDefault(nums1[i], 0) + 1);
        }

        for (int i = 0; i < nums2.length; i++) {
            map2.put(nums2[i], map2.getOrDefault(nums2[i], 0) + 1);
        }

        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : map1.entrySet()) {
            if (map2.containsKey(e.getKey())) {
                int t = Math.min(e.getValue(), map2.get(e.getKey()));
                for (int i = 0; i < t; i++) {
                    list.add(e.getKey());
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }


    /**
     * leetcode 371
     * */
    public int getSum(int a, int b) {
        int m = a & b;
        m = m << 1;
        int n = a ^ b;
        return m+n;
    }

    /**
     * leetcode 378
     * */
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < matrix.length; i++) {
            int[] nums = new int[3];
            nums[0] = matrix[i][0];
            nums[1] = i;
            nums[2] = 0;
            queue.offer(nums);
        }

        for (int i = 0; i < k - 1; i++) {
            int[] nums = queue.poll();
            int row = nums[1];
            int column = nums[2];
            if (column + 1 < matrix[row].length) {
                int[] t = new int[3];
                t[0] = matrix[row][column + 1];
                t[1] = row;
                t[2] = column + 1;
                queue.offer(t);
            }
        }
        return queue.poll()[0];
    }


    /**
     * leetcode 387
     * */
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }



    /**
     * leetcode 412
     * */
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String s = String.valueOf(i);
            if (i % 3 == 0 && i % 5 == 0) {
                s = "FizzBuzz";
            } else if(i % 3 == 0) {
                s = "Fizz";
            } else if (i % 5 == 0) {
                s = "Buzz";
            }
            list.add(s);
        }
        return list;
    }


    /**
     * leetcode 454
     * */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int n1 = nums1[i] + nums2[j];
                map1.put(n1, map1.getOrDefault(n1, 0) + 1);
            }
        }

        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int n1 = nums3[i] + nums4[j];
                map2.put(n1, map2.getOrDefault(n1, 0) + 1);
            }
        }

        int res = 0;
        for (Map.Entry<Integer, Integer> e : map1.entrySet()) {
            int key = e.getKey();
            int value = e.getValue();
            if (map2.containsKey(-key)) {
                res += value * map2.get(-key);
            }
        }
        return res;
    }
}



