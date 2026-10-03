public class HotWeatherAlertWindows {

    public static int countAlerts(int[] readings, int k, int threshold) {
        if (readings == null || readings.length < k || k <= 0) {
            return 0;
        }

        long requiredSum = (long) k * threshold;
        long currentWindowSum = 0;

        for (int i = 0; i < k; i++) {
            currentWindowSum += readings[i];
        }

        int alertCount = 0;
        if (currentWindowSum >= requiredSum) {
            alertCount++;
        }

        for (int i = k; i < readings.length; i++) {
            currentWindowSum += readings[i] - readings[i - k];
            if (currentWindowSum >= requiredSum) {
                alertCount++;
            }
        }

        return alertCount;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        int threshold = 4;
        System.out.println(countAlerts(readings, k, threshold));
    }
}
