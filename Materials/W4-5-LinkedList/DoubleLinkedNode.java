public abstract class DoubleLinkedNode<E> extends Node<E> {

    private DoubleLinkedNode<E> prev;

    public DoubleLinkedNode (E data, DoubleLinkedNode<E> next, DoubleLinkedNode<E> prev) {
        super(data, next);
        this.prev = prev;
    };

    public void setPrev (DoubleLinkedNode<E> prev) {
        this.prev = prev;
    }

    public DoubleLinkedNode<E> getPrev () {
        return this.prev;
    }

}
