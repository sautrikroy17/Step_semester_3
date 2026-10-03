import java.util.Arrays;

public class MergingTwoTokenQueues {

    public static int[] mergeTokens(int[] counterA, int[] counterB) {
        if (counterA == null) counterA = new int[0];
        if (counterB == null) counterB = new int[0];

        int n = counterA.length;
        int m = counterB.length;
        int[] merged = new int[n + m];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < n && j < m) {
            if (counterA[i] <= counterB[j]) {
                merged[k++] = counterA[i++];
            } else {
                merged[k++] = counterB[j++];
            }
        }

        while (i < n) {
            merged[k++] = counterA[i++];
        }

        while (j < m) {
            merged[k++] = counterB[j++];
        }

        return merged;
    }

    public static void main(String[] args) {
        int[] counterA1 = {3, 8, 15, 20};
        int[] counterB1 = {5, 8, 12};
        System.out.println(Arrays.toString(mergeTokens(counterA1, counterB1)));

        int[] counterA2 = {};
        int[] counterB2 = {4, 9};
        System.out.println(Arrays.toString(mergeTokens(counterA2, counterB2)));
    }
}
