public class MissingNumberFinder {

    /**
     * How it works:
     * The sum of integers from 0 to n is:
     * <p>
     * 0 + 1 + 2 + ... + n
     * The given array contains all numbers except one missing number.
     * <p>
     * Instead of computing both sums separately (sum(0..n) and sum(nums)),
     * this approach uses a single variable result to:
     * <p>
     * Start at n (since that number is missing from the index sequence).
     * <p>
     * Add (i - nums[i]) for each index to adjust toward the missing value.
     * <p>
     * The final result is the missing number.
     * */
    public int missingNumber(int[] nums) {
        // Start with nums.length because in the complete range [0..n],
        // the missing number could be 'n' itself.
        int result = nums.length;

        // Loop through the array
        for (int i = 0; i < nums.length; i++) {
            // For each index i, add the difference between the expected index 'i'
            // and the actual number nums[i].
            // This effectively accumulates the difference between sum(0..n)
            // and sum(nums), without explicitly computing the sums.
            result = result + (i - nums[i]);
        }

        // The result now holds the missing number
        return result;
    }

    public static void main(String[] args) {
        MissingNumberFinder finder = new MissingNumberFinder();

        // Example 1
        int[] nums1 = {3, 0, 1};
        System.out.println("Missing number in [3, 0, 1] is: " + finder.missingNumber(nums1));
        // Expected: 2

        // Example 2
        int[] nums2 = {0, 1};
        System.out.println("Missing number in [0, 1] is: " + finder.missingNumber(nums2));
        // Expected: 2

        // Example 3
        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println("Missing number in [9, 6, 4, 2, 3, 5, 7, 0, 1] is: " + finder.missingNumber(nums3));
        // Expected: 8
    }
}
