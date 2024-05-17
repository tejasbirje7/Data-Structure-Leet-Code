import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String s = "aa";
        String t = "aa";
        System.out.println(minWindow(s,t));
    }

    /**
     * Input: s = "ADOBECODEBANC", t = "ABC"
     * Output: "BANC"
     * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
     */
    public static String minWindow(String s, String t) {
        if(s.isEmpty() || t.isEmpty())
            return "";

        Map<Character,Integer> countMap = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();

        for (int i=0; i < t.length(); i++) {
            countMap.put(t.charAt(i),countMap.getOrDefault(t.charAt(i),0) + 1);
        }

        int have = 0;
        int need = countMap.size();
        int l = 0;
        int minLength = Integer.MAX_VALUE;
        int[] result = new int[2];

        for (int r=0; r < s.length(); r++) {
            Character c = s.charAt(r);
            window.put(c,window.getOrDefault(c,0) + 1);

            if (shouldIncrementHavePointer(c,window,countMap))
                have++;

            while (have == need) {

                // UPDATE THE RESULT IF LENGTH OF SUBSTRING IS MINIMUM
                if ((r - l + 1) < minLength) {
                    result[0] = l;
                    result[1] = r;
                    minLength = r - l + 1;
                }

                // DECREMENT THE COUNT FOR LEFT MOST CHARACTER AS WE ARE GOING TO SHRINK THE WINDOW
                window.put(s.charAt(l),window.get(s.charAt(l)) - 1);

                // DECREMENT HAVE POINTER
                if(shouldDecrementHavePointer(s.charAt(l),window,countMap))
                    have--;

                // SHRINK WINDOW FROM LEFT BY POPPING ELEMENT FROM LEFT
                l++;
            }
        }

        int left = result[0];
        int right = result[1];

        return minLength != Integer.MAX_VALUE ? s.substring(left,right+1) : "";
    }

    public static boolean shouldIncrementHavePointer(Character c, Map<Character,Integer> window, Map<Character,Integer> countMap) {
        return countMap.containsKey(c) && Objects.equals(window.get(c), countMap.get(c));
    }

    public static boolean shouldDecrementHavePointer(Character c, Map<Character,Integer> window, Map<Character,Integer> countMap) {
        return countMap.containsKey(c) && window.get(c) < countMap.get(c);
    }

}
