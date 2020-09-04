public class que2 {
    static Node getMiddle(Node head) {
        if (head == null) {
            return head;
        }
        Node ptr1, ptr2;
        ptr1 = head;
        ptr2 = head;
        while (ptr2.link != null && ptr2.link.link != null) {
            ptr1 = ptr1.link;
            ptr2 = ptr2.link.link;
        }
        return ptr1;
    }

    static Node sort(Node head) {
        if (head == null || head.link == null) {
            return head;
        }
        Node mid = getMiddle(head);
        Node mid_next = mid.link;
        mid.link = null;
        Node first = sort(head);
        Node second = sort(mid_next);
        Node sorted = merge(first, second);
        return sorted;
    }

    static Node merge(Node first, Node second) {
        Node head = null;
        Node ptr = null;
        if (first == null) {
            return second;
        } else if (second == null) {
            return first;
        }
        if (first.data < second.data) {
            head = first;
            ptr = first;
            first = first.link;

        } else {
            head = second;
            ptr = second;
            second = second.link;
        }
        while (first != null && second != null) {
            if (first.data < second.data) {
                ptr.link = first;
                ptr = first;
                first = first.link;
            } else {
                ptr.link = second;
                ptr = second;
                second = second.link;
            }
        }
        while (first != null) {
            ptr.link = first;
            ptr = first;
            first = first.link;
        }
        while (second != null) {
            ptr.link = second;
            ptr = second;
            second = second.link;
        }
        ptr.link = null;
        return head;
    }

    public static void main(String[] args) {
        LL list = new LL();
        for (int i = 0; i < 5; i++) {
            list.insert();
        }
        list.insert(4);
        list.insert(1);
        list.insert(6);
        list.insert(3);
        list.insert(10);
        list.display();
        list.head = sort(list.head);
        list.display();
    }
}

class Node {
    int data;
    Node link;

    public Node(int x) {
        this.data = x;
        this.link = null;
    }

    public Node() {
        this.data = 0;
        this.link = null;
    }
}

class LL {
    Node head;

    public LL() {
        this.head = null;
    }

    void insert(int x) {
        Node new_node = new Node(x);
        if (this.head == null) {
            this.head = new_node;
        } else {
            Node last = this.head;
            while (last.link != null) {
                last = last.link;
            }
            last.link = new_node;
        }
    }

    void insert() {
        insert(0);
    }

    void display() {
        Node ptr = head;
        System.out.println("List: ");
        while (ptr != null) {
            System.out.print(Integer.toString(ptr.data) + " ");
            ptr = ptr.link;
        }
        System.out.println();
    }

    void find() {
        if (this.head == null) {
            return;
        }
        System.out.println("First element is: " + head.data);
        Node last = this.head;
        while (last.link != null) {
            last = last.link;
        }
        System.out.println("Last element is: " + Integer.toString(last.data));
    }
}