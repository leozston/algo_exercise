package hot100;

import static hot100.HotUtils.*;

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
//        int[] nums = {5,7,7,8,8,10};
//        int[] result = searchRange(nums, 8);
//        printArray(result);

//        test 240
//        int[][] nums = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
//        boolean r = searchMatrix(nums, 5);
//        System.out.println(r);

//        test 287
        int[] nums = {1,3,4,2,2};
        int r = findDuplicate(nums);
        System.out.println(r);
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


    /**
     * LeetCode 240
     * 折半查找的变形题，折半查找还有很多其他的变形题，需要掌握
     * */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int column = matrix[0].length;

        int i = 0;
        int j = column - 1;
        while (i < row && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    /**
     * LeetCode 287
     * */
    public static int findDuplicate(int[] nums) {
        int low = 1;
        int high = nums.length;
        while (low < high) {
            int mid = (low + high) / 2;
            int number = findDuplicateNum(nums, low, mid);
            if (number > (mid - low + 1)) {
                high = mid;
            } else if (number == mid - low + 1) {
                low = mid + 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    public static int findDuplicateNum(int[] nums, int low, int high) {
        int number = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= low && nums[i] <= high) {
                number++;
            }
        }
        return number;
    }
}
