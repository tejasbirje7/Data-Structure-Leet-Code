public class RainWater {

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }

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
                if((leftCounter - height[l]) > 0) {
                    water += leftCounter - height[l];
                }
            } else {
                // Decrement right pointer since it's minimum or equal
                r -= 1;

                // Update right counter if there's a block i.e.
                // if height is more than right counter
                if (height[r] > rightCounter) {
                    rightCounter = height[r];
                }

                // Update water, if right counter value is more than block height
                if((rightCounter - height[r]) > 0) {
                    water += rightCounter - height[r];
                }
            }


        }
        return water;
    }
}
