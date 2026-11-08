/**
 * LeetCode 202. Happy Number
 * https://leetcode.com/problems/happy-number/
 *
 * PROBLEM
 *   - Input: a positive integer n.
 *   - One step: replace the number by the sum of the squares of its digits.
 *   - Repeat. Either the number reaches 1 (and stays 1), or it loops forever in a cycle without 1.
 *   - Return: true if it reaches 1 (n is happy), false otherwise.
 *
 * EXAMPLE
 *   Input: n = 19
 *   Output: true
 *   Explanation:
 *   1^2 + 9^2 = 82
 *   8^2 + 2^2 = 68
 *   6^2 + 8^2 = 100
 *   1^2 + 0^2 + 0^2 = 1
 *
 * HOW TO THINK
 *   - Clue: "loops endlessly in a cycle" = the numbers form a chain (like a linked list)
 *     that either ends at 1 or goes round a cycle.
 *   - Technique: fast and slow pointers (Floyd's cycle detection) on that chain.
 *     slow takes one step at a time, fast takes two. No set of seen numbers needed.
 *   - Why it works: 1 maps to itself, so it is a cycle of length 1.
 *     Every chain ends in some cycle; fast either lands on 1 or catches up with slow inside the other cycle.
 *   - Rule that stays true: after k rounds, slow is k steps along the chain and fast is 2k steps along.
 *   - Decision: fast == 1 -> happy, return true.
 *               slow == fast (and not 1) -> they met inside a cycle without 1, stop.
 *               otherwise -> next round.
 *   - Return: false once the pointers meet.
 *   - Use do-while: slow and fast both start at n, so a plain while (slow != fast) would never run.
 *
 * STEPS
 *   1. slow = n, fast = n.
 *   2. Do: slow = digitSquareSum(slow); fast = digitSquareSum(digitSquareSum(fast)).
 *   3. If fast == 1, return true.
 *   4. Repeat while slow != fast.
 *   5. Return false.
 *   digitSquareSum: take the last digit (n % 10), add its square, drop it (n / 10), until n == 0.
 *
 * DRY RUN   n = 2   (slow and fast are values after the round)
 *
 *    round | slow | fast: 1st hop -> 2nd hop | fast == 1? | slow == fast?
 *   -------+------+--------------------------+------------+---------------
 *      1   |   4  |        4 -> 16           |     no     |   no
 *      2   |  16  |       37 -> 58           |     no     |   no
 *      3   |  37  |       89 -> 145          |     no     |   no
 *      4   |  58  |       42 -> 20           |     no     |   no
 *      5   |  89  |        4 -> 16           |     no     |   no
 *      6   | 145  |       37 -> 58           |     no     |   no
 *      7   |  42  |       89 -> 145          |     no     |   no
 *      8   |  20  |       42 -> 20           |     no     |   yes, stop
 *
 *   Returns false. The chain is stuck in the cycle 4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20 -> 4.
 *
 * EDGE CASES
 *   n = 1            -> true    first round: slow = 1, fast = 1, fast == 1
 *   n = 19           -> true    round 1: slow 82, fast 68; round 2: fast 100 -> 1
 *   n = 7            -> true    7 -> 49 -> 97 -> 130 -> 10 -> 1
 *   n = 4            -> false   4 is already inside the unhappy cycle
 *   n = 2147483647   -> false   largest int; digit square sum is only 260, so no overflow
 *
 * COMMON MISTAKES
 *   - Looping "until the number is 1" with no cycle check: never stops for unhappy numbers.
 *   - while (slow != fast) with both starting at n: the loop never runs. Use do-while
 *     (or start fast one step ahead).
 *   - Moving fast only one step: slow and fast stay equal, so it returns false after round 1.
 *   - Forgetting to square the digit, or dividing before taking n % 10.
 *
 * COMPLEXITY
 *   O(log n) time: the first step looks at every digit; after that the numbers stay below 1000,
 *   so the chain reaches its cycle quickly. O(1) extra space.
 *
 * RELATED
 *   LeetCode 141 (LinkedListCycle, LinkedList/): the same slow/fast idea on linked list nodes.
 *   LeetCode 141 (LinkedListCycleDetector, FAQ/): the same linked list cycle problem, also in FAQ/.
 *   LeetCode 142 (StartOfCycle, FAQ/): after slow and fast meet, also find where the cycle starts.
 */
public class IsHappy {

    public static void main(String[] args) {
        int n = 2;
        System.out.println(isHappy(n));
    }

    public static boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
            if (fast == 1) return true;
        } while (slow != fast);
        return false;
    }

    public static int digitSquareSum(int n) {
        int sum = 0;
        int tmp;
        while (n != 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n = n / 10;
        }
        return sum;
    }
}
