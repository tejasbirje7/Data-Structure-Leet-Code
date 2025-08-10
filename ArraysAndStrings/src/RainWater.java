public class RainWater {

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }

    /**
     * Given n non-negative integers representing an elevation map
     * where the width of each bar is 1, compute how much water
     * it can trap after raining.
     *
     *
     * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * Output: 6
     * Explanation: The above elevation map (black section) is
     * represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this
     * case, 6 units of rain water (blue section) are being trapped.
     *
     * Input: height = [4,2,0,3,2,5]
     * Output: 9
     */

    public static int trap(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int leftCounter = height[l];
        int rightCounter = height[r];
        int water = 0;

        while (l < r) {

            // Move pointer which is less than the other
            if(leftCounter < rightCounter) {
                // Increment left pointer since it's minimum
                l += 1;

                // Update left counter, if there's a block i.e.
                // if height is more than left counter
                if(height[l] > leftCounter) {
                    leftCounter = height[l];
                }

                // Update water, if left counter value is more than block height
                water += Math.max(leftCounter - height[l], 0);
            } else {
                // Decrement right pointer since it's minimum or equal
                r -= 1;

                // Update right counter if there's a block i.e.
                // if height is more than right counter
                if (height[r] > rightCounter) {
                    rightCounter = height[r];
                }

                // Update water, if right counter value is more than block height
                water += Math.max(rightCounter - height[r],0);
            }


        }
        return water;
    }

    int[] lc = { 0,1 };
    int[] rc = { 1,2 };
}
