package offer;

/**
 * @description:
 * @author: longlonglv
 * @date: 2022/5/22
 */
public class HalfSearch {
    public static void main(String[] args) {

    }


    /**
     * offer 11
     * */
    public int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;

        while (low < high) {
            int mid = (low + high) / 2;
            int temp = numbers[mid];
            if (temp > numbers[high]) {
                low = mid + 1;
            } else if (temp < numbers[high]) {
                high = mid;
            } else if (temp == numbers[high]) {
                high--;
            }
        }
        return low;
    }

    /**
     * offer 53-I
     * */
    public int search(int[] nums, int target) {
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

        if (index== -1) {
            return 0;
        }

        int left = -1;
        int leftHigh = index - 1;
        int leftLow = 0;
        while (leftLow <= leftHigh) {
            int mid = (leftLow + leftHigh) / 2;
            if (nums[mid] == nums[index]) {
                leftHigh = mid - 1;
                left = mid;
            } else {
                leftLow = mid + 1;
            }
        }

        int right = -1;
        int rightLow = index + 1;
        int rightHigh = nums.length - 1;
        while (rightLow <= rightHigh) {
            int mid = (rightLow + rightHigh) / 2;
            if (nums[mid] == nums[index]) {
                rightLow = mid + 1;
                right = mid;
            } else {
                rightHigh = mid - 1;
            }
        }

        if (left == -1) {
            left = index;
        }
        if (right == -1) {
            right = index;
        }
        return right - left + 1;
    }

    /**
     * offer 53-II
     * */
    public int missingNumber(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        if (nums[high] == nums.length - 1) {
            return nums.length;
        }

        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] == mid) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
