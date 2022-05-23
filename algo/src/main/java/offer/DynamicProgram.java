package offer;

/**
 * @description:
 * @author: longlonglv
 * @date: 2022/5/22
 */
public class DynamicProgram {
    public static void main(String[] args) {
    }

    /**
     * offer 10-I
     * */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int a = 0;
        int b = 1;
        int c = a + b;
        for (int i = 2; i <= n; i++) {
            c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * offer 10-II
     * */
    public int numWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int a = 1;
        int b = 2;
        int c = a + b;
        for (int i = 3; i <= n; i++) {
            c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        return c;
    }


    /**
     * offer 14-I
     * */
    public int cuttingRope(int n) {
        int[] nums = new int[n+1];
        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i <=n; i++) {
            int t = -1;
            for (int j = 1; j < i; j++) {
                t = Math.max(t, Math.max(nums[j], j) * (i - j));
            }
            nums[i] = t;
        }
        return nums[n];
    }

    /**
     * offer 14-II
     * TODO
     * */
    public int cuttingRopeV2(int n) {
        return 0;
    }


    /**
     * offer 19
     * */
    public static boolean isMatch(String s, String p) {
        boolean[][] re = new boolean[s.length() + 1][p.length() + 1];
        re[0][0] = true;

        /**
         * 注意：这里初值很关键，否则使用默认的初值全为false，这个初值是不正确的
         * */
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i-1) != '*') {
                re[0][i] = false;
            } else {
                re[0][i] = re[0][i-2];
            }
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1)) {
                    re[i][j] = re[i-1][j-1];
                } else {
                    if (p.charAt(j - 1) == '.') {
                        re[i][j] = re[i-1][j-1];
                    } else if (p.charAt(j-1) == '*') {
                        re[i][j] = re[i][j-2] || re[i][j-1] || ((s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') && re[i-1][j]);
                    }
                }
            }
        }
        return re[s.length()][p.length()];
    }
}
