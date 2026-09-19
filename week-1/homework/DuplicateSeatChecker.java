public class DuplicateSeatChecker {
    public static void checkDuplicateSeats(int[] seatNumbers) {
        if (seatNumbers == null || seatNumbers.length == 0) {
            System.out.println("No seat numbers provided.");
            return;
        }

        boolean duplicateFound = false;
        int[] reportedDuplicates = new int[seatNumbers.length];
        int reportedCount = 0;

        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    boolean alreadyReported = false;
                    for (int k = 0; k < reportedCount; k++) {
                        if (reportedDuplicates[k] == seatNumbers[i]) {
                            alreadyReported = true;
                            break;
                        }
                    }
                    if (!alreadyReported) {
                        System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                        reportedDuplicates[reportedCount++] = seatNumbers[i];
                        duplicateFound = true;
                    }
                }
            }
        }

        if (!duplicateFound) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        int[] case1 = {101, 102, 103, 102, 105};
        System.out.print("Testing case 1: ");
        checkDuplicateSeats(case1);

        int[] case2 = {101, 102, 103, 104, 105};
        System.out.print("Testing case 2: ");
        checkDuplicateSeats(case2);
    }
}
