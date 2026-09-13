package string.class_problems;

public class BMICalculator {
    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 25.0) {
            return "Normal";
        } else if (bmi >= 25.0 && bmi < 30.0) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        if (heights == null || weights == null || heights.length != weights.length) {
            System.out.println("Invalid data provided for wellness report.");
            return;
        }

        System.out.println("=========================================================================");
        System.out.printf("%-10s | %-12s | %-12s | %-10s | %-15s%n",
                "Person", "Height (m)", "Weight (kg)", "BMI", "Status");
        System.out.println("=========================================================================");

        for (int i = 0; i < heights.length; i++) {
            double h = heights[i];
            double w = weights[i];
            if (h <= 0 || w <= 0) {
                continue;
            }
            double bmi = w / (h * h);
            String status = getBmiStatus(bmi);
            System.out.printf("%-10s | %-12.2f | %-12.2f | %-10.2f | %-15s%n",
                    "Person " + (i + 1), h, w, bmi, status);
        }
        System.out.println("=========================================================================");
    }

    public static void main(String[] args) {
        double[] sampleHeights = {1.75, 1.60, 1.80, 1.65, 1.70};
        double[] sampleWeights = {70.0, 90.0, 62.0, 85.0, 52.0};

        printWellnessReport(sampleHeights, sampleWeights);
    }
}
