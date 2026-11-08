import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 68. Text Justification
 * https://leetcode.com/problems/text-justification/
 *
 * PROBLEM
 *   - Input: an array of words and a line width maxWidth.
 *   - Return: the text as lines of exactly maxWidth characters, fully justified.
 *   - Pack as many words as fit on each line (greedy), with at least one space between words.
 *   - Spread extra spaces as evenly as possible; if they don't divide evenly, the LEFT gaps get one more.
 *   - The last line, and any line with a single word, is left-justified: single spaces, padded at the end.
 *
 * EXAMPLE
 *   Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 *   Output:
 *   [
 *     "What   must   be",
 *     "acknowledgment  ",
 *     "shall be        "
 *   ]
 *   Explanation: Note that the last line is "shall be    " instead of "shall     be",
 *   because the last line must be left-justified instead of fully-justified.
 *   Note that the second line is also left-justified because it contains only one word.
 *
 * HOW TO THINK
 *   - Clue: "as many words as you can in each line" = greedy; then it's pure counting.
 *   - Technique: simulation, one line at a time, with two pointers left and right
 *     marking the first and last word of the line.
 *   - Rule that stays true: every word before left is already placed in result.
 *   - Decision (findRight): keep adding the next word while sum + 1 (space) + its length <= maxWidth.
 *   - Decision (justify):
 *       one word (right == left)  -> word + padding.
 *       last line                 -> words joined by one space, + padding.
 *       otherwise                 -> spaces = maxWidth - letters; gaps = right - left;
 *                                    each gap gets spaces / gaps, and the first spaces % gaps gaps get one more.
 *   - Check one word first: it has 0 gaps, so spaces / gaps would divide by zero.
 *   - Return: the list of lines; then left = right + 1 for the next line.
 *
 * STEPS
 *   1. left = 0. While left < words.length:
 *   2.   findRight: right = last word that still fits (sum counts one space before each extra word).
 *   3.   justify(left, right): one word -> pad it; last line -> join with " " and pad;
 *        else space = blank(spaces / gaps), remainder = spaces % gaps; append each word + space,
 *        + one more " " while remainder-- > 0; trim the trailing spaces, then pad.
 *   4.   Add the line, set left = right + 1.
 *   5. Return result.
 *
 * DRY RUN   words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 17
 *   (maxWidth 17, not 16, so one line has a remainder and every case of justify is hit.)
 *
 *    line | left | right | words on line      | sum | case     | gaps | spaces | each | remainder | line built
 *   ------+------+-------+--------------------+-----+----------+------+--------+------+-----------+---------------------
 *     1   |  0   |   2   | What must be       | 12  | normal   |  2   |   7    |  3   |     1     | "What    must   be"
 *     2   |  3   |   3   | acknowledgment     | 14  | one word |  -   |   -    |  -   |     -     | "acknowledgment   "
 *     3   |  4   |   5   | shall be           |  8  | last     |  1   |   -    |  1   |     0     | "shall be         "
 *
 *   Line 1: findRight stops because 12 + 1 + 14 = 27 > 17; 17 - 10 letters = 7 spaces;
 *     7 / 2 = 3 each, 7 % 2 = 1 extra, so the first gap gets 4 and the second gets 3.
 *   Line 2: 14 + 1 + 5 = 20 > 17, so the word is alone; padded with 3 spaces.
 *   Line 3: right = 5 is the last word, so single spaces + 9 spaces of padding.
 *   Returns ["What    must   be", "acknowledgment   ", "shall be         "].
 *
 * EDGE CASES
 *   LeetCode example 2, maxWidth = 16   -> ["What   must   be", "acknowledgment  ", "shall be        "]
 *                                          6 spaces / 2 gaps divides evenly, remainder 0
 *   ["This","is","an","example","of","text","justification."], 16
 *                                       -> ["This    is    an", "example  of text", "justification.  "]
 *                                          line 2 has 3 spaces for 2 gaps: the left gap gets the extra one
 *   ["a"], 1                            -> ["a"]        one word that fills the line exactly
 *   ["a","b"], 1                        -> ["a", "b"]   one word per line; the first is not the last line
 *   []                                  -> []           the loop never runs (LeetCode guarantees at least one word)
 *   ["abc"], 2                          -> crashes (NegativeArraySizeException in blank(-1));
 *                                          LeetCode guarantees every word fits in maxWidth
 *
 * COMMON MISTAKES
 *   - Fully justifying the last line: it must use single spaces and padding at the end.
 *   - Giving the extra spaces to the right-hand gaps: the LEFT gaps get them first.
 *   - Dividing by the number of gaps on a one-word line: 0 gaps, divide by zero. Handle right == left first.
 *   - Forgetting the +1 for the space when checking if the next word fits.
 *
 * COMPLEXITY
 *   O(L) time, where L = total characters in the output (lines * maxWidth); each word is measured
 *     a constant number of times. O(maxWidth) extra space per line, besides the result.
 *
 * RELATED
 *   LeetCode 151 (ReverseWordsInString, ArraysAndStrings/): also works word by word, but squeezes
 *     spaces down to one instead of spreading them to fill a width.
 */
public class TextJustification {

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        String[] words2 = {"What","must","be","acknowledgment","shall","be"};
        int maxWidth = 17;
        TextJustification tx = new TextJustification();
        System.out.println(tx.fullJustify(words2, maxWidth));
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        int left = 0;
        List<String> result = new ArrayList<>();

        while (left < words.length) {
            int right = findRight(left, words, maxWidth);
            result.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }

        return result;
    }

    private int findRight(int left, String[] words, int maxWidth) {
        int right = left;
        int sum = words[right++].length();

        while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth)
            sum += 1 + words[right++].length();

        return right - 1;
    }

    private String justify(int left, int right, String[] words, int maxWidth) {
        if (right == left)
            return padResult(words[left], maxWidth);

        boolean isLastLine = right == words.length - 1;
        int numSpacesBetweenWords = right - left;
        int remainingSpaceAfterOccupiedByChar = maxWidth - wordsLength(left, right, words);

        String space = isLastLine ? " " : blank(remainingSpaceAfterOccupiedByChar / numSpacesBetweenWords);

        int remainder = isLastLine ? 0 : remainingSpaceAfterOccupiedByChar % numSpacesBetweenWords;

        StringBuilder result = new StringBuilder();
        for (int i = left; i <= right; i++)
            result.append(words[i])
                    .append(space)
                    .append(remainder-- > 0 ? " " : "");

        return padResult(result.toString().trim(), maxWidth);
    }

    private int wordsLength(int left, int right, String[] words) {
        int wordsLength = 0;
        for (int i = left; i <= right; i++)
            wordsLength += words[i].length();
        return wordsLength;
    }


    private String padResult(String result, int maxWidth) {
        return result + blank(maxWidth - result.length());
    }

    private String blank(int length) {
        return new String(new char[length]).replace('\0', ' ');
    }
}
