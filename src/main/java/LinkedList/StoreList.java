package LinkedList;

public class StoreList<T> { //Generic clas so can store any type e.g Item, Floorarea etc.

    public Node<T> head;  //reference to first node
    public int size = 0;  //total size of linked list

    // ---------- Add ----------
    public void add(T data) {
        Node<T> newNode = new Node<>(data);  //creates newnode.

        if (head == null) {
            head = newNode;   //if there are no nodes in the list the node created is the first node in the list.
        } else {
            Node<T> current = head;     //otherwise starts at the first node in the list
            while (current.next != null) {  //while the next node is not null keeps looping
                current = current.next;  // node --> node ---> node
            }
            current.next = newNode;  //when the loop detects that the next node is null sets the current.next --> null to newNode
        }

        size++; //increases total size of the linked list.
    }

    public T remove(int index) {
        if (index < 0 || index >= size) return null;  //if index is less than 0 or more than size its invalid therefore returns null

        // Removing the first node (head)
        if (index == 0) {
            T data = head.data; //saves the data from the first node
            head = head.next;  //moves the data from first node to the next node
            size--;  //size is decreased for the missing/removed node.
            return data;
        }

        // Removing any other node
        Node<T> current = head;

        // Move to the node right before the one to remove
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        T data = current.next.data;       // save removed data
        current.next = current.next.next; // unlink the node
        size--;

        return data;
    }

    // ---------- Get ----------
    public T get(int index) {
        if (index < 0 || index >= size) return null; //returns null if the index is outside of bounds.

        Node<T> current = head;  //current referencing the first node.

        for (int i = 0; i < index; i++) { //Loops until the index is reached
            current = current.next;
        }

        return current.data; //returns data on the node which the loop has stopped on.
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

}
