import java.util.Arrays;

public class HIndex {

    // #TODO Learn more about H-index
    public static void main(String[] args) {
        int[] citations = {3,0,6,1,5};
        System.out.println(hIndex(citations));
    }

    /**
     *
     * Given an array of integers citations where citations[i] is the number of
     * citations a researcher received for their ith paper, return the researcher's h-index.
     *
     * According to the definition of h-index on Wikipedia: The h-index
     * is defined as the maximum value of h such that the given researcher
     * has published at least h papers that have each been cited at least h times.
     *
     * Input: citations = [3,0,6,1,5]
     * Output: 3
     * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total
     * and each of them had received 3, 0, 6, 1, 5 citations respectively.
     * Since the researcher has 3 papers with at least 3 citations each and
     * the remaining two with no more than 3 citations each, their h-index is 3.
     */
    public static int hIndex(int[] citations) {
        /**
         *  Will keep track array where each index represent the paper number and
         *  the value will be the count of citations for that i`th paper.
         *  Citation array could have duplicate i.e. 2 papers may get n equal citations.
         *  So citation array is different from number our track array.
         */
        int n = citations.length;
        int[] trackArr = new int[n + 1];

        for (int c : citations) {
            if (c > n)
                trackArr[n]++;
            else
                trackArr[c]++;
        }

        System.out.println(Arrays.toString(trackArr));

        int count = 0;
        for (int i = n; i >= 0; i--) {
            count = count + trackArr[i];
            if (count >= i)
                return i;
        }
        return 0;
    }
}
