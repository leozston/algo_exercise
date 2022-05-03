package hot;

import java.util.*;

import static hot.HotUtils.*;

/**
 * @description:
 * @author: longlonglv
 * @date: 2022/4/29
 */
public class BaseAlgo {
    public static void main(String[] args) {
        System.out.println("hello world");

        // test t1
//        int[] nums = {2,7,11,15};
//        int[] result = twoSum(nums, 9);
//        printArray(result);

        // test3
//        String s = "abcabcbb";
//        int max = lengthOfLongestSubstring(s);
//        System.out.println(max);

//        test11
//        int[] nums = {1,8,6,2,5,4,8,3,7};
//        int result = maxArea(nums);
//        System.out.println(result);

//        test15
//        int[] nums = {-1,0,1,2,-1,-4};
//        List<List<Integer>> result = threeSum(nums);
//        printArrayList(result);

//        test20
//        String s = "()[]{}";
//        boolean r = isValid(s);
//        System.out.println(r);

//        test23
//        int[] nums = {1,1};
//        nextPermutation(nums);
//        printArray(nums);

//        test32
//        String s = ")()())";
//        int r = longestValidParentheses(s);
//        System.out.println(r);

//        test 42
//        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
//        int[] nums = {4,2,0,3,2,5};
//        int r = trap(nums);
//        System.out.println(r);

//        test 48
//        int[][] nums = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
//        rotate(nums);

//        String[] s = {"eat", "tea", "tan", "ate", "nat", "bat"};
//        List<List<String>> r = groupAnagrams(s);
//        printArrayListString(r);

//        test55
//        int[] nums = {3,2,1,0,4};
//        boolean r = canJump(nums);
//        System.out.println(r);

//        test56
//        int[][] nums = {{1,3},{2,6},{8,10},{15,18}};
//        int[][] values = merge(nums);
//        printTwoDimensionArray(values);

//        test 75
//        int[] nums = {2,0,2,1,1,0};
//        int[] nums = {2,0,2,1,1,0};
//        sortColors(nums);
//        printArray(nums);

//        test 121
//        int[] nums = {7,1,5,3,6,4};
//        int r = maxProfit(nums);
//        System.out.println(r);

//        test 215
        int[] nums = {3,2,1,5,6,4};
        int r = findKthLargest(nums, 2);
        System.out.println(r);
    }

    /**
     * leetcode1
     * 加一个hash保存值与index
     * */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = {-1, -1};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }


    /**
     * leetcode3
     * */
    public static int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int startIndex = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) {
                set.add(s.charAt(i));
                if (maxLen < i - startIndex + 1) {
                    maxLen = i - startIndex + 1;
                }
            } else {
                while (s.charAt(startIndex) != s.charAt(i)) {
                    set.remove(s.charAt(startIndex));
                    startIndex++;
                }
                set.remove(s.charAt(startIndex));
                startIndex++;
                set.add(s.charAt(i));
            }
        }
        return maxLen;
    }


    /**
     * LeetCode 11
     * 双指针，分别指向首和尾，要获取最大面积，首指针向右，尾指针向左，知道重合
     * */
    public static int maxArea(int[] height) {
        int low = 0;
        int high = height.length - 1;

        int maxArea = 0;
        while (low < high) {
            int temp = Math.min(height[low], height[high]) * (high - low);
            if (maxArea < temp) {
                maxArea = temp;
            }
            if (height[low] < height[high]) {
                low++;
            } else {
                high--;
            }
        }
        return maxArea;
    }

    /**
     * LeetCode15
     * 夹逼法
     * */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                if (low > i + 1 && nums[low] == nums[low - 1]) {
                    low++;
                    continue;
                }
