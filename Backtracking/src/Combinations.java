import java.util.ArrayList;
import java.util.List;

public class Combinations {

    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        System.out.println(combine(4,2));
    }

    /**
     * Input: n = 4, k = 2
     * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
     * Explanation: There are 4 choose 2 = 6 total combinations.
     * Note that combinations are unordered, i.e., [1,2] and [2,1]
     * are considered to be the same combination.
     */

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combs = new ArrayList<>();
        combine(combs,new ArrayList<>(),1,n,k);
        return combs;
    }

    public static void combine(List<List<Integer>> combs,
                               List<Integer> comb,
                               int start,
                               int n,
                               int k) {

        if ( k == 0) {
            combs.add(new ArrayList<>(comb));
            return;
        }

        for (int i=start; i<=n; i++) {
            comb.add(i);
            combine(combs,comb,i+1,n,k-1);
            comb.remove(comb.size() - 1);
        }
    }
}
