import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class IntToRoman {

    public static void main(String[] args) {
        System.out.println(intToRoman(1994));
    }

    public static String intToRoman(int num) {
        // NOTE : This must be LinkedHashMap as we need to
        // iterate in order and place map elements
        // in decreasing order
        Map<String, Integer> m = getStringIntegerMap();

        StringBuilder roman = new StringBuilder();
        for (Map.Entry<String,Integer> e : m.entrySet()) {
            if(num / e.getValue() > 0) {
                int count = num / e.getValue();
                while (count > 0) {
                    roman.append(e.getKey());
                    count--;
                }
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
