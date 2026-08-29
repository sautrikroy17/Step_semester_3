public class CanteenItem {
    private String itemName;
    private int stock;

    public CanteenItem(String itemName, int stock) {
        this.itemName = itemName;
        this.stock = stock;
    }

    public void restock(int stock) {
        this.stock += stock;
    }

    public void printStock() {
        System.out.println(itemName + " | Final Stock: " + stock);
    }

    public static void main(String[] args) {
        CanteenItem[] items = {
            new CanteenItem("Samosa", 15),
            new CanteenItem("Tea Powder", 40),
            new CanteenItem("Bread", 8),
            new CanteenItem("Biscuit Packs", 25)
        };

        for (CanteenItem item : items) {
            item.restock(20);
            item.printStock();
        }
    }
}
