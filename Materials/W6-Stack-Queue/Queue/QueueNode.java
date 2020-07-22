public interface QueueNode<E> {

    public void setData (E data);
    public E getData ();

    public void setNext (QueueNode<E> next);
    public QueueNode<E> getNext ();

}
