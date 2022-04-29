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
        int[] nums = {1,8,6,2,5,4,8,3,7};
        int result = maxArea(nums);
        System.out.println(result);
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
}
