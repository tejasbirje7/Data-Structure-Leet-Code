import java.util.*;

/**
 * How this avoids duplicates without sorting:
 * Sorting groups duplicates together so you can skip based on neighbors.
 *
 * This method instead detects duplicates dynamically at each recursion depth using the seen set.
 *
 * This ensures that, for example, when building permutations starting with 1, you don’t pick another identical 1 at the same starting position.
 */
class UniquePermutations {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(res, new ArrayList<>(), nums, used);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> temp, int[] nums, boolean[] used) {

        // Base case: permutation complete
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        // Keep track of elements used at this recursion level
        Set<Integer> seen = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            // Skip if already used in the permutation
            if (used[i]) continue;

            // Skip if already tried at this depth
            if (seen.contains(nums[i])) continue;
            seen.add(nums[i]);

            // Choose
            used[i] = true;
            temp.add(nums[i]);

            // Explore
            backtrack(res, temp, nums, used);

            // Un-choose
            used[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

    // Main for testing
    public static void main(String[] args) {
        UniquePermutations sol = new UniquePermutations();

        int[] nums1 = {1, 1, 2};
        System.out.println(sol.permuteUnique(nums1)); // [[1, 1, 2], [1, 2, 1], [2, 1, 1]]

        int[] nums2 = {1, 2, 3};
        System.out.println(sol.permuteUnique(nums2)); // [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    }
}
