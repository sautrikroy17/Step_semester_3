public class RoundRobinGameTurns {

    public static class Node {
        String name;
        Node next;

        public Node(String name) {
            this.name = name;
            this.next = null;
        }
    }

    private Node tail;
    private int size;

    public RoundRobinGameTurns() {
        this.tail = null;
        this.size = 0;
    }

    public void addLast(String name) {
        Node newNode = new Node(name);
        if (tail == null) {
            newNode.next = newNode;
            tail = newNode;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void printTurns(int turns) {
        if (tail == null || turns <= 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        Node curr = tail.next;
        for (int i = 0; i < turns; i++) {
            sb.append(curr.name);
            if (i < turns - 1) {
                sb.append(" ");
            }
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        RoundRobinGameTurns game = new RoundRobinGameTurns();
        game.addLast("Asha");
        game.addLast("Ravi");
        game.addLast("Neha");

        game.printTurns(7);
    }
}
