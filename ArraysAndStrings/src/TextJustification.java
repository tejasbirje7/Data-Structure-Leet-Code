import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        String[] words2 = {"What","must","be","shall","be","acknowledgment"};
        int maxWidth = 16;
        TextJustification tx = new TextJustification();
        System.out.println(tx.fullJustify(words2, maxWidth));
    }

    /**
     * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
     * Output:
     * [
     *   "What   must   be",
     *   "acknowledgment  ",
     *   "shall be        "
     * ]
     * Explanation: Note that the last line is "shall be    " instead of "shall     be",
     * because the last line must be left-justified instead of fully-justified.
     * Note that the second line is also left-justified because it contains only one word.
     */
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

    /**
     * Find the words to justify in each row.
     * Thus calculating the maximum words including space
     * from given array, which should fit in each line
     */
    private int findRight(int left, String[] words, int maxWidth) {
        int right = left;
        // Increment pointer to next word after operation
        int sum = words[right++].length();

        while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth)
            sum += 1 + words[right++].length(); // Again increment pointer to next word after operation

        // Calculating +1 for space after each word above,
        // so while returning splicing the space added for last word above
        return right - 1;
    }

    private String justify(int left, int right, String[] words, int maxWidth) {
        if (right == left)
            return padResult(words[left], maxWidth);

        boolean isLastLine = right == words.length - 1; // Checking if last line
        int numSpacesBetweenWords = right - left; // Space between each word
        int remainingSpaceAfterOccupiedByChar = maxWidth - wordsLength(left, right, words); // Remaining space, after occupied by characters in words

        // Checking number of spaces to be added between words
        // For example if words are `What must be` and maxWidth is 16 words per line
        // Now since there are 3 words, the space in between will be
        // remainingSpaceAfterOccupiedByChar / numSpacesBetweenWords
        // which is 6 / 2 = 3; so 3 spaces between each words
        String space = isLastLine ? " " : blank(remainingSpaceAfterOccupiedByChar / numSpacesBetweenWords);

        // Calculating remained if spaces are not equal
        // For example in above case if remainder is 5 then we would need to
        // append the extra space after first word
        int remainder = isLastLine ? 0 : remainingSpaceAfterOccupiedByChar % numSpacesBetweenWords;

        StringBuilder result = new StringBuilder();
        for (int i = left; i <= right; i++)
            result.append(words[i])
                    .append(space)
                    .append(remainder-- > 0 ? " " : "");

        return padResult(result.toString().trim(), maxWidth);
    }

    // Calculating number of characters in all the words
    // between left to right index
    private int wordsLength(int left, int right, String[] words) {
        int wordsLength = 0;
        for (int i = left; i <= right; i++)
            wordsLength += words[i].length();
        return wordsLength;
    }


    private String padResult(String result, int maxWidth) {
        return result + blank(maxWidth - result.length());
    }

    // Returns the number of blank spaces as per specified in length
    private String blank(int length) {
        return new String(new char[length]).replace('\0', ' ');
    }
}
