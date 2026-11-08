import java.util.Arrays;

/**
 * LeetCode 274. H-Index
 * https://leetcode.com/problems/h-index/
 *
 * PROBLEM
 *   - Input: citations[i] = how many times paper i was cited.
 *   - Return: the h-index = the largest h such that at least h papers have at least h citations each.
 *   - h can never be more than n, the number of papers.
 *
 * EXAMPLE
 *   Input: citations = [3,0,6,1,5]
 *   Output: 3
 *   Explanation: [3,0,6,1,5] means the researcher has 5 papers in total
 *   and each of them had received 3, 0, 6, 1, 5 citations respectively.
 *   Since the researcher has 3 papers with at least 3 citations each and
 *   the remaining two with no more than 3 citations each, their h-index is 3.
 *
 * HOW TO THINK
 *   - Clue: the answer is between 0 and n, and we only need "how many papers have at least h citations".
 *     So count instead of sorting.
 *   - Technique: bucket counting. trackArr[c] = number of papers with exactly c citations.
 *     A paper with more than n citations goes into trackArr[n] (counting it as n is enough, h <= n).
 *   - Careful: index = a citation count, value = how many papers have it (not paper numbers).
 *     Two papers with the same citations share one bucket.
 *   - Rule that stays true: walking i from n down, count = number of papers with at least i citations.
 *   - Decision: count >= i -> at least i papers have at least i citations: return i
 *               (the first hit from the top is the largest h).
 *               otherwise -> move down to i - 1.
 *   - Return: i. At i = 0, count >= 0 is always true, so the final "return 0" is never reached.
 *
 * STEPS
 *   1. n = citations.length; trackArr = new int[n + 1].
 *   2. For each c: trackArr[c]++ if c <= n, else trackArr[n]++.
 *   3. count = 0; loop i from n down to 0: count += trackArr[i].
 *   4. If count >= i, return i.
 *   5. Return 0 (fallback only).
 *
 * DRY RUN   citations = [3,0,6,1,5]   (n = 5; count is the value after adding trackArr[i])
 *   Buckets: 3 -> [3], 0 -> [0], 6 > 5 -> [5], 1 -> [1], 5 -> [5]
 *   trackArr = [1,1,0,1,0,2]   (the code prints this line)
 *
 *    i | trackArr[i] | count | count >= i ? | action
 *   ---+-------------+-------+--------------+-----------
 *    5 |      2      |   2   | 2 >= 5  no   | go down
 *    4 |      0      |   2   | 2 >= 4  no   | go down
 *    3 |      1      |   3   | 3 >= 3  yes  | return 3
 *
 *   Returns 3: three papers (3, 6 and 5 citations) have at least 3 citations each.
 *
 * EDGE CASES
 *   [1,3,1]     -> 1   only one paper has 2 or more citations
 *   [0]         -> 0   found at i = 0
 *   [100]       -> 1   100 > n, so it goes into bucket n = 1
 *   [0,0,0]     -> 0   no paper is cited
 *   [5,5,5,5]   -> 4   capped by n = 4 papers
 *   []          -> 0   trackArr = [0], returns at i = 0; LeetCode guarantees n >= 1
 *
 * COMMON MISTAKES
 *   - Using trackArr[c] without the cap: c > n is out of bounds. Put it in trackArr[n].
 *   - Looping i upward and returning the first count >= i: that finds the smallest h, not the largest.
 *     Go from n down.
 *   - Returning count instead of i: count can be bigger than i. [1,1,1] has count 3 at i = 1,
 *     but the answer is 1.
 *
 * COMPLEXITY
 *   O(n) time, O(n) extra space. (Sorting first and scanning is O(n log n).)
 *
 * RELATED
 *   LeetCode 451 (SortCharactersByFrequency, FAQ/): bucket by count again, then walk the buckets
 *     from the highest count down.
 *   LeetCode 268 (MissingNumberFinder, FAQ/): also uses the fact that the useful values lie in 0..n.
 */
public class HIndex {

    // #TODO Learn more about H-index
    public static void main(String[] args) {
        int[] citations = {3,0,6,1,5};
        System.out.println(hIndex(citations));
    }

    public static int hIndex(int[] citations) {
        int n = citations.length;
        int[] trackArr = new int[n + 1];

        for (int c : citations) {
            if (c > n)
                trackArr[n]++;
            else
                trackArr[c]++;
        }

        System.out.println(Arrays.toString(trackArr));

        int count = 0;
        for (int i = n; i >= 0; i--) {
            count = count + trackArr[i];
            if (count >= i)
                return i;
        }
        return 0;
    }
}
