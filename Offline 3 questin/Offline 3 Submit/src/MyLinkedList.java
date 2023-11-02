// Author Md. Shahrukh Islam
// Buet 1805098
//Date Start 21.03.2021
class Node<T> {
    private T data;
    private Node<T> prev, next;

    public Node(T data, Node<T> prev, Node<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}

class MyLinkedList<T>{
    private int size=0;
    private Node<T> head=null;
    private Node<T> tail=null;

    public int size(){
        return size;

    }

    public boolean isEmpty(){
        return size()==0;
    }

    public void addLast(T element)
    {
        if (isEmpty())
            head=tail=new Node<T> (element, null, null);
        else {
            tail.setNext(new Node<T>(element, tail,null));
            tail=  tail.getNext();
        }
        size++;
    }
    public void add(T element)
    {
        addLast(element);
    }
    public void addFirst(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            head.setPrev(new Node<T>(elem, null, head));
            head = head.getPrev();
        }
        size++;
    }
    public T getFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.getData();
    }


    public T getLast() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return tail.getData();
    }
    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");

        T data = head.getData();
        head = head.getNext();
        --size;

        //Just a memory clean
        if (isEmpty()) tail = null;
        else head.setPrev(null);
        return data;
    }

    // Remove the last value at the tail of the linked list, O(1)
    public T removeLast() {
        if (isEmpty()) throw new RuntimeException("Empty list");

        T data = tail.getData();
        tail = tail.getPrev();
        --size;

        if (isEmpty()) head = null;
        else tail.setNext(null);  //Just a memory cleanup

        return data;
    }
}