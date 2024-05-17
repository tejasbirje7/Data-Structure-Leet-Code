import java.util.*;

public class LetterCombinations {

    public static Map<Character,String> digitToLetter = new HashMap<>();
    public static List<String> resultList = new ArrayList<>();

    public static void main(String[] args) {
        String digits = "";
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
