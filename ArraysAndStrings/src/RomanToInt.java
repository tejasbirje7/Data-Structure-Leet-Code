import java.util.HashMap;
import java.util.Map;

public class RomanToInt {
    public static void main(String[] args) {
        String s = "MCMXCIV";
        System.out.println(romanToInt(s));
    }

    public static int romanToInt(String s) {
        Map<Character, Integer> m = new HashMap<>();

        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);

        int number = 0;
        for (int i=0; i < s.length(); i++) {
            if(i+1 < s.length() && m.get(s.charAt(i)) < m.get(s.charAt(i+1))) {
                number -= m.get(s.charAt(i));
            } else {
                number += m.get(s.charAt(i));
            }
        }

        return number;

    }
}
