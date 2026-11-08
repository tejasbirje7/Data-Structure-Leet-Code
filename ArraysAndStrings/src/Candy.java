/**
 * LeetCode 135. Candy
 * https://leetcode.com/problems/candy/
 *
 * PROBLEM
 *   - Input: ratings[i] = the rating of child i; the children stand in a line.
 *   - Rules: every child gets at least 1 candy.
 *     A child with a higher rating than a neighbour must get more candies than that neighbour.
 *   - Return: the minimum total number of candies.
 *
 * EXAMPLE
 *   Input: ratings = [1,0,2]
 *   Output: 5
 *   Explanation: You can allocate to the first, second and third
 *   child with 2, 1, 2 candies respectively.
 *
 * HOW TO THINK
 *   - Clue: each child has two neighbours and the rule must hold on both sides.
 *     Hard to fix both sides at once, easy to fix one side at a time.
 *   - Technique: two greedy passes.
 *     Left to right fixes "higher than my left neighbour".
 *     Right to left fixes "higher than my right neighbour" without breaking the first rule.
 *   - Trick in this code: candies[i] holds only the EXTRA candies on top of the 1 every child gets.
 *     So the array starts at 0 and the answer is sum + n.
 *   - Rule that stays true: after pass 1, candies[i] > candies[i-1] wherever ratings[i] > ratings[i-1].
 *     Pass 2 only ever raises values, so that stays true.
 *   - Decision: pass 1: ratings[i] > ratings[i-1] -> candies[i] = candies[i-1] + 1, else leave it 0.
 *               pass 2: ratings[i] > ratings[i+1] and candies[i] is not already bigger
 *                       -> candies[i] = candies[i+1] + 1; if it's already bigger, keep it.
 *   - Why it works: each child ends with max(need from the left, need from the right),
 *     the smallest number that keeps both neighbours happy.
 *   - Return: sum of the extras + ratings.length (the base candy per child).
 *
 * STEPS
 *   1. candies = new int[n], all 0 (extras on top of the base 1).
 *   2. Left to right, i = 1..n-1: if ratings[i] > ratings[i-1], candies[i] = candies[i-1] + 1.
 *   3. Right to left, i = n-2..0: if ratings[i] > ratings[i+1] and candies[i] <= candies[i+1],
 *      candies[i] = candies[i+1] + 1.
 *   4. Return sum(candies) + n.
 *
 * DRY RUN   ratings = [5,4,3,5,6,2]   (candies = extras, after the step)
 *
 *    pass  | i | rating higher than neighbour? | action                | candies
 *   -------+---+-------------------------------+-----------------------+---------------
 *    start |   |                               |                       | [0,0,0,0,0,0]
 *    L->R  | 1 | 4 > 5 (left)  no              | skip                  | [0,0,0,0,0,0]
 *    L->R  | 2 | 3 > 4 (left)  no              | skip                  | [0,0,0,0,0,0]
 *    L->R  | 3 | 5 > 3 (left)  yes             | candies[3] = 0 + 1    | [0,0,0,1,0,0]
 *    L->R  | 4 | 6 > 5 (left)  yes             | candies[4] = 1 + 1    | [0,0,0,1,2,0]
 *    L->R  | 5 | 2 > 6 (left)  no              | skip                  | [0,0,0,1,2,0]
 *    R->L  | 4 | 6 > 2 (right) yes             | 2 > 0 already, keep   | [0,0,0,1,2,0]
 *    R->L  | 3 | 5 > 6 (right) no              | skip                  | [0,0,0,1,2,0]
 *    R->L  | 2 | 3 > 5 (right) no              | skip                  | [0,0,0,1,2,0]
 *    R->L  | 1 | 4 > 3 (right) yes             | candies[1] = 0 + 1    | [0,1,0,1,2,0]
 *    R->L  | 0 | 5 > 4 (right) yes             | candies[0] = 1 + 1    | [2,1,0,1,2,0]
 *
 *   Returns sum 6 + n 6 = 12. Real candies per child: [3,2,1,2,3,1].
 *
 * EDGE CASES
 *   [1,2,2]       -> 4    equal neighbours need nothing extra: [1,2,1]
 *   [5]           -> 1    one child, both loops skip
 *   [2,2,2]       -> 3    all equal: everyone gets 1
 *   [1,2,3,4]     -> 10   only the left pass adds: 1+2+3+4
 *   [4,3,2,1]     -> 10   only the right pass adds
 *   [1,3,2,2,1]   -> 7    candies [1,2,1,2,1]
 *   []            -> 0    loops never run; LeetCode guarantees n >= 1
 *
 * COMMON MISTAKES
 *   - Pass 2 without the "already bigger" check: it can lower a value pass 1 set.
 *     [1,2,3,1]: index 2 has extra 2 from the left; candies[3] + 1 = 1 would drop it. Keep the max.
 *   - Only one pass: a falling run like [4,3,2,1] needs the right-to-left pass.
 *   - Forgetting the base candy: here candies start at 0, so add n at the end
 *     (or start every entry at 1 and don't add n).
 *   - Using >= on ratings: equal ratings don't need more candies.
 *
 * COMPLEXITY
 *   O(n) time (three passes), O(n) extra space for candies.
 *
 * RELATED
 *   LeetCode 238 (ProductExceptSelf, ArraysAndStrings/): the same left pass + right pass pattern,
 *     with products instead of candy counts.
 *   LeetCode 42 (RainWater, ArraysAndStrings/): each spot also needs facts from both sides
 *     (tallest bar on the left and on the right); solved there with two pointers.
 */
public class Candy {

    public static void main(String[] args) {
        int[] ratings = {5,4,3,5,6,2};
//        int[] ratings = {1,3,2,2,1};
        System.out.println(candy(ratings));
    }

    public static int candy(int[] ratings) {
        int[] candies = new int[ratings.length];


        for (int i=1 ; i < ratings.length; i++) {
            if(ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1] + 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1]) {

                if(!(candies[i] > candies[i+1])) {
                    candies[i] = candies[i+1] + 1;
                }
            }
        }

        int sum = 0;
        for (int i : candies) {
            sum += i;
        }

        return sum + ratings.length;
    }
}
