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
}
