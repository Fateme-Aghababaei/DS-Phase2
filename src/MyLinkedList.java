public class MyLinkedList {
    private class Node {
        private int value;
        private int columnIndex;
        private Node next;

        public Node(int value, int columnIndex, Node next) {
            this.value = value;
            this.columnIndex = columnIndex;
            this.next = next;
        }
    }

    public class Iterator {
        private Node node;

        private Iterator(Node node) {
            this.node = node;
        }

        public boolean hasNext() {
            if (node == null) return false;
            return node.next != null;
        }

        public Iterator next() {
            if (hasNext()) {
                node = node.next;
                return this;
            }
            node = null;
            return this;
        }

        public int getValue() {
            return node.value;
        }

        public boolean reachedEnd() {
            return node == null;
        }

        public int getColumnIndex() {
            return node.columnIndex;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        size = 0;
    }

    public Iterator getHead() {
        return new Iterator(head);
    }

    public int getSize() {
        return size;
    }

    public void addLast(int value, int columnIndex) {
        Node newNode = new Node(value, columnIndex, null);
        if (size == 0) {
            this.head = newNode;
        } else {
            tail.next = newNode;
        }
        this.tail = newNode;
        size++;
    } //O(1)

    public void addBetween(int value, int columnIndex) {
        Node n = head;
        Node newNode;
        if (size == 0) {
            addLast(value, columnIndex);
            return;
        }
        if (columnIndex < head.columnIndex) {
            newNode = new Node(value, columnIndex, n);
            head = newNode;
            size++;
            return;
        }
        if (columnIndex > tail.columnIndex) {
            newNode = new Node(value, columnIndex, null);
            tail.next = newNode;
            tail = newNode;
            size++;
            return;
        }
        for (int i = 0; i < size; i++) {
            if (columnIndex < n.next.columnIndex) {
                newNode = new Node(value, columnIndex, n.next);
                n.next = newNode;
                size++;
                return;
            }
            n = n.next;
        }
    } //O(n)

    public void remove(int columnIndex) {
        if (columnIndex == head.columnIndex) {
            head = head.next;
            size--;
            return;
        }
        Node n = head;
        for (int i = 1; i < size; i++) {
            if (columnIndex == n.next.columnIndex) {
                n.next = n.next.next;
                size--;
                if (i == size - 1) tail = n;
                return;
            }
            n = n.next;
        }
    } //O(n)

    public boolean contains(int value) {
        if (head == null) return false;
        if (value == head.value) return true;
        Node n = head;
        for (int i = 1; i < size; i++) {
            n = n.next;
            if (value == n.value) {
                return true;
            }
        }
        return false;
    } //O(n)

    public boolean changeValue(int col, int value) {
        if (head == null) return false;
        if (col == head.columnIndex) {
            head.value = value;
            return true;
        }
        Node n = head;
        for (int i = 1; i < size; i++) {
            n = n.next;
            if (col == n.columnIndex) {
                n.value = value;
                return true;
            }
        }
        return false;
    } //O(n)

    public int getValue(int col) {
        if (head == null) return 0;
        if (col == head.columnIndex) return head.value;
        Node n = head;
        for (int i = 1; i < size; i++) {
            n = n.next;
            if (col == n.columnIndex) return n.value;
        }
        return 0;
    } //O(n)

    public boolean isZero(int col) {
        if (head == null) return true;
        if (head.columnIndex == col) return false;
        Node n = head;
        while (n.next != null) {
            n = n.next;
            if (n.columnIndex == col) return false;
        }
        return true;
    } //O(n)
}
