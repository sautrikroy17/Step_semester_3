import java.util.HashSet;
import java.util.Set;

public class PairWithTargetSum {

    public static boolean hasPairWithSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return true;
            }
            seen.add(num);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] sample1 = {2, 7, 11, 15};
        System.out.println(hasPairWithSum(sample1, 9));

        int[] sample2 = {3, 4, 6};
        System.out.println(hasPairWithSum(sample2, 20));
    }
}