//                当遇到等于target的时候，low++，所以这里的high重复判断可以去掉
//                if (high < nums.length - 1 && nums[high] == nums[high + 1]) {
//                    high--;
//                    continue;
//                }
                if (nums[low] + nums[high] == -nums[i]) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[i]);
                    cur.add(nums[low]);
                    cur.add(nums[high]);
                    result.add(new ArrayList<>(cur));
                    low++;
                } else if (nums[low] + nums[high] > -nums[i]) {
                    high--;
                } else {
                    low++;
                }
            }
        }
        return result;
    }

    /**
     * LeetCode20
     * */
    public static boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character c = stack.pop();
                if ((c == '(' && s.charAt(i) != ')') || (c == '[' && s.charAt(i) != ']') || (c == '{' && s.charAt(i) != '}')) {
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
     * LeetCode 31
     * 这实质是一个找规律题
     * */
    public static void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int index = nums.length - 2;
        while (index >= 0 && nums[index] >= nums[index + 1]) {
            index--;
        }
        if (index == -1) {
            // 将逆序排序改成正序排列
            int low = 0;
            int high = nums.length - 1;
            while (low < high) {
                swap(nums, low, high);
                low++;
                high--;
            }
            return;
        }

        int higerIndex = nums.length - 1;
        while (nums[higerIndex] <= nums[index]) {
            higerIndex--;
        }
        swap(nums, index, higerIndex);

        // 将逆序排序改成正序排列
        int low = index + 1;
        int high = nums.length - 1;
        while (low < high) {
            swap(nums, low, high);
            low++;
            high--;
        }
    }


    /**
     * LeetCode 32
     * 技巧题：用一个stack保存index
     * */
    public static int longestValidParentheses(String s) {
        int startIndex = 0;
        int maxLen = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    int temp = stack.pop();
                    if (!stack.isEmpty()) {
                        maxLen = Math.max(maxLen, i - stack.peek());
                    } else {
                        maxLen = Math.max(maxLen, i - startIndex + 1);
                    }

                } else {
                    startIndex = i + 1;
                }
            }
        }
        return maxLen;
    }


    /**
     * LeetCode 42
     * 很常见的一个题
     * */
    public static int trap(int[] height) {
        int[] left = new int[height.length];
        int[] right = new int[height.length];

        int maxLeft = 0;
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(maxLeft, height[i - 1]);
            maxLeft = Math.max(maxLeft, height[i - 1]);
        }

        int maxRight = 0;
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(maxRight, height[i + 1]);
            maxRight = Math.max(maxRight, height[i + 1]);
        }

        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            int temp = Math.min(left[i], right[i]);
            sum += Math.max(temp - height[i], 0);
        }
        return sum;
    }

    /**
     * LeetCode 48
     * 主要掌握对应的位置关系:(i,j)->(len-i-1, j),即：(x,y)->(len-y-1,x)
     * */
    public static void rotate(int[][] matrix) {
        int len = matrix.length;
        int layer = len / 2;
        for (int i = 0; i < layer; i++) {
            for (int j = i; j < len - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len - j - 1][i];
                matrix[len - j - 1][i] = matrix[len - i - 1][len - j - 1];
                matrix[len - i - 1][len - j - 1] = matrix[j][len - i - 1];
                matrix[j][len - i - 1] = temp;
            }
        }
    }

    /**
     * LeetCode 49
     * */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            Arrays.sort(cs);
            String t_s = String.valueOf(cs);
            if (map.containsKey(t_s)) {
                List<String> t = map.get(t_s);
                t.add(strs[i]);
                map.put(t_s, t);
            } else {
                List<String> cur = new ArrayList<>();
                cur.add(strs[i]);
                map.put(t_s, cur);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> e: map.entrySet()) {
            result.add(e.getValue());
        }
        return result;
    }

    /**
     * LeetCode 55
     * */
    public static boolean canJump(int[] nums) {
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
     * LeetCode 56
     * 区间合并比较常见的一个题
     * */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0 || intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > result.get(result.size() - 1)[1]) {
                result.add(intervals[i]);
            } else {
                result.get(result.size() - 1)[1] = Math.max(result.get(result.size() - 1)[1], intervals[i][1]);
            }
        }
        int[][] values = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            values[i] = result.get(i);
        }
        return values;
    }


    /**
     * LeetCode 75
     * 双指针的题目
     * */
    public static void sortColors(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < nums.length - 1 && nums[low] == 0) {
            low++;
        }
        while (high >= 0 && nums[high] == 2) {
            high--;
        }
        for (int i = low; i <= high; i++) {
            if (nums[i] == 0) {
                swap(nums, low, i);
                low++;
//                注意：这里不要i--，否则会出现low跑到i的前面去
//                i--;
            } else if (nums[i] == 2) {
                swap(nums, i, high);
                high--;
                i--;
            }
        }

    }


    /**
     * LeetCode 76
     * 字符串操作的题目，必须要掌握的，高频题
     * TODO
     * */
    public String minWindow(String s, String t) {
        return null;
    }

    /**
     * LeetCode 121
     * 股票的题目，LeetCode中股票题目最少有4中类型，都需要掌握，这是最简单的一种类型
     * */
    public static int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int min = prices[0];
        int maxValue = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            maxValue = Math.max(prices[i] - min, maxValue);
            min = Math.min(prices[i], min);
        }
        return Math.max(maxValue, 0);
    }

    /**
     * LeetCode 215
     * 常见的题目，注意时间复杂度是O(n)
     * */
    public static int findKthLargest(int[] nums, int k) {
        if (nums.length < k) {
            return -1;
        }
        k = nums.length - k;
        return findKthLargestImpl(nums, k, 0, nums.length - 1);
    }
    public static int findKthLargestImpl(int[] nums, int k, int low, int high) {
        int index = findQsortIndex(nums, low, high);
        if (index == k) {
            return nums[index];
        } else if (index < k) {
            return findKthLargestImpl(nums, k,index + 1, high);
        } else {
            return findKthLargestImpl(nums, k, low, index-1);
        }
    }

    public static int findQsortIndex(int[] nums, int low, int high) {
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
}
