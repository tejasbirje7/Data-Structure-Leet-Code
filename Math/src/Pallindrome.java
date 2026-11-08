/**
 * LeetCode 9. Palindrome Number
 * https://leetcode.com/problems/palindrome-number/
 *
 * PROBLEM
 *   - Input: an int x (any value from -2^31 to 2^31 - 1).
 *   - Return: true if x reads the same forwards and backwards, else false.
 *   - Follow-up: solve it without converting x to a string.
 *
 * EXAMPLE
 *   Input: x = 121
 *   Output: true
 *   Explanation: 121 reads as 121 from left to right and from right to left.
 *
 * HOW TO THINK
 *   - Clue: "without converting to a string" = work with digits using % 10 and / 10.
 *   - Technique: build the reversed number digit by digit, then compare it with the original.
 *   - Negative numbers are never palindromes: -121 reads 121- backwards. Return false at once.
 *   - Rule that stays true: rev = the digits already taken off x, in reverse order.
 *   - Decision: while x != 0: rev = rev * 10 + (last digit of x); drop that digit from x.
 *   - Return: org == rev (keep a copy of x in org, since the loop destroys x).
 *
 * STEPS
 *   1. If x < 0, return false.
 *   2. org = x, rev = 0.
 *   3. While x != 0: rev = rev * 10 + x % 10; x = x / 10.
 *   4. Return org == rev.
 *
 * DRY RUN   x = 121   (x and rev are values after the step)
 *
 *    step | x % 10 | rev = rev * 10 + digit | x = x / 10
 *   ------+--------+------------------------+------------
 *      1  |   1    |    0 * 10 + 1 = 1      |     12
 *      2  |   2    |    1 * 10 + 2 = 12     |      1
 *      3  |   1    |   12 * 10 + 1 = 121    |      0
 *
 *   x = 0, loop stops. org = 121 == rev = 121, returns true.
 *
 * EDGE CASES
 *   -121              -> false   negative, rejected at once
 *   10                -> false   rev = 1 (the trailing 0 becomes a leading 0 and vanishes)
 *   0                 -> true    loop never runs, 0 == 0
 *   7                 -> true    one digit
 *   1221              -> true    even number of digits
 *   123               -> false   rev = 321
 *   Integer.MAX_VALUE -> false   rev overflows int (7463847412 doesn't fit), but the wrapped value
 *                                never equals org; checked for every 10-digit int
 *   Integer.MIN_VALUE -> false   negative
 *   1000000001        -> true    a real palindrome never overflows: its reverse is itself
 *
 * COMMON MISTAKES
 *   - Comparing rev with x after the loop: x is 0 by then. Save the original first (org).
 *   - Forgetting negatives: the minus sign makes them never palindromes.
 *   - Ignoring overflow: rev can overflow for big x. It is harmless here (see EDGE CASES), but the
 *     cleaner version reverses only half: loop while x > rev, then check x == rev || x == rev / 10.
 *     That never overflows, but then numbers ending in 0 (except 0) must return false first.
 *
 * COMPLEXITY
 *   O(log10 x) time (one step per digit), O(1) extra space.
 *
 * RELATED
 *   LeetCode 125 (ValidPalindrome, TwoPointers/): palindrome check on a string with two pointers.
 *   LeetCode 202 (IsHappy, TwoPointers/): the same % 10 and / 10 digit loop.
 */
public class Pallindrome {

    public static void main(String[] args) {
        int x = 121;
        System.out.println(isPalindrome(x));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) return false;

        int org = x, rev =0;
        while(x != 0){
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return org == rev;
    }

}
