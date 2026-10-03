public class ClassTopperFinder {

    public static class TopperResult {
        private final int rowIndex;
        private final int totalMarks;

        public TopperResult(int rowIndex, int totalMarks) {
            this.rowIndex = rowIndex;
            this.totalMarks = totalMarks;
        }

        public int getRowIndex() {
            return rowIndex;
        }

        public int getTotalMarks() {
            return totalMarks;
        }

        @Override
        public String toString() {
            return "(" + rowIndex + ", " + totalMarks + ")";
        }
    }

    public static TopperResult findTopper(int[][] marks) {
        if (marks == null || marks.length == 0) {
            return new TopperResult(-1, 0);
        }

        int bestIndex = 0;
        int maxTotal = Integer.MIN_VALUE;

        for (int i = 0; i < marks.length; i++) {
            int currentTotal = 0;
            for (int j = 0; j < marks[i].length; j++) {
                currentTotal += marks[i][j];
            }

            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                bestIndex = i;
            }
        }

        return new TopperResult(bestIndex, maxTotal);
    }

    public static void main(String[] args) {
        int[][] marks = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };

        TopperResult result = findTopper(marks);
        System.out.println(result);
    }
}
