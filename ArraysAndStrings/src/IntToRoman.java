import java.util.LinkedHashMap;
import java.util.Map;

public class IntToRoman {

    public static void main(String[] args) {
        System.out.println(intToRoman(3749));
    }

    /**
     * Input: num = 3749
     * Output: "MMMDCCXLIX"
     * Explanation:
     * 3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M)
     *  700 = DCC as 500 (D) + 100 (C) + 100 (C)
     *   40 = XL as 10 (X) less of 50 (L)
     *    9 = IX as 1 (I) less of 10 (X)
     * Note: 49 is not 1 (I) less of 50 (L) because the conversion is based on decimal places
     *
     * Input: num = 58
     * Output: "LVIII"
     * Explanation:
     * 50 = L
     *  8 = VIII
     *
     * Input: num = 1994
     * Output: "MCMXCIV"
     * Explanation:
     * 1000 = M
     *  900 = CM
     *   90 = XC
     *    4 = IV
     */
    public static String intToRoman(int num) {
        // NOTE : This must be LinkedHashMap as we need to
        // iterate in order and place map elements
        // in decreasing order
        Map<String, Integer> m = getStringIntegerMap();

        StringBuilder roman = new StringBuilder();
        for (Map.Entry<String,Integer> e : m.entrySet()) {
            if(num / e.getValue() > 0) {
                // Check how many roman characters needs to be inserted
                // by dividing the given num with roman value
                int count = num / e.getValue();

                // Append number of roman characters found in above step
                while (count > 0) {
                    roman.append(e.getKey());
                    count--;
                }

                // Remove first digit from the given number
                num = num % e.getValue();
            }
        }
        return roman.toString();
    }

    private static Map<String, Integer> getStringIntegerMap() {
        Map<String, Integer> m = new LinkedHashMap<>();
        m.put("M", 1000);
        m.put("CM", 900);
        m.put("D", 500);
        m.put("CD", 400);
        m.put("C", 100);
        m.put("XC", 90);
        m.put("L", 50);
        m.put("XL", 40);
        m.put("X", 10);
        m.put("IX", 9);
        m.put("V", 5);
        m.put("IV",4);
        m.put("I", 1);
        return m;
    }


}
