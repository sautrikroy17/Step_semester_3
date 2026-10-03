import java.util.HashMap;
import java.util.Map;

public class MostPopularCanteenOrder {

    public static class PopularItemResult {
        private final String item;
        private final int count;

        public PopularItemResult(String item, int count) {
            this.item = item;
            this.count = count;
        }

        public String getItem() {
            return item;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "(\"" + item + "\", " + count + ")";
        }
    }

    public static PopularItemResult mostPopular(String[] orders) {
        if (orders == null || orders.length == 0) {
            return new PopularItemResult("", 0);
        }

        Map<String, Integer> counts = new HashMap<>();
        for (String order : orders) {
            counts.put(order, counts.getOrDefault(order, 0) + 1);
        }

        int maxCount = 0;
        for (int c : counts.values()) {
            if (c > maxCount) {
                maxCount = c;
            }
        }

        for (String order : orders) {
            if (counts.get(order) == maxCount) {
                return new PopularItemResult(order, maxCount);
            }
        }

        return new PopularItemResult(orders[0], maxCount);
    }

    public static void main(String[] args) {
        String[] sample1 = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        System.out.println(mostPopular(sample1));

        String[] sample2 = {"tea", "coffee", "coffee", "tea"};
        System.out.println(mostPopular(sample2));
    }
}
