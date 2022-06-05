package offer;

import java.util.*;

import static offer.OfferUtils.*;

/**
 * @description:
 * @author: longlonglv
 * @date: 2022/5/22
 */
public class BaseAlgo {
    public static void main(String[] args) {
//        System.out.println("hello world");
//        int n = 11111101;
//        int t1 = n >> 1;
//        int t2 = n >>> 1;
//        System.out.println(t1);
//        System.out.println(t2);
//
//        int[] nums = {1,2,3,4};
//        exchange(nums);

//        int[] nums = {3,2,1};
//        int[] r = getLeastNumbers(nums, 2);
//        printArray(r);
//        String s = "the sky is blue";
//        String s1 = reverseWords(s);
//        System.out.println(s1);

//        int[] nums = new int[]{0};
//        System.out.println(nums.length);
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



    /**
     * offer 39
     * */
    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int major = nums[0];
        int times = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == major) {
                times++;
            } else {
                if (times == 0) {
                    major = nums[i];
                    times = 1;
                } else {
                    times--;
                }
            }
        }
        return major;
    }

    /**
     * offer 40
     * O(n)
     * */
    public static int[] getLeastNumbers(int[] arr, int k) {
        k = k - 1;
        int low = 0;
        int high = arr.length - 1;
        int[] result = new int[k + 1];
        while (low <= high) {
            int index = getLeastNumbersImpl(arr, low, high);
            if (index == k) {
                for (int i = 0; i <= k; i++) {
                    result[i] = arr[i];
                }
                return result;
            } else if (index > k) {
                high = index - 1;
            } else {
                low = index + 1;
            }
        }
        return new int[0];
    }
    public static int getLeastNumbersImpl(int[] arr, int low, int high) {
        int temp = arr[high];
        while (low < high) {
            while (low < high && arr[low] <= temp) {
                low++;
            }
            swap(arr, low, high);

            while (low < high && temp <= arr[high]) {
                high--;
            }
            swap(arr, low, high);
        }
        return low;
    }


    /**
     * offer 48
     * */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int low = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) {
                set.add(s.charAt(i));
                maxLen = Math.max(maxLen, i - low + 1);
            } else {
                while (low < i) {
                    if (s.charAt(low) == s.charAt(i)) {
                        set.remove(s.charAt(low));
                        low++;
                        set.add(s.charAt(i));
                        break;
                    }
                    set.remove(s.charAt(low));
                    low++;
                }
            }
        }
        return maxLen;
    }

    /**
     * offer 49
     * */
    public int nthUglyNumber(int n) {
        int p2 = 1;
        int p3 = 1;
        int p5 = 1;
        int num = 1;
        int[] result = new int[n+1];
        result[1] = 1;
        while (num < n) {
            int p2_t = result[p2] * 2;
            int p3_t = result[p3] * 3;
            int p5_t = result[p5] * 5;
            int min_v = Math.min(p2_t, Math.min(p3_t, p5_t));
            if (min_v == p2_t) {
                p2++;
            }
            if (min_v == p3_t) {
                p3++;
            }
            if (min_v == p5_t) {
                p5++;
            }

            result[num+1] = min_v;
            num++;
        }
        return result[n];
    }

    /**
     * offer 50
     * */
    public char firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }

    /**
     * offer 46
     * */
    public int translateNum(int num) {
        String numStr = String.valueOf(num);
        int[] result = new int[numStr.length() + 1];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2; i <= numStr.length(); i++) {
            char c = numStr.charAt(i-1);
            char preC = numStr.charAt(i-2);
            if (c >= '0' && c <= '5') {
                if (preC >= '1' && preC <= '2') {
                    result[i] = result[i-1] + result[i-2];
                } else {
                    result[i] = result[i-1];
                }
            } else {
                if (preC == '1') {
                    result[i] = result[i-1] + result[i-2];
                } else {
                    result[i] = result[i-1];
                }
            }
        }
        return result[numStr.length()];
    }

    /**
     * offer 43
     * 这题主要是找规律
     * */
    public int countDigitOne(int n) {
        int result = 0;
        int pow = 1;
        for (int i = 0; (int)Math.pow(10, pow -1) <= n; i++) {
            int a = (int)Math.pow(10, pow);
            int b = (int)Math.pow(10, pow - 1);

            int carry = n / a;
            int mod = n % a;


            int curNum = carry * b + Math.min(b, Math.max(mod - b + 1, 0));
            System.out.println(curNum);

            result += curNum;

            pow++;
        }
        return result;
    }

    /**
     * offer 44
     * TODO
     * */
    public int findNthDigit(int n) {
        return 0;
    }


    /**
     * offer 31
     * */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        LinkedList<Integer> stack = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }


    /**
     * offer 51
     * */
    public int reversePairs(int[] nums) {
        int re = reversePairsImpl(nums, 0, nums.length - 1);
        return re;
    }
    public int reversePairsImpl(int[] nums, int low, int high) {
        if (low >= high) {
            return 0;
        }
        int mid = (low + high) / 2;
        int l = reversePairsImpl(nums, low, mid);
        int r = reversePairsImpl(nums, mid + 1, high);
//        这个判断可以提前结束，个人觉得倒不是很重要，但是在LeetCode里面会导致超时，稍微注意一下
        if (nums[mid] < nums[mid+1]) {
            return l + r;
        }
        int merge = reversePairsMerge(nums, low, mid, high);
        return l + r + merge;
    }
    public int reversePairsMerge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[nums.length];
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }
        int i = left;
        int j = mid + 1;
        int index = left;
        int number = 0;
        while (i <= mid || j <= right) {
            // while (index <= right) {
            if (i > mid) {
                nums[index] = temp[j];
                index++;
                j++;
            } else if (j > right) {
                nums[index] = temp[i];
                index++;
                i++;
            } else if (temp[i] <= temp[j]) {
                nums[index] = temp[i];
                index++;
                i++;
            } else {
                nums[index] = temp[j];
                index++;
                j++;

                number += mid - i + 1;
            }
        }
        return number;
    }


    /**
     * offer 56-I
     * */
    public int[] singleNumbers(int[] nums) {
        int r = nums[0];
        for (int i = 1; i < nums.length; i++) {
            r ^= nums[i];
        }
        int t = 1;
        int index = 0;
        while ((t & r) == 0) {
            t = t << 1;
            index++;
        }

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & t) == 0) {
                l1.add(nums[i]);
            } else {
                l2.add(nums[i]);
            }
        }

        int t1 = 0;
        int t2 = 0;
        for (int i = 0;i < l1.size(); i++) {
            t1 ^= l1.get(i);
        }
        for (int i = 0;i < l2.size(); i++){
            t2 ^= l2.get(i);
        }
        int[] result = new int[2];
        result[0] = t1;
        result[1] = t2;
        return result;
    }

    /**
     * offer 56-II
     * 与56-I相比，这是两种思路
     * */
    public int singleNumber(int[] nums) {
        int[] r = new int[32];
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            for (int j = 0; j < 32; j++) {
                /**
                 * 注意：这里有个括号，否则会先算+
                 * */
                r[j] = r[j] + (temp & 1);
                temp = temp >> 1;
            }
        }

        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (r[i] % 3 != 0) {
                result += (int)Math.pow(2, i);
            }
        }
        return result;
    }

    /**
     * offer 57-I
     * 比较基础两数之和
     * */
    public int[] twoSum(int[] nums, int target) {
        int[] r = new int[2];
        r[0] = -1;
        r[1] = -1;
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            if ((nums[low] + nums[high]) == target) {
                r[0] = nums[low];
                r[1] = nums[high];
                return r;
            } else if ((nums[low] + nums[high]) > target) {
                high--;
            } else {
                low++;
            }
        }
        return r;
    }

    /**
     * offer 57-II
     * */
    public int[][] findContinuousSequence(int target) {
        int low = 1;
        int high = 2;
        int sum = 3;
        List<int[]> list = new ArrayList<>();
        while (high <= target && low < high) {
            /**
             * 注意：这里的low与high
             * low：sum = sum - low; low++;
             * high：high++; sum = sum + high;
             * 顺序是反的
             * */
            if (sum == target) {
                int[] cur = new int[high - low + 1];
                for (int i = low; i <= high; i++) {
                    cur[i-low] = i;
                }
                list.add(cur);
                sum = sum - low;
                low++;
            } else if (sum > target) {
                sum = sum - low;
                low++;
            } else {
                high++;
                sum = sum + high;
            }
        }
        return list.toArray(new int[list.size()][]);
    }


    /**
     * offer 58-I
     * */
    public static String reverseWords(String s) {
        int low = 0;
        int high = s.length() - 1;
        while (low <= high && s.charAt(low) == ' ') {
            low++;
        }
        while (low <= high && s.charAt(high) == ' ') {
            high--;
        }

        StringBuilder sb = new StringBuilder();
        low--;
        for (int i = low + 1; i <= high; i++) {
            if (s.charAt(i) == ' ') {
                String t = getReverseString(s, low + 1, i-1);
                if (!t.equals("")) {
                    sb.append(t);
                    sb.append(" ");
                }
                low = i;
            }
        }
        sb.append(getReverseString(s, low + 1, high));
        return getReverseString(sb.toString(), 0, sb.length() - 1);

    }

    public static String getReverseString(String s, int low, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = end; i >= low; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    /**
     * offer 58-II
     * */
    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder();
        n = n % s.length();
        for (int i = n; i < s.length(); i++) {
            sb.append(s.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    /**
     * offer 59
     * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.offer(i);
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(queue.peek());

        for (int i = k; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            if (!queue.isEmpty() && i - queue.peek() >= k) {
                queue.poll();
            }
            queue.offer(i);
            list.add(queue.peek());
        }

        int[] re = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            re[i] = nums[list.get(i)];
        }
        return re;
    }

    /**
     * offer 61
     * */
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int num1 = 0;
        int gap = 0;
        int index = 0;
        while (nums[index] == 0) {
            num1++;
            index++;
        }

        for (int i = index+1; i < nums.length; i++) {
            int t = nums[i] - nums[i-1];
            if (t <= 0) {
                return false;
            }
            gap += t - 1;
        }
        if (num1 >= gap) {
            return true;
        }
        return false;
    }

    /**
     * offer 62
     * */
    public int lastRemaining(int n, int m) {
        Set<Integer> set = new HashSet<>();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
        int k = 0;
        int index = 0;
        while (set.size() < n-1) {
            if (!set.contains(nums[index])) {
                k++;
                if (k == m) {
                    set.add(nums[index]);
                    k = 0;
                }
            }
            index++;
            index = index % n;
        }
        for (int i = 0; i < n; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * offer 63
     * */
    public int maxProfit(int[] prices) {
        int maxV = 0;
        if (prices.length <= 1) {
            return maxV;
        }
        int lowV = prices[0];
        for (int i = 1; i < prices.length; i++) {
            maxV = Math.max(maxV, prices[i] - lowV);
            lowV = Math.min(lowV, prices[i]);
        }
        return maxV;
    }

    /**
     * offer 64
     * 这种题意义性不是很大
     * */
    public int sumNums(int n) {
        int[] nums = new int[]{0};
        try {
            return nums[n];
        } catch (Exception e) {
            return n + sumNums(n-1);
        }
    }

    /**
     * offer 65
     * */
    public int add(int a, int b) {
        int t1 = a^b;
        int t2 = a&b;
        return t1 + (t2 << 1);
    }

    /**
     * offer 66
     * */
    public int[] constructArr(int[] a) {
        if (a.length == 0) {
            return new int[0];
        }

        int[] l2r = new int[a.length];
        int[] r2l = new int[a.length];

        int[] re = new int[a.length];

        l2r[0] = 1;
        r2l[a.length - 1] = 1;

        for (int i = 1; i < a.length; i++) {
            l2r[i] = l2r[i-1] * a[i-1];
        }
        for (int i = a.length - 2; i>=0; i--) {
            r2l[i] = r2l[i+1] * a[i+1];
        }

        re[0] = r2l[0];
        re[a.length - 1] = l2r[a.length - 1];
        for (int i = 1; i < a.length - 1; i++) {
            re[i] = l2r[i] * r2l[i];
        }
        return re;
    }


}

