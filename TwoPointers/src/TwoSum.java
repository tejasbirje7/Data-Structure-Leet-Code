import java.util.Arrays;

public class TwoSum {

    public static void main(String[] args) {
        int[] numbers = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }

    public static int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;

        while (numbers[l] + numbers[r] != target) {
            if(numbers[l] + numbers[r] < target) l++;
            else r--;
        }

        return new int[] {l+1,r+1};
    }
}
