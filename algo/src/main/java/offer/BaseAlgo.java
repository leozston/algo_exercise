package offer;

import java.util.ArrayList;

import static offer.OfferUtils.swap;

/**
 * @description:
 * @author: longlonglv
 * @date: 2022/5/22
 */
public class BaseAlgo {
    public static void main(String[] args) {
        System.out.println("hello world");
        int n = 11111101;
        int t1 = n >> 1;
        int t2 = n >>> 1;
        System.out.println(t1);
        System.out.println(t2);

        int[] nums = {1,2,3,4};
        exchange(nums);
    }

    /**
     * offer03
     * */
    public static int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i && nums[nums[i]] != nums[i]) {
                swap(nums, i, nums[i]);
                i--;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * offer 04
     * */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int right = matrix[0].length - 1;
        int up = 0;

        while (right >= 0 && up <= matrix.length - 1) {
            if (target == matrix[up][right]) {
                return true;
            } else if (target > matrix[up][right]) {
                up++;
            } else {
                right--;
            }
        }
        return false;
    }

    /**
     * offer05
     * 这道题如果是java的话，因为java中string是不可变的，所以只能重新new一个字符串
     * */
    public static String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * offer 15
     * */
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
//            n = n & (n-1);
//            res++;

            /**
             * 注意：>>> 无符号右移，无论正负，都补充0
             * >> 有符号右移，为负号，则补充1
             * */
            res += n & 1;
            n = n >>> 1;
        }
        return res;
    }

    /**
     * offer 16
     * */
    public double myPow(double x, int n) {
        /**
         * 注意这里有一个int->long的转换，主要是防止int负数临界情况
         * */
        long n1 = n;
        if (n1 < 0) {
            return 1 / myPowImp(x, -n1);
        } else {
            return myPowImp(x, n1);
        }
    }

    public double myPowImp(double x, long n) {
        if (n == 0) {
            return 1;
        }
        double t = myPowImp(x, n / 2);
        if (n % 2 == 1) {
            return t * t * x;
        } else {
            return t * t;
        }
    }

    /**
     * offer 29
     * */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int rows = matrix.length;
        int columns = matrix[0].length;

        int up = 0;
        int right = columns - 1;
        int left = 0;
        int down = rows - 1;

        int index = rows * columns;
        ArrayList<Integer> re = new ArrayList<>();
        while (index > 0) {
            for (int i = left; i <= right && index > 0; i++) {
                re.add(matrix[up][i]);
                index--;
            }
            up++;

            for (int i = up; i <= down && index > 0; i++) {
                re.add(matrix[i][right]);
                index--;
            }
            right--;

            for (int i = right; i >= left && index > 0; i--) {
                re.add(matrix[down][i]);
                index--;
            }
            down--;

            for (int i = down; i >= up && index > 0; i--) {
                re.add(matrix[i][left]);
                index--;
            }
            left++;
        }

        int[] result = new int[re.size()];
        for (int i = 0; i < re.size(); i++) {
            result[i] = re.get(i);
        }
        return result;
    }

    /**
     * offer 17
     * */
    public int[] printNumbers(int n) {
        int end = (int)Math.pow(10, n) - 1;
        int[] res = new int[end];
        for (int i = 1; i <= end; i++) {
            res[i-1] = i;
        }
        return res;
    }

    /**
     * offer 20
     * TODO 考虑的情况比较多
     * */
    public boolean isNumber(String s) {
        return true;
    }

    /**
     * offer 21
     * */
    public static int[] exchange(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            while (nums[low] % 2 == 1 && low < high) {
                low++;
            }
            while (nums[high] % 2 == 0 && low < high) {
                high--;
            }
            swap(nums, low, high);
        }
        return nums;
    }
}
