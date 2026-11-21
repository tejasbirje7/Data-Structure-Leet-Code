import java.util.Arrays;

/**
 * LeetCode 443. String Compression
 * https://leetcode.com/problems/string-compression/
 *
 * PROBLEM
 *   - Input: an array chars (1..2000 long) of letters, digits or symbols.
 *   - For each run of the same char: write the char, then the run length if it is more than 1.
 *     A length of 10 or more is written as several digit chars ("12" -> '1','2').
 *   - Write the result into chars itself (O(1) extra space) and return its new length.
 *
 * EXAMPLE
 *   Input: chars = ["a","a","b","b","c","c","c"]
 *   Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 *   Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
 *
 * HOW TO THINK
 *   - Clue: "modify the input array" + "return the new length" = read pointer + write pointer.
 *   - Technique: i finds the start of each run; j walks to the end of the run; w is where to write.
 *   - Why writing never overwrites unread chars: a run of length L takes 1 + digits(L) <= L slots
 *     (for L >= 2), so w never passes i.
 *   - Rule that stays true: chars[0..w-1] is the compressed form of chars[0..i-1].
 *   - Decision per run: write the char; if count > 1, write each digit of count.
 *   - Return: w (the compressed length).
 *
 * STEPS
 *   1. i = 0, w = 0.
 *   2. While i < n: j = i; move j while chars[j] == chars[i]; count = j - i.
 *   3.   chars[w++] = chars[i].
 *   4.   If count > 1: for each digit of String.valueOf(count): chars[w++] = digit.
 *   5.   i = j.
 *   6. Return w.
 *
 * DRY RUN   chars = [a,a,b,b,c,c,c]
 *
 *    i | run char | j (end) | count | written at w | chars[0..w-1] after | w after
 *   ---+----------+---------+-------+--------------+---------------------+--------
 *    0 |    a     |    2    |   2   |      0       | [a,2]               |   2
 *    2 |    b     |    4    |   2   |      2       | [a,2,b,2]           |   4
 *    4 |    c     |    7    |   3   |      4       | [a,2,b,2,c,3]       |   6
 *
 *   Returns 6; chars starts with [a,2,b,2,c,3].
 *
 * EDGE CASES
 *   [a]                    -> 1, [a]          single char: count 1 is not written
 *   [a,b,c]                -> 3, [a,b,c]      no runs, nothing shrinks
 *   [a,b,b,b,b,b,b,b,b,b,b,b,b]  -> 4, [a,b,1,2]   count 12 becomes two chars
 *   [a,a,a,b,b,a,a]        -> 6, [a,3,b,2,a,2]   the same char in two separate runs
 *
 * COMMON MISTAKES
 *   - Writing count as (char) count or count + '0': breaks for counts >= 10; write each digit.
 *   - Writing "1" for runs of one: the problem says to skip the count then.
 *   - Building a new String / StringBuilder and copying back: works, but not O(1) extra space.
 *
 * COMPLEXITY
 *   O(n) time (i and j only move forward), O(1) extra space (the digits of count are at most 4 chars).
 *
 * RELATED
 *   LeetCode 26 (RemoveDuplicates, ArraysAndStrings/): same read/write pointers, keeps one of each run.
 *   LeetCode 283 (MoveZeroes, TwoPointers/): read/write pointers inside one array.
 */
public class StringCompression {

    public static void main(String[] args) {
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int len = compress(chars);
        System.out.println(len + " " + Arrays.toString(Arrays.copyOf(chars, len)));
    }

    public static int compress(char[] chars) {
        int i = 0;
        int w = 0;
        while (i < chars.length) {
            int j = i;
            while (j < chars.length && chars[j] == chars[i]) j++;
            int count = j - i;
            chars[w++] = chars[i];
            if (count > 1) {
                for (char d : String.valueOf(count).toCharArray()) chars[w++] = d;
            }
            i = j;
        }
        return w;
    }
}
