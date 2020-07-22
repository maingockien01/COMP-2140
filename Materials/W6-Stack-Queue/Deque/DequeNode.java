public interface DequeNode<E> {

    void setData (E data);
    E getData ();

    void setNext (DequeNode<E> next);
    DequeNode<E> getNext ();

    void setPrev (DequeNode<E> prev);
    DequeNode<E> getPrev ();

}
