import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PairWithTargetSumUnsorted {

    public static boolean hasPairHashSet(int[] nums, int target) {
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

    public static boolean hasPairTwoPointers(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);

        int left = 0;
        int right = copy.length - 1;

        while (left < right) {
            int currentSum = copy[left] + copy[right];
            if (currentSum == target) {
                return true;
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    public static boolean hasPairWithTargetSum(int[] nums, int target) {
        return hasPairHashSet(nums, target);
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        System.out.println(hasPairWithTargetSum(nums1, 9));

        int[] nums2 = {3, 4, 6};
        System.out.println(hasPairWithTargetSum(nums2, 20));
    }
}
