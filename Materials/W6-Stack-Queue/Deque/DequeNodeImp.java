public class DequeNodeImp<E> implements DequeNode<E> {
    private E data;

    private DequeNode<E> next;
    private DequeNode<E> prev;

    public DequeNodeImp (E data, DequeNode<E> next, DequeNode<E> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public void setData (E data) {
        this.data = data;
    }

    public E getData () {
        return this.data;
    }

    public void setNext (DequeNode<E> node) {
        this.next = next;
    }

    public DequeNode<E> getNext() {
        return this.next;
    }

    public void setPrev (DequeNode<E> prev) {
        this.prev = prev;
    } 

    public DequeNode<E> getPrev () {
        return this.prev;
    }
}
