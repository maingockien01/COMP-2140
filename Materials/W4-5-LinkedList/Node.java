public abstract class Node<E> {
    private E data;
    private Node<E> next;

    public Node (E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

    public void setNext (Node<E> next) {
        this.next = next;
    }

    public Node<E> getNext () {
        return this.next;
    }

    public abstract boolean equals (Node<E> otherNode);

    public E getData() {
        return this.data;
    }
}
