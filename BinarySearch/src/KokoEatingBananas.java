/**
 * LeetCode 875. Koko Eating Bananas
 * https://leetcode.com/problems/koko-eating-bananas/
 *
 * PROBLEM
 *   - Input: piles of bananas and h hours. Each hour Koko picks one pile and eats k bananas
 *     from it; if the pile has fewer than k, she finishes it and waits out the hour.
 *   - Return: the smallest whole speed k that lets her finish every pile within h hours.
 *   - Constraints: piles.length <= h <= 10^9, piles[i] up to 10^9 (so a speed always exists,
 *     and sums need a long).
 *
 * EXAMPLE
 *   Input: piles = [3,6,7,11], h = 8
 *   Output: 4
 *
 * HOW TO THINK
 *   - Clue: "smallest k that still works" + "faster never hurts" = binary search on the answer.
 *   - Technique: hours(k) = sum of ceil(pile / k) only goes down as k goes up. So speeds split
 *     into "too slow" then "fast enough", and we want the first "fast enough".
 *     k ranges from 1 to max(piles): eating faster than the biggest pile never saves an hour.
 *   - Rule that stays true: the answer is inside [lo, hi]; hi is always fast enough.
 *   - Decision: hours(mid) <= h -> mid works, but maybe something slower does too: hi = mid.
 *               hours(mid) >  h -> mid is too slow, and so is everything below it: lo = mid + 1.
 *   - Return: lo once lo == hi.
 *   - ceil(p / k) without floating point: (p + k - 1) / k.
 *
 * STEPS
 *   1. lo = 1, hi = max(piles).
 *   2. While lo < hi: mid = lo + (hi - lo) / 2.
 *   3. hours(mid) <= h -> hi = mid, else lo = mid + 1.
 *   4. Return lo.
 *
 * DRY RUN   piles = [3,6,7,11], h = 8   (lo and hi are values before the step's action)
 *
 *    lo | hi | mid | hours per pile | hours(mid) | fits h = 8? | action
 *   ----+----+-----+----------------+------------+-------------+--------
 *     1 | 11 |  6  | 1 + 1 + 2 + 2  |     6      | yes         | hi = 6
 *     1 |  6 |  3  | 1 + 2 + 3 + 4  |    10      | no          | lo = 4
 *     4 |  6 |  5  | 1 + 2 + 2 + 3  |     8      | yes         | hi = 5
 *     4 |  5 |  4  | 1 + 2 + 2 + 3  |     8      | yes         | hi = 4
 *     4 |  4 |  -  |       -        |     -      | lo == hi    | stop
 *
 *   Returns 4.
 *
 * EDGE CASES
 *   [30,11,23,4,20], h = 5      -> 30          h == number of piles: must clear the biggest pile in an hour
 *   [30,11,23,4,20], h = 6      -> 23          official example 3
 *   [5], h = 10                 -> 1           plenty of time: slowest speed
 *   [1000000000], h = 2         -> 500000000   huge pile: split evenly over 2 hours
 *   [1,1,1,1], h = 4            -> 1           every pile takes one hour at speed 1
 *
 * COMMON MISTAKES
 *   - Starting lo at 0: hours(0) divides by zero. The slowest real speed is 1.
 *   - Summing hours in an int: 10^4 piles of 10^9 at speed 1 overflows; use a long.
 *   - Using p / k (rounded down): a leftover part of a pile still costs a whole hour.
 *   - hi = mid - 1 when mid fits: mid may be the answer itself.
 *
 * COMPLEXITY
 *   O(n log M) time, M = max pile (each guess costs one pass over the piles), O(1) extra space.
 *
 * RELATED
 *   LeetCode 69 (SqrRtx, Math/): also binary search on the answer, with mid * mid <= x as the test.
 *   LeetCode 34 (FirstAndLastPosition, BinarySearch/): finding the first index that passes a
 *     test is the same boundary search.
 *   LeetCode 1011 (Capacity To Ship Packages Within D Days, not in repo yet): same idea, the
 *     test is "days needed with capacity mid <= D".
 */
public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int lo = 1;
        int hi = 0;
        for (int p : piles) {
            hi = Math.max(hi, p);
        }
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (hoursNeeded(piles, mid) <= h) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private long hoursNeeded(int[] piles, int k) {
        long hours = 0;
        for (int p : piles) {
            hours += (p + k - 1L) / k;
        }
        return hours;
    }

    public static void main(String[] args) {
        KokoEatingBananas sol = new KokoEatingBananas();

        System.out.println(sol.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));         // Expected: 4
        System.out.println(sol.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));   // Expected: 30
        System.out.println(sol.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));   // Expected: 23
        System.out.println(sol.minEatingSpeed(new int[]{5}, 10));                  // Expected: 1
        System.out.println(sol.minEatingSpeed(new int[]{1000000000}, 2));          // Expected: 500000000
        System.out.println(sol.minEatingSpeed(new int[]{1, 1, 1, 1}, 4));          // Expected: 1
    }
}
