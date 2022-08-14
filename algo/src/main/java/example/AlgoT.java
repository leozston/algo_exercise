package example;

import java.util.*;

/**
 * @description:
 * @author: longlonglv
 * @date: 2022/8/14
 */
public class AlgoT {
    public static void main(String[] args) {
        int[] nums = {10,1,2,7,6,1,5};
        int target = 8;
        combinationSum2(nums, 8);
        System.out.println("aa");
    }

    public static List<List<Integer>> list = null;
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        list = new ArrayList<>();
        Arrays.sort(candidates);
        boolean[] used = new boolean[candidates.length];
        List<Integer> cur = new ArrayList<>();
        combinationSum2Impl(candidates, target, 0, cur, used);
        return list;
    }


    public static void combinationSum2Impl(int[] candidates, int left, int index, List<Integer> cur, boolean[] used) {
        if (left == 0) {
            list.add(new ArrayList(cur));
            return;
        }
        if (index > candidates.length - 1) {
            return;
        }
        if (left < 0) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            combinationSum2Impl(candidates, left, index + 1, cur, used);
            if (i > 0 && candidates[i] == candidates[i-1] && !used[i - 1]) {
                continue;
            }
            cur.add(candidates[i]);
            used[i] = true;
            combinationSum2Impl(candidates, left - candidates[i], index+1, cur, used);
            used[i] = false;
            cur.remove(cur.size() - 1);
        }
    }
}
