package hot;

import static hot.HotUtils.*;

/**
 * @description:
 * @author: longlonglv
 * @date: 2022/4/30
 */
public class HalfSearch {

    public static void main(String[] args) {
        System.out.println("hello world");

//        test 33
//        int[] nums = {4,5,6,7,0,1,2};
//        int[] nums = {5, 1, 3};
//        int index = search(nums, 3);
//        System.out.println(index);

//        test 34
        int[] nums = {5,7,7,8,8,10};
        int[] result = searchRange(nums, 8);
        printArray(result);
    }

    /**
     * leetcode4
     * TODO
     * */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }

    /**
     * LeetCode 33
     * 二分查找的变种，很常见的题
     * */
    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            }
//            左边有序，注意这里的等号
            if (nums[mid] >= nums[low]) {
                if (nums[mid] > target && nums[low] <= target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {  // 右边有序
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
     * LeetCode 34
     * 二分查找边界
     * */
    public static int[] searchRange(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        int index = -1;
        int[] result = {-1, -1};
        while (low <= high) {
            int mid = (low + high) / 2;
            if (target == nums[mid]) {
                index = mid;
                break;
            }
            if (target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (index == -1) {
            return result;
        }

        int leftLow = 0;
        int leftHigh = index - 1;
        int leftIndex = -1;
        while (leftLow <= leftHigh) {
            int mid = (leftLow + leftHigh) / 2;
            if (target == nums[mid]) {
                leftIndex = mid;
                leftHigh = mid - 1;
            } else if (target > nums[mid]) {
                leftLow = mid + 1;
            }
        }

        int rightLow = index + 1;
        int rightHigh = nums.length - 1;
        int rightIndex = -1;
        while (rightLow <= rightHigh) {
            int mid = (rightLow + rightHigh) / 2;
            if (target == nums[mid]) {
                rightIndex = mid;
                rightLow = mid + 1;
            } else if (target < nums[mid]) {
                rightHigh = mid - 1;
            }
        }
        if (leftIndex == -1) {
            result[0] = index;
        } else {
            result[0] = leftIndex;
        }
        if (rightIndex == -1) {
            result[1] = index;
        } else {
            result[1] = rightIndex;
        }
        return result;
    }

}
