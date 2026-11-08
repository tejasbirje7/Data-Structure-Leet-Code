import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 228. Summary Ranges
 * https://leetcode.com/problems/summary-ranges/
 *
 * PROBLEM
 *   - Input: a sorted array of unique integers nums.
 *   - Return: the shortest list of ranges that covers every number exactly once.
 *   - A range [a,b] is written "a->b", or just "a" when a == b.
 *
 * EXAMPLE
 *   Input: nums = [0,1,2,4,5,7]
 *   Output: ["0->2","4->5","7"]
 *   Explanation: The ranges are:
 *   [0,2] --> "0->2"
 *   [4,5] --> "4->5"
 *   [7,7] --> "7"
 *
 * HOW TO THINK
 *   - Clue: "sorted" + "unique" means consecutive numbers sit next to each other,
 *     so each range is one block of neighbours that differ by 1.
 *   - Technique: one pass. Remember where the block starts (a), then move i forward
 *     while the next number is exactly one more.
 *   - Rule that stays true: at the top of the for loop, i is the first index of a new block,
 *     and every earlier number is already in res.
 *   - Decision: after the while loop, a != nums[i] -> the block has 2+ numbers: add "a->nums[i]".
 *               a == nums[i] -> a single number: add "a".
 *   - Return: res. The for loop's i++ then jumps to the start of the next block.
 *   - The nums.length == 1 check is only a shortcut; the main loop would give the same answer.
 *
 * STEPS
 *   1. If nums has one number, return just that number.
 *   2. For i from 0: a = nums[i].
 *   3. While i + 1 is in range and nums[i+1] - nums[i] == 1, i++.
 *   4. Add "a->nums[i]" if a != nums[i], else "a". The for loop moves i to the next block.
 *   5. Return res.
 *
 * DRY RUN   nums = [0,1,2,4,5,7]   (i after the while loop is where the block ends)
 *
 *    i at start | a | while loop moves i     | i after | a != nums[i]? | added  | res
 *   ------------+---+------------------------+---------+---------------+--------+------------------
 *        0      | 0 | 1-0=1, 2-1=1, 4-2 stop |    2    | yes           | "0->2" | [0->2]
 *        3      | 4 | 5-4=1, 7-5 stop        |    4    | yes           | "4->5" | [0->2, 4->5]
 *        5      | 7 | last index, stop       |    5    | no            | "7"    | [0->2, 4->5, 7]
 *
 *   Returns ["0->2","4->5","7"].
 *
 * EDGE CASES
 *   []                         -> []                       the loop never runs
 *   [5]                        -> ["5"]                    the length-1 shortcut
 *   [0,2,3,4,6,8,9]            -> ["0","2->4","6","8->9"]  single numbers and blocks mixed
 *   [1,3,5]                    -> ["1","3","5"]            no two numbers are consecutive
 *   [-1,0,1]                   -> ["-1->1"]                negatives work the same way
 *   [MIN_VALUE, MAX_VALUE]     -> ["-2147483648","2147483647"]   the difference overflows to -1,
 *                                                          never 1, so the answer is still right
 *
 * COMMON MISTAKES
 *   - Reading nums[i+1] without checking i + 1 < nums.length: out of bounds on the last number.
 *   - Moving i to the next block start by hand and then letting the for loop add 1 again: a number is skipped.
 *   - Writing "7->7" for a single number: check a != nums[i] first.
 *
 * COMPLEXITY
 *   O(n) time (each index is visited once, by the for loop or the while loop),
 *   O(1) extra space apart from the output.
 *
 * RELATED
 *   LeetCode 128 (LongestConsecutive, HashMap/): unsorted input, so a hash map finds the consecutive runs.
 *   LeetCode 56 (MergeIntervals, Intervals/): joins overlapping ranges instead of consecutive numbers.
 */
public class SummaryRanges {
    public static void main(String[] args) {
        int[] nums = {0,1,2,4,5,7};
        System.out.println(summaryRanges(nums));
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if(nums.length==1) {
            res.add(nums[0]+"");
            return res;
        }
        for (int i=0; i<nums.length; i++) {
            int a = nums[i];
            while (i+1 < nums.length && (nums[i+1] - nums[i] == 1)) {
                i++;
            }
            if(a != nums[i]) {
                res.add(a+"->"+nums[i]);
            } else {
                res.add(String.valueOf(a));
            }
        }
        return res;
    }

}
