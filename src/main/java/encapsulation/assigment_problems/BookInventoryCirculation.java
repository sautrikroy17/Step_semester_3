package encapsulation.assigment_problems;

public class BookInventoryCirculation {
    private int copiesTotal;
    private int copiesAvailable;

    public BookInventoryCirculation(int copiesTotal) {
        if (copiesTotal < 0) {
            this.copiesTotal = 0;
            this.copiesAvailable = 0;
        } else {
            this.copiesTotal = copiesTotal;
            this.copiesAvailable = copiesTotal;
        }
    }

    public void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public static void main(String[] args) {
        BookInventoryCirculation b = new BookInventoryCirculation(3);
        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();
        System.out.println(b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();
        System.out.println(b.getCopiesAvailable());
    }
}
