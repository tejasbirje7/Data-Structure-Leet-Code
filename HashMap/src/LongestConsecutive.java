import java.util.*;

public class LongestConsecutive {

    public static void main(String[] args) {
        int[] nums = {100,4,200,5,1,3,2};
        System.out.println(longestConsecutive(nums));
    }

    /**
     * Input: nums = [100,4,200,1,3,2]
     * Output: 4
     * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
     * Therefore its length is 4.
     *
     * Input: nums = [0,3,7,2,5,8,4,6,0,1]
     * Output: 9
     */
    public static int longestConsecutive(int[] num) {
        int res =0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : num) {
            if (!map.containsKey(n)) {
                int left = map.getOrDefault(n - 1, 0);
                int right = map.getOrDefault(n + 1, 0);

                // sum: length of the sequence n is in
                // Checking what's the length of boundary to left and to right + self n i.e. left + right + 1
                int sum = left + right + 1;
                map.put(n, sum);

                // keep track of the max length
                res = Math.max(res, sum);

                // extend the length to the boundary(s)
                // of the sequence
                // will do nothing if n has no neighbors
                map.put(n - left, sum);
                map.put(n + right, sum);
                System.out.println(map);
                System.out.println(" ");
            }
        }
        return res;
    }
}
