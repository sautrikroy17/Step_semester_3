public class TrainCoachesBothWays {

    public static class Node {
        String name;
        Node prev;
        Node next;

        public Node(String name) {
            this.name = name;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;

    public TrainCoachesBothWays() {
        this.head = null;
        this.tail = null;
    }

    public void addLast(String name) {
        Node newNode = new Node(name);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public boolean remove(String name) {
        Node curr = head;
        while (curr != null) {
            if (curr.name.equals(name)) {
                if (curr == head && curr == tail) {
                    head = null;
                    tail = null;
                } else if (curr == head) {
                    head = head.next;
                    head.prev = null;
                } else if (curr == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                }
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public void printForward() {
        StringBuilder sb = new StringBuilder("Forward: ");
        Node curr = head;
        while (curr != null) {
            sb.append(curr.name);
            if (curr.next != null) {
                sb.append(" <-> ");
            }
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }

    public void printBackward() {
        StringBuilder sb = new StringBuilder("Backward: ");
        Node curr = tail;
        while (curr != null) {
            sb.append(curr.name);
            if (curr.prev != null) {
                sb.append(" <-> ");
            }
            curr = curr.prev;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        TrainCoachesBothWays train = new TrainCoachesBothWays();
        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        train.remove("B1");

        train.printForward();
        train.printBackward();
    }
}
