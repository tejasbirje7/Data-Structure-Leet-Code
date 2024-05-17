import java.util.Arrays;

public class PlusOne {

    public static void main(String[] args) {
        int[] arr = {9,8,9};
        System.out.println(Arrays.toString(PlusOne(arr)));
    }

    /**
     * Input: digits = [9,9,9]
     * Output: [1,0,0,0]
     * Explanation: The array represents the integer 9.
     * Incrementing by one gives 999 + 1 = 1000.
     * Thus, the result should be [1,0,0,0].
     */
    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int [n+1];
        newNumber[0] = 1;

        return newNumber;
    }

    public static int[] PlusOne(int[] digits) {
        int carry = 1;
        int index = digits.length - 1;

        while (index >= 0 && carry > 0) {
            digits[index] = (digits[index] + carry) % 10;
            carry = digits[index] == 0 ? 1 : 0;
            index--;
        }

        if (carry > 0) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
        }

        return digits;
    }

}
