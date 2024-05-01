import java.util.Arrays;

/**
 * Moore's Voting Algorithm
 *
 * ALGORITHM:
 * Begin by initializing two variables, num and a counter count, both of which are set to 0.
 * Now we’ll begin iterating over the number and for each element x.
 * We first check to see if the count is 0, and then we assign num to x.
 * Then we check to see if the current num is equal to x, if not, we decrease the count by one, else we increment it by one.
 * Reset the counter to zero.
 * Find the frequency of the num variable by looping through the nums array.
 * Now, if the count is larger than N/2, we return num; otherwise we return -1.
 *
 *
 * ILLUSTRATION:
 * Input: { 1,7,2,7,8,7,7}
 *
 * Index 0: num = 1, count =1
 * Index 1: num = 1, count =0 ( 7 not equal to 1)
 * Index 2: num = 2 (as the count is 0 we initalise num to current), count =1
 * Index 3: num = 2, count =0 ( 7 not equal to 2)
 * Index 4: num = 8 (as the count is 0 we initalise num to current), count =1
 * Index 5: num = 8, count =0 ( 7 not equal to 8)
 * Index 6: num = 7 (as the count is 0 we initalise num to current), count =1
 *
 * Now we can check for the frequency of 7, i.e, 4 which is > 7/2.
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }

    /**
     * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
     * Input: nums = [3,2,3]
     * Output: 3
     */
    public static int majorityElement(int[] nums) {
        int count = 0;
        int el = 0;

        for (int num : nums) {
            if (count == 0) {
                el = num;
                count++;
            } else if (num != el) {
                count--;
            } else {
                count++;
            }
        }

        int repeatingElement = 0;
        for (int num : nums) {
            if(el == num) {
                repeatingElement++;
            }
        }

        if(repeatingElement > (nums.length / 2)) {
            return el;
        }
        return -1;
    }
}
