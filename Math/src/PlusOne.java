import java.util.Arrays;

public class PlusOne {

    public static void main(String[] args) {
        int[] arr = {9,8,9};
        System.out.println(Arrays.toString(PlusOne(arr)));
    }

    /**
     * You are given a large integer represented as an integer array digits,
     * where each digits[i] is the ith digit of the integer.
     * The digits are ordered from most significant to least significant in
     * left-to-right order. The large integer does not contain any leading 0's.
     *
     * Increment the large integer by one and return the resulting array of digits.
     *
     * Input: digits = [1,2,3]
     * Output: [1,2,4]
     * Explanation: The array represents the integer 123.
     * Incrementing by one gives 123 + 1 = 124.
     * Thus, the result should be [1,2,4].
     *
     * Input: digits = [4,3,2,1]
     * Output: [4,3,2,2]
     * Explanation: The array represents the integer 4321.
     * Incrementing by one gives 4321 + 1 = 4322.
     * Thus, the result should be [4,3,2,2].
     *
     * Input: digits = [9]
     * Output: [1,0]
     * Explanation: The array represents the integer 9.
     * Incrementing by one gives 9 + 1 = 10.
     * Thus, the result should be [1,0].
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
