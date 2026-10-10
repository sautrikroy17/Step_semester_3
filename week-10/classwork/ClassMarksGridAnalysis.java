import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ClassMarksGridAnalysis {

    public static void analyze(List<String> names, List<int[]> marksList) {
        if (names.isEmpty() || marksList.isEmpty()) {
            return;
        }

        int numStudents = names.size();
        int numSubjects = marksList.get(0).length;

        int[][] grid = new int[numStudents][numSubjects];
        for (int i = 0; i < numStudents; i++) {
            grid[i] = marksList.get(i);
        }

        int[] totals = new int[numStudents];
        int maxTotal = Integer.MIN_VALUE;
        String topperName = "";

        StringBuilder totalsSb = new StringBuilder("Totals ");
        for (int i = 0; i < numStudents; i++) {
            int sum = 0;
            for (int j = 0; j < numSubjects; j++) {
                sum += grid[i][j];
            }
            totals[i] = sum;
            if (sum > maxTotal) {
                maxTotal = sum;
                topperName = names.get(i);
            }
            totalsSb.append(names.get(i)).append(" ").append(sum);
            if (i < numStudents - 1) {
                totalsSb.append(", ");
            }
        }
        System.out.println(totalsSb.toString());

        double[] averages = new double[numSubjects];
        StringBuilder avgSb = new StringBuilder("averages ");
        for (int j = 0; j < numSubjects; j++) {
            double colSum = 0;
            for (int i = 0; i < numStudents; i++) {
                colSum += grid[i][j];
            }
            averages[j] = colSum / numStudents;
            avgSb.append(String.format(Locale.US, "%.2f", averages[j]));
            if (j < numSubjects - 1) {
                avgSb.append(", ");
            }
        }
        System.out.println(avgSb.toString());

        System.out.println("topper " + topperName + " (" + maxTotal + ")");
    }

    private static void parseAndAdd(String line, List<String> names, List<int[]> marksList) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }
        int openBracket = line.indexOf('[');
        int closeBracket = line.indexOf(']');
        if (openBracket != -1 && closeBracket != -1) {
            String name = line.substring(0, openBracket).trim();
            String numbersPart = line.substring(openBracket + 1, closeBracket);
            String[] tokens = numbersPart.split(",");
            int[] marks = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                marks[i] = Integer.parseInt(tokens[i].trim());
            }
            names.add(name);
            marksList.add(marks);
        }
    }

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        try {
            if (System.in.available() > 0) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    parseAndAdd(line, names, marksList);
                }
                scanner.close();
                analyze(names, marksList);
                return;
            }
        } catch (Exception ignored) {
        }

        String[] sample = {
            "Asha [78, 85, 90]",
            "Ravi [88, 92, 79]",
            "Neha [65, 70, 95]"
        };
        for (String line : sample) {
            parseAndAdd(line, names, marksList);
        }
        analyze(names, marksList);
    }
}
