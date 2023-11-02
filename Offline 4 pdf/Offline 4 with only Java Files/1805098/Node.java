public class Node<T> {
    T data;
    Node left, right;

    public Node(Node left, Node right, T elem) {
        this.data = elem;
        this.left = left;
        this.right = right;
    }
}