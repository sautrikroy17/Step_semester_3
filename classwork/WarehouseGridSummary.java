public class WarehouseGridSummary {

    public static class Coordinate {
        private final int row;
        private final int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }

    public static class SummaryResult {
        private final int totalItems;
        private final Coordinate maxCoordinate;

        public SummaryResult(int totalItems, Coordinate maxCoordinate) {
            this.totalItems = totalItems;
            this.maxCoordinate = maxCoordinate;
        }

        public int getTotalItems() {
            return totalItems;
        }

        public Coordinate getMaxCoordinate() {
            return maxCoordinate;
        }

        @Override
        public String toString() {
            return "(" + totalItems + ", " + maxCoordinate + ")";
        }
    }

    public static SummaryResult warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new SummaryResult(0, new Coordinate(-1, -1));
        }

        int total = 0;
        int maxVal = Integer.MIN_VALUE;
        int bestRow = 0;
        int bestCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int val = grid[r][c];
                total += val;
                if (val > maxVal) {
                    maxVal = val;
                    bestRow = r;
                    bestCol = c;
                }
            }
        }

        return new SummaryResult(total, new Coordinate(bestRow, bestCol));
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        SummaryResult result = warehouseSummary(grid);
        System.out.println(result);
    }
}
