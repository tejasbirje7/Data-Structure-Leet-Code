import java.util.*;

/**
 * permute() → Initializes the result list and calls backTrack.
 *
 * backTrack():
 *
 * If the current list addHere has the same size as nums, it’s a complete permutation → add it to results.
 *
 * Otherwise, try adding each unused number, recurse, then remove it (backtrack).
 *
 * addHere.contains(nums[i]) ensures we don’t reuse numbers in the same permutation.
 *
 * Uses DFS-style backtracking to explore all possibilities.
 */
public class PossiblePermutations {

    // Main function to return all permutations of the given array
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>(); // Stores all permutations
        backTrack(res, new ArrayList<>(), nums);     // Start backtracking
        return res;
    }

    /**
     * Backtracking function to generate permutations
     * @param res      - Final result list of all permutations
     * @param addHere  - Current partial permutation being built
     * @param nums     - Original input array
     */
    public void backTrack(List<List<Integer>> res, List<Integer> addHere, int[] nums) {
        // Base case: If current permutation is complete, add it to results
        if (addHere.size() == nums.length) {
            res.add(new ArrayList<>(addHere)); // Make a copy before adding
            return;
        }

        // Try each number that is not already in the current permutation
        for (int i = 0; i < nums.length; i++) {
            if (addHere.contains(nums[i])) // Skip if number already used
                continue;

            // Choose
            addHere.add(nums[i]);

            // Explore
            backTrack(res, addHere, nums);

            // Un-choose (backtrack)
            addHere.remove(addHere.size() - 1);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        PossiblePermutations sol = new PossiblePermutations();

        int[] nums1 = {1, 2, 3};
        System.out.println("Permutations of [1, 2, 3]: " + sol.permute(nums1));

        int[] nums2 = {0, 1};
        System.out.println("Permutations of [0, 1]: " + sol.permute(nums2));

        int[] nums3 = {1};
        System.out.println("Permutations of [1]: " + sol.permute(nums3));

        /**
         * Permutations of [1, 2, 3]: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
         * Permutations of [0, 1]: [[0, 1], [1, 0]]
         * Permutations of [1]: [[1]]
         */
    }
}
