import java.util.*;

/**
 * LeetCode 17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * PROBLEM
 *   - Input: a string of digits from 2 to 9 (0 to 4 digits long).
 *   - Return: every letter string the digits could stand for on a phone keypad, in any order.
 *   - Keypad: 2 = abc, 3 = def, 4 = ghi, 5 = jkl, 6 = mno, 7 = pqrs, 8 = tuv, 9 = wxyz.
 *   - Empty input -> empty list.
 *
 * EXAMPLE
 *   Input: digits = "23"
 *   Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * HOW TO THINK
 *   - Clue: "return all possible letter combinations" = try every choice, so backtracking.
 *   - Technique: one recursion level per digit; at each level try every letter of that digit.
 *   - Rule that stays true: currentCombination holds one letter for each digit before currentIndex.
 *   - Decision: currentIndex == digits.length() -> one full string: add it, return.
 *               else -> for each letter of digits[currentIndex]: append it, recurse with
 *                       currentIndex + 1, then delete the last char (backtrack).
 *   - Return: resultList, which is a static field shared by every call (see EDGE CASES).
 *
 * STEPS
 *   1. If digits is null or empty, return resultList.
 *   2. Fill digitToLetter with the keypad map.
 *   3. generateCombinations(digits, 0, empty StringBuilder).
 *   4. If currentIndex == digits.length(): add the string and return.
 *   5. Else, for each letter of the current digit: append, recurse with currentIndex + 1, delete last char.
 *   6. Return resultList.
 *
 * DRY RUN   digits = "23"   (combination is the builder right after the append; each append is undone after its call)
 *
 *    depth | digit | letters | letter | combination | result
 *   -------+-------+---------+--------+-------------+---------------------
 *      0   |  '2'  |   abc   |   a    |     a       |
 *      1   |  '3'  |   def   |   d    |     ad      | index 2 = length, add "ad"
 *      1   |  '3'  |   def   |   e    |     ae      | add "ae"
 *      1   |  '3'  |   def   |   f    |     af      | add "af"; loop ends, delete a
 *      0   |  '2'  |   abc   |   b    |     b       |
 *      1   |  '3'  |   def   |   d    |     bd      | add "bd"
 *      1   |  '3'  |   def   |   e    |     be      | add "be"
 *      1   |  '3'  |   def   |   f    |     bf      | add "bf"; loop ends, delete b
 *      0   |  '2'  |   abc   |   c    |     c       |
 *      1   |  '3'  |   def   |   d    |     cd      | add "cd"
 *      1   |  '3'  |   def   |   e    |     ce      | add "ce"
 *      1   |  '3'  |   def   |   f    |     cf      | add "cf"; loop ends, delete c
 *
 *   Returns [ad, ae, af, bd, be, bf, cd, ce, cf].
 *
 * EDGE CASES
 *   ""       -> []            early return (also for null)
 *   "2"      -> [a, b, c]     one level, three strings (on a fresh run)
 *   "79"     -> 16 strings    4 letters x 4 letters
 *   "2345"   -> 81 strings    longest input, 3 x 3 x 3 x 3
 *   "21"     -> []            '1' has no letters, so no string is ever finished (LeetCode digits are 2-9)
 *   Calling it twice: "23" then "2" returns [ad, ..., cf, a, b, c].
 *     resultList is static and never cleared, so old answers stay. Fine for one LeetCode call,
 *     wrong if the same JVM calls it again.
 *
 * COMMON MISTAKES
 *   - Keeping the result in a static field: it leaks between calls. Make a new list in letterCombinations.
 *   - Forgetting deleteCharAt after the recursive call: letters pile up ("ad", then "ade").
 *   - Returning [""] for empty input: LeetCode wants [], so check for empty first.
 *
 * COMPLEXITY
 *   O(4^n * n) time (up to 4 letters per digit, n chars to build each string),
 *   O(n) extra space for the recursion and the builder, not counting the output.
 *
 * RELATED
 *   LeetCode 77 (Combinations, Backtracking/): pick k numbers from 1..n instead of one letter per digit.
 *   LeetCode 46 (Permutations, Backtracking/): same choose / recurse / undo pattern over the numbers left.
 */
public class LetterCombinations {

    public static Map<Character,String> digitToLetter = new HashMap<>();
    public static List<String> resultList = new ArrayList<>();

    public static void main(String[] args) {
        String digits = "23";
        System.out.println(letterCombinations(digits));
    }

    public static List<String> letterCombinations(String digits) {
        if(digits == null || digits.isEmpty()){
            return resultList;
        }

        digitToLetter.put('2',"abc");
        digitToLetter.put('3',"def");
        digitToLetter.put('4',"ghi");
        digitToLetter.put('5',"jkl");
        digitToLetter.put('6',"mno");
        digitToLetter.put('7',"pqrs");
        digitToLetter.put('8',"tuv");
        digitToLetter.put('9',"wxyz");

        generateCombinations(digits,0,new StringBuilder());

        return resultList;
    }

    public static void generateCombinations(String digits, int currentIndex, StringBuilder currentCombination){
        if(currentIndex == digits.length()){
            resultList.add(currentCombination.toString());
            return;
        }
        char currentDigit = digits.charAt(currentIndex);
        String letterOption = digitToLetter.get(currentDigit);

        if(letterOption != null){
            for(int i=0;i<letterOption.length();i++){
                char letter = letterOption.charAt(i);
                currentCombination.append(letter);
                generateCombinations(digits,currentIndex+1,currentCombination);
                currentCombination.deleteCharAt(currentCombination.length()-1);
            }
        }
    }
}
