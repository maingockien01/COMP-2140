public class StackNodeImp<E> implements StackNode<E> {
    private E data;
    private StackNode<E> next;
    private StackNode<E> prev;

    public StackNodeImp (E data, StackNode<E> next, StackNode<E> prev) {
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

    public void setNext (StackNode<E> next) {
        this.next = next;
    }

    public StackNode<E> getNext () {
        return this.next;
    }

    public void setPrev (StackNode<E> prev) {
        this.prev = prev;
    }

    public StackNode<E> getPrev () {
        return this.prev;
    }

}
