import java.util.Arrays;

/**
 * LeetCode 134. Gas Station
 * https://leetcode.com/problems/gas-station/
 *
 * PROBLEM
 *   - Input: gas[i] = gas you pick up at station i; cost[i] = gas needed to drive from station i to i + 1.
 *     The stations form a circle, and you start with an empty tank.
 *   - Return: the station index to start from so you can drive the full circle once, or -1.
 *   - If an answer exists, it is unique.
 *
 * EXAMPLE
 *   Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 *   Output: 3
 *   Explanation:
 *   Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 *   Travel to station 4. Your tank = 4 - 1 + 5 = 8
 *   Travel to station 0. Your tank = 8 - 2 + 1 = 7
 *   Travel to station 1. Your tank = 7 - 3 + 2 = 6
 *   Travel to station 2. Your tank = 6 - 4 + 3 = 5
 *   Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 *   Therefore, return 3 as the starting index.
 *
 * HOW TO THINK
 *   - Clue 1: total gas < total cost -> no start can work, return -1.
 *     If total gas >= total cost, some start always works.
 *   - Clue 2: if you start at s and the tank goes negative after station i,
 *     no station between s and i can be the start either
 *     (you reached each of them with a tank >= 0, so starting there with 0 is no better).
 *     So jump the start straight to i + 1.
 *   - Technique: greedy, one pass over diff[i] = gas[i] - cost[i] with a running tank (total) that resets.
 *   - Rule that stays true: total = gas left in the tank after driving from start past station i;
 *     no index before start can be the answer.
 *   - Decision: total += diff[i]; total < 0 -> can't get here from start: start = i + 1, total = 0.
 *               otherwise -> keep driving.
 *   - Return: start. The totals check already proved an answer exists, so no need to drive the
 *     circle again to confirm it.
 *
 * STEPS
 *   1. diff[i] = gas[i] - cost[i]; also add up gasSum and costSum.
 *   2. If gasSum < costSum, return -1.
 *   3. total = 0, start = 0. Loop i over diff: total += diff[i].
 *   4. If total < 0: total = 0, start = i + 1.
 *   5. Return start.
 *
 * DRY RUN   gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 *   diff = [-2,-2,-2,3,3] (the code prints this line), gasSum = 15, costSum = 15 -> not less, go on.
 *   (total and start are values after the step)
 *
 *    i | diff[i] | total + diff[i] | action            | total | start
 *   ---+---------+-----------------+-------------------+-------+-------
 *    0 |   -2    |       -2        | < 0, start = 1    |   0   |   1
 *    1 |   -2    |       -2        | < 0, start = 2    |   0   |   2
 *    2 |   -2    |       -2        | < 0, start = 3    |   0   |   3
 *    3 |    3    |        3        | keep driving      |   3   |   3
 *    4 |    3    |        6        | keep driving      |   6   |   3
 *
 *   Returns 3.
 *
 * EDGE CASES
 *   gas = [2,3,4], cost = [3,4,3]   -> -1   gasSum 9 < costSum 10
 *   gas = [5], cost = [4]           -> 0    one station with enough gas
 *   gas = [4], cost = [5]           -> -1   one station, not enough gas
 *   gas = [3,1,1], cost = [1,2,2]   -> 0    tank goes 2, 1, 0, never negative, start never moves
 *   gas = [1,1], cost = [1,1]       -> 0    tank stays exactly 0; 0 is not < 0, so no reset
 *
 * COMMON MISTAKES
 *   - Moving start to start + 1 instead of i + 1: the stations in between can't work either,
 *     and retrying them makes it O(n^2).
 *   - Resetting on total <= 0: a tank of exactly 0 is fine. [1,1], [1,1] would return 2,
 *     an index that doesn't exist.
 *   - Skipping the gasSum < costSum check: the loop alone always returns some start.
 *     [2,3,4], [3,4,3] would return 2 instead of -1.
 *
 * COMPLEXITY
 *   O(n) time, O(n) extra space for diffArr (O(1) if gas[i] - cost[i] is used directly).
 *
 * RELATED
 *   LeetCode 55 (JumpGame, ArraysAndStrings/): another one-pass greedy where one running value
 *     decides the answer.
 *   LeetCode 121 (MaxProfit, ArraysAndStrings/): also restarts from a better point while scanning once
 *     (a new lowest price becomes the new buy day).
 */
public class GasStation {

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(canCompleteCircuit(gas,cost));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int[] diffArr = new int[gas.length];
        int gasSum = 0;
        int costSum = 0;

        for (int i=0; i < gas.length; i++) {
            diffArr[i] = gas[i] - cost[i];
            gasSum += gas[i];
            costSum += cost[i];
        }

        if(gasSum < costSum) {
            return -1;
        }

        int total = 0;
        int start = 0;

        System.out.println(Arrays.toString(diffArr));

        for (int i=0; i<diffArr.length; i++) {
            total += diffArr[i];

            if (total < 0) {
                total = 0;
                start = i + 1;
            }
        }

        return start;

    }
}
