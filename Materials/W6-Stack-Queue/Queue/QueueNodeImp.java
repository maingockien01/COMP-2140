public class QueueNodeImp<E> implements QueueNode<E> {
    private E data;
    private QueueNode<E> next;

    public QueueNodeImp (E data, QueueNode<E> next) {
        this.data = data;
        this.next = next;
    }

    public void setData (E data) {
        this.data = data;
    }

    public E getData () {
        return this.data;
    }

    public void setNext (QueueNode<E> node) {
        this.next = node;
    }

    public QueueNode<E> getNext () {
        return this.next;
    }

}
