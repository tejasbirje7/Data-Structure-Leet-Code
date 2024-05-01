import java.util.Arrays;

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

        int total =0;
        int start = 0;

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
