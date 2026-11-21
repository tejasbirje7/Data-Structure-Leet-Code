/**
 * LeetCode 204. Count Primes
 * https://leetcode.com/problems/count-primes/
 *
 * PROBLEM
 *   - Input: an int n (0 <= n <= 5 * 10^6).
 *   - Return: how many prime numbers are strictly less than n.
 *
 * EXAMPLE
 *   Input: n = 10
 *   Output: 4
 *   Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 *
 * HOW TO THINK
 *   - Clue: count ALL primes below n (not test one number) -> testing each number by division is O(n * sqrt(n)), too slow.
 *   - Technique: Sieve of Eratosthenes. Every multiple of a prime is not prime, so cross them out.
 *   - Rule that stays true: when the loop reaches i, composite[i] is final:
 *     if no smaller prime crossed it out, i is prime.
 *   - Decision for each i from 2 while i * i < n: composite -> skip;
 *     prime -> cross out i*i, i*i + i, i*i + 2i, ... below n.
 *     Start at i * i: smaller multiples (2i, 3i, ...) were already crossed out by smaller primes.
 *     Stop at i * i >= n: any composite below n has a factor <= sqrt(n), so it is already crossed out.
 *   - Return: the count of numbers in [2, n - 1] that are not crossed out.
 *
 * STEPS
 *   1. If n < 3, return 0 (no primes below 2).
 *   2. composite = boolean[n].
 *   3. For i = 2 while i * i < n: if !composite[i], mark j = i*i, i*i + i, ... < n as composite.
 *   4. Count i in [2, n - 1] with !composite[i].
 *   5. Return the count.
 *
 * DRY RUN   input = n = 30
 *
 *    i | i * i < 30? | composite[i]? | action | numbers crossed out (new ones only)
 *   ---+-------------+---------------+--------+-------------------------------------------
 *    2 |   4 yes     |      no       | mark   | 4 6 8 10 12 14 16 18 20 22 24 26 28
 *    3 |   9 yes     |      no       | mark   | 9 15 21 27   (12, 18, 24 were already crossed)
 *    4 |  16 yes     |      yes      | skip   |
 *    5 |  25 yes     |      no       | mark   | 25
 *    6 |  36 no      |               | stop   |
 *
 *   Not crossed out in [2, 29]: 2 3 5 7 11 13 17 19 23 29. Returns 10.
 *
 * EDGE CASES
 *   0      -> 0    no numbers below 0
 *   1      -> 0
 *   2      -> 0    "strictly less than": 2 itself is not counted
 *   3      -> 1    only 2
 *   10     -> 4    the example
 *   5000000 -> 348513   largest input; the sieve stays fast
 *
 * COMMON MISTAKES
 *   - Counting n itself: the question is primes strictly LESS than n, so the array has size n, not n + 1.
 *   - Counting 0 and 1 as primes: start counting at 2.
 *   - Testing every number with trial division: O(n * sqrt(n)) times out for n = 5 * 10^6.
 *   - Crossing out from 2 * i instead of i * i: still correct, just slower.
 *
 * COMPLEXITY
 *   O(n log log n) time, O(n) extra space for the boolean array.
 *
 * RELATED
 *   LeetCode 69 (SqrRtx, Math/): the same "stop at sqrt(n)" bound, written as i * i < n.
 *   LeetCode 172 (FactorialTrailingZero, Math/): another counting problem solved with multiples instead of brute force.
 */
public class CountPrimes {

    public static void main(String[] args) {
        System.out.println(countPrimes(30));
    }

    public static int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        boolean[] composite = new boolean[n];
        for (int i = 2; i * i < n; i++) {
            if (!composite[i]) {
                for (int j = i * i; j < n; j += i) {
                    composite[j] = true;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!composite[i]) {
                count++;
            }
        }
        return count;
    }

}
