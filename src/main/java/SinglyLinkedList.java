public class SinglyLinkedList<T> {

    // Inner Node class.
    public class Node<T> {
        // Properties of the Node class.
        // The two properties should be:
        // 1. data (the data stored in the node).
        // 2. next (a reference (also known as a pointer) to the next node.
        private T data;
        private Node next;

        // Constructor of the Node class.
        // The constructor should set the data property of the Node to be the value passed as a parameter.
        // The constructor should set the next property of the Node to be null.
        public Node(T data){
            this.data = data;
            next = null;
        }

    }

    // Properties of the Singly Linked List class.
    // The three properties should be:
    // 1. size (records the number of nodes in our Singly Linked List)
    // 2. head (a reference to the first (also known as the head) node in our Singly Linked List).
    // 3. tail (a reference to the last (also known as the tail) node in our Singly Linked List.
    private int size;
    private Node head;
    private Node tail;

    // Constructor.
    // Creates a Singly Linked List with a head node.
    public SinglyLinkedList(T value) {
        Node node = new Node(value);
        head = node;
        tail = node;
        size = 1;
    }

    // Methods

    // size
    // returns the size of the Singly Linked List.
    public int size() {
        return this.size;

    }

    // isEmpty
    // returns whether the Singly Linked List is empty.
    public boolean isEmpty() {
        if(size==0) return true;
        return false;

    }

    // peekFirst
    // returns the data stored in the head node.
    // throws a run time exception if the Singly Linked List is empty.
    public T peekFirst() {
        if(isEmpty()){
            throw new RuntimeException();
        }else{
            return (T) head.data;
        }
    }

    // peekLast
    // returns the data stored in the tail node.
    // throws a run time exception if the Singly Linked List is empty.
    public T peekLast() {
        if(isEmpty()){
            throw new RuntimeException();
        }else{
            return (T) tail.data;
        }
    }

    // addFirst
    // Adds a node to the front of the Singly Linked List.
    // If the Singly Linked List is empty,
    public void addFirst(T value) {
        Node n = new Node(value);
        if(isEmpty()){
            head = n;
            tail = n;
        }else{
            n.next = head;
            head = n;
        }
        size++;
    }

    // addLast
    // Adds a node to the back of the Singly Linked List.
    public void addLast(T value) {
        Node n = new Node(value);
        if(size==0){
            addFirst(value);
        }else{
            tail.next = n;
            tail = n;
            size++;
        }

    }

    // insertAt
    // Inserts a node at a specific index.
    // If the index is equal to 0, then we can invoke the addFirst method.
    // If the index is equal to size, then we can invoke the addLast method.
    // throws an illegal argument exception if the index is invalid.
    public void insert(T value, int index) {
        if(index == 0){
            addFirst(value);
        }else if(index == size){
            addLast(value);
        }else if(index < 0 || index > size){
            throw new IllegalArgumentException();
        }else{
            Node curNode = head;
            Node n = new Node(value);
            for (int i = 1; i < index; i++){
                curNode = curNode.next;
            }
            n.next = curNode.next;
            System.out.println("curNode.next = " + curNode.next);
            curNode.next = n;
            size++;

        }

        System.out.println("insertion");
        Node curNode = head;
        Node n = new Node(value);
        while(curNode.next!=null){
            System.out.println(curNode.data);
            curNode = curNode.next;
        }
    }

    // removeFirst
    // Removes the first (also known as the head node) from the Singly Linked List.
    // Throws a run time exception if the Singly Linked List is empty.
    // Returns the data stored in the head node.
    // If the size of the Singly Linked List becomes 0, need to set the tail to null.
    public T removeFirst() {
        T r;
        if(isEmpty()){
            throw new RuntimeException();
        }else{
            r = (T) head.data;
            head = head.next;
        }

        size--;
        if(size==0) tail = null;
        return r;
    }

    // removeLast
    // Removes the last (also known as the tail node) from the Singly Linked List.
    // Throws a run time exception if the Singly Linked List is empty.
    // Returns the data stored in the tail node.
    // If the size of the Singly Linked List becomes 0, need to set the tail to null.
    public T removeLast() {
        T r = null;
        if(isEmpty()){
            throw new RuntimeException();
        }else{
            if(size==1){
                removeFirst();
            }else{
                r = (T) tail.data;
                Node nl = head;
                while(nl.next!=tail){
                    nl = nl.next;
                }
                tail = nl;
                nl.next = null;
                size--;
            }
        }
        return r;

    }

    // removeAt
    // Removes a node at a specific index.
    // Returns the data stored in the removed node.
    // If the index is equal to 0, then we can invoke the removeFirst method.
    // If the index is equal to size-1, then we can invoke the removeLast method.
    // throws an illegal argument exception if the index is invalid.

    public T removeAt(int index) {
        T r = null;
        if(index==0){
            removeFirst();
        }else if(index==size-1){
            removeLast();
        }else if(index<0 || index>=size){
            throw new IllegalArgumentException();
        }else{
            Node curNode = head;
            for (int i = 1; i < index-1; i++){
                curNode = curNode.next;
            }
            r = (T) curNode.next.data;
            curNode.next = curNode.next.next;
            size--;
        }

        return r;

    }

    // contains
    // Determines whether the Singly Linked List contains a node that holds data equivalent to the value passed.
    // Returns a boolean.
    public boolean contains(T value) {
        Node curNode = head;
        System.out.println("data: "+ curNode.data);
        System.out.println("size: "+ size);
        for (int i = 1; i <= size; i++){

            if(curNode.data == value) return true;
            curNode = curNode.next;
        }
        return false;

    }

    // valueAt
    // Returns the data held in the node at a specified index.
    // Throws an illegal argument exception if the index is invalid.
    public T valueAt(int index) {
        Node curNode = head;
        if(index<0 || index >= size){
            throw new IllegalArgumentException();
        }else{

            for (int i = 0; i < index; i++){
                curNode = curNode.next;
            }
        }
        return (T) curNode.data;

    }

    // reverse
    // Reverses the Singly Linked List.
    public void reverse() {
        if(size<=1) return;
        Node prev = null;
        Node curr = head;
        Node next = null;
        while(curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        tail = head;
        head = prev;
    }

    // toString
    // Returns a String representation of the Singly Linked List.
    public String toString() {
        String s = "";
        Node curNode = head;
        s += curNode.data;
        while(curNode.next!=null){
            s+= (" -> " + curNode.next.data);
            curNode = curNode.next;
        }
        s+=" -> null";
        return s;

    }

}
