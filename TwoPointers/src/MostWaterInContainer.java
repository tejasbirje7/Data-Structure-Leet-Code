public class MostWaterInContainer {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int maxContainer = 0;

        while (l < r) {
            int container = (r - l) * Math.min(height[l],height[r]);
            if ( container > maxContainer) {
                maxContainer = container;
            }
            if(height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxContainer;
    }
}
