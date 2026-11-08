/**
 * LeetCode 11. Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 *
 * PROBLEM
 *   - Input: height[i] = the height of a vertical line at x = i.
 *   - Pick two lines; with the x-axis they form a container.
 *   - Return: the most water one container can hold.
 *   - Water = width * shorter line = (r - l) * min(height[l], height[r]).
 *
 * EXAMPLE
 *   Input: height = [1,8,6,2,5,4,8,3,7]
 *   Output: 49
 *   Explanation: The above vertical lines are represented by
 *   array [1,8,6,2,5,4,8,3,7]. In this case,
 *   the max area of water (blue section) the container can contain is 49.
 *
 * HOW TO THINK
 *   - Clue: "two lines" + area = width * the shorter height. The widest pair is the first and the last line.
 *   - Technique: two pointers from both ends (l = 0, r = n - 1), moving inwards.
 *   - Why it works: the shorter line limits the area. Pairing it with any line closer in
 *     gives a smaller width and a height that is still at most the shorter line,
 *     so that area can't be bigger. That line is done: drop it.
 *   - Rule that stays true: every pair outside [l, r] has been checked or can't beat the best so far.
 *   - Decision: area = (r - l) * min(height[l], height[r]); keep the max.
 *               height[l] < height[r] -> l++ (left is shorter).
 *               otherwise             -> r-- (right is shorter, or both equal).
 *   - Width is r - l (the distance between the lines), not r - l + 1.
 *   - Return: maxContainer, the best area seen over all steps.
 *
 * STEPS
 *   1. l = 0, r = n - 1, maxContainer = 0.
 *   2. Loop while l < r.
 *   3. container = (r - l) * min(height[l], height[r]); maxContainer = max(container, maxContainer).
 *   4. If height[l] < height[r], l++; else r--.
 *   5. Return maxContainer.
 *
 * DRY RUN   height = [1,8,6,2,5,4,8,3,7]   (l, r are values before the move)
 *
 *    l | r | h[l] | h[r] | width | area | maxContainer (after) | move
 *   ---+---+------+------+-------+------+----------------------+------
 *    0 | 8 |  1   |  7   |   8   |   8  |          8           | l++
 *    1 | 8 |  8   |  7   |   7   |  49  |         49           | r--
 *    1 | 7 |  8   |  3   |   6   |  18  |         49           | r--
 *    1 | 6 |  8   |  8   |   5   |  40  |         49           | r-- (equal)
 *    1 | 5 |  8   |  4   |   4   |  16  |         49           | r--
 *    1 | 4 |  8   |  5   |   3   |  15  |         49           | r--
 *    1 | 3 |  8   |  2   |   2   |   4  |         49           | r--
 *    1 | 2 |  8   |  6   |   1   |   6  |         49           | r--
 *
 *   Loop ends at l = r = 1. Returns 49 (lines at 1 and 8, row 2).
 *
 * EDGE CASES
 *   [1,8,6,2,5,4,8,3,7]         -> 49  the dry run above
 *   [1,1]                       -> 1   smallest valid input: one pair, width 1
 *   [0,0]                       -> 0   every area is 0
 *   [4,4,4,4]                   -> 12  all equal: the widest pair wins, 3 * 4
 *   [5]                         -> 0   loop never runs (LeetCode guarantees n >= 2)
 *   []                          -> 0   r = -1, loop never runs
 *
 * COMMON MISTAKES
 *   - Updating the wrong variable in max(...): "container = max(container, best)" never
 *     changes best, so it stays 0. Assign the result to the best: maxContainer = max(...).
 *   - Moving the taller line: the area can only shrink. Always move the shorter one.
 *   - Width r - l + 1: that counts lines; the width is the distance r - l.
 *   - Checking every pair: O(n^2), too slow for 10^5 lines.
 *
 * COMPLEXITY
 *   O(n) time (each step moves one pointer inwards), O(1) extra space.
 *
 * RELATED
 *   LeetCode 42 (RainWater, ArraysAndStrings/): also two pointers from both ends that move the lower side,
 *     but adds up the water above every bar instead of finding one container.
 *   LeetCode 167 (TwoSum, TwoPointers/): same "start at both ends, move one inwards" pattern on a sorted array.
 */
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
            maxContainer = Math.max(container,maxContainer);
            if(height[l] < height[r]) l++;
             else r--;
        }
        return maxContainer;
    }
}
