import java.util.Arrays;

public class Candy {

    public static void main(String[] args) {
        int[] ratings = {5,4,3,5,6,2};
//        int[] ratings = {1,3,2,2,1};
        System.out.println(candy(ratings));
    }

    /**
     * Input: ratings = [1,0,2]
     * Output: 5
     * Explanation: You can allocate to the first, second and third
     * child with 2, 1, 2 candies respectively.
     * Steps :
     *   - Give 1 candy to all the children
     *   - Iterate from left to right
     *   - Iterate from right to left and check if already candies are more for current child
     *   - Add all the candies
     */
    public static int candy(int[] ratings) {
        int[] candies = new int[ratings.length];


        // Iterate from left to right to check if current index has more
        // candies then index-1 if rating is higher for ith index candidate
        for (int i=1 ; i<ratings.length; i++) {
            if(ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1] + 1;
            }
        }

        // Iterate from right to left to check if current index has more
        // candies then index+1 if rating is higher for ith index candidate
        for (int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1]) {

                /*
                 * Check if current index already has candies
                 * maximum then i+1 index if not then add 1 candy to i+1 index candies
                 * and assign it to current index which will ensure the highest rating
                 * student at current index has maximum candies
                 * then student with less rating in index+1
                 */
                if(!(candies[i] > candies[i+1])) {
                    candies[i] = candies[i+1] + 1;
                }
            }
        }

        // Add all the candies
        int sum = 0;
        for (int i : candies) {
            sum += i;
        }

        // return all the candies + all initial candies assigned to each student
        return sum + ratings.length;
    }
}
