/**
 * LeetCode 151. Reverse Words in a String
 * https://leetcode.com/problems/reverse-words-in-a-string/
 *
 * PROBLEM
 *   - Input: a string s of words separated by spaces.
 *   - Return: the words in reverse order, joined by exactly one space.
 *   - s may have leading, trailing, or several spaces between words; none of them may stay in the answer.
 *   - s has at least one word.
 *
 * EXAMPLE
 *   Input: s = "the sky is blue"
 *   Output: "blue is sky the"
 *
 *   Input: s = "  hello world  "
 *   Output: "world hello"
 *   Explanation: Your reversed string should not contain leading or trailing spaces.
 *
 *   Input: s = "a good   example"
 *   Output: "example good a"
 *   Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 * HOW TO THINK
 *   - Clue: "reverse the words" (not the letters) + messy spaces.
 *     Read words left to right and put each finished word in FRONT of the answer.
 *   - Technique: one scan with two StringBuilders.
 *     temp = " " + letters of the current word; sb = the answer being built.
 *   - Rule that stays true: sb = the finished words in reverse order, each with one space in front.
 *     wordAppended = temp holds at least one letter.
 *   - Decision: letter                       -> append it to temp, wordAppended = true.
 *               space, no word in temp       -> skip it (leading or repeated spaces).
 *               space, word in temp          -> insert temp at the front of sb, reset temp to " ".
 *   - After the loop: if a word is still in temp (s doesn't end with a space), insert it too.
 *   - Return: sb without its first character (the extra space in front of the first word).
 *
 * STEPS
 *   1. sb = "", temp = " ", wordAppended = false.
 *   2. For each character c of s:
 *   3.   If c is not a space: temp.append(c), wordAppended = true.
 *   4.   Else if wordAppended: sb.insert(0, temp), temp = " ", wordAppended = false. Else skip.
 *   5. If wordAppended: sb.insert(0, temp).
 *   6. Delete sb's first character (the leading space) and return sb.
 *
 * DRY RUN   s = "  the sky is blue  "   (temp and sb are after the step; quotes show the spaces)
 *
 *     i | char | action                | temp     | sb
 *   ----+------+-----------------------+----------+----------------------
 *     0 | ' '  | no word yet, skip     | " "      | ""
 *     1 | ' '  | no word yet, skip     | " "      | ""
 *     2 | 't'  | append                | " t"     | ""
 *     3 | 'h'  | append                | " th"    | ""
 *     4 | 'e'  | append                | " the"   | ""
 *     5 | ' '  | insert " the" at 0    | " "      | " the"
 *     6 | 's'  | append                | " s"     | " the"
 *     7 | 'k'  | append                | " sk"    | " the"
 *     8 | 'y'  | append                | " sky"   | " the"
 *     9 | ' '  | insert " sky" at 0    | " "      | " sky the"
 *    10 | 'i'  | append                | " i"     | " sky the"
 *    11 | 's'  | append                | " is"    | " sky the"
 *    12 | ' '  | insert " is" at 0     | " "      | " is sky the"
 *    13 | 'b'  | append                | " b"     | " is sky the"
 *    14 | 'l'  | append                | " bl"    | " is sky the"
 *    15 | 'u'  | append                | " blu"   | " is sky the"
 *    16 | 'e'  | append                | " blue"  | " is sky the"
 *    17 | ' '  | insert " blue" at 0   | " "      | " blue is sky the"
 *    18 | ' '  | no word yet, skip     | " "      | " blue is sky the"
 *
 *   wordAppended is false, so nothing is left to insert. Delete the leading space.
 *   Returns "blue is sky the".
 *
 * EDGE CASES
 *   "the sky is blue"    -> "blue is sky the"   no trailing space: the last word is inserted after the loop
 *   "a good   example"   -> "example good a"    repeated spaces are skipped
 *   "a"                  -> "a"                 one word, inserted after the loop
 *   "   "                -> ""                  no word; delete(0, 1) on an empty sb does nothing
 *                                               (LeetCode guarantees at least one word)
 *   ""                   -> ""                  the loop never runs
 *
 * COMMON MISTAKES
 *   - s.split(" "): repeated spaces give empty strings, which become extra spaces.
 *     Use s.trim().split("\\s+"), or skip spaces when no word is pending (as here).
 *   - Forgetting the last word when s doesn't end with a space: check wordAppended after the loop.
 *   - Leaving the leading space: every word carries a space in front, so remove the first character at the end.
 *
 * COMPLEXITY
 *   O(n^2) time in the worst case: every sb.insert(0, ...) shifts the whole answer
 *     (many short words, e.g. "a b c d ..."). Scanning from the right and appending would make it O(n).
 *   O(n) extra space for the two builders.
 *
 * RELATED
 *   LeetCode 71 (SimplifiedPath, Stack/): split a path on '/', skip empty parts, rebuild it;
 *     the same "ignore repeated separators" problem.
 *   LeetCode 68 (TextJustification, ArraysAndStrings/): also works word by word, but has to add spaces
 *     to fill a fixed width instead of squeezing them to one.
 */
public class ReverseWordsInString {

    public static void main(String[] args) {
        String s = "  the sky is blue  ";
        System.out.println(reverseWords(s));
    }

    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder(" ");
        boolean wordAppended = false;

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != ' ') {
                temp.append(s.charAt(i));
                wordAppended = true;
            }
            else {
                if(!wordAppended) continue;
                sb.insert(0, temp);
                temp.setLength(0);
                temp.insert(0, " ");
                wordAppended = false;
            }
        }

        if(wordAppended) sb.insert(0, temp);
        sb.delete(0, 1);
        return String.valueOf(sb);
    }
}
