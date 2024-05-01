public class RainWater {

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }

    public static int trap(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int maxLeft = height[l];
        int maxRight = height[r];
        int water = 0;

        while (l < r) {
            if(maxLeft < maxRight) {
                l += 1;
                if(height[l] > maxLeft) {
                    maxLeft = height[l];
                }
                if((maxLeft - height[l]) > 0) {
                    water += maxLeft - height[l];
                }
            } else {
                r -= 1;
                if (height[r] > maxRight) {
                    maxRight = height[r];
                }
                if((maxRight - height[r]) > 0) {
                    water += maxRight - height[r];
                }
            }
        }
        return water;
    }
}
