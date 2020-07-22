public interface Deque<E> {

    void pushFront (DequeNode<E> node);
    void pushBack (DequeNode<E> node);
    DequeNode<E> popFront ();
    DequeNode<E> popBack ();
    DequeNode<E> peekFront ();
    DequeNode<E> peekBack ();
    boolean isEmpty ();
    int getLength();

}
    
