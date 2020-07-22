public interface Queue<E> {

    public void push (QueueNode<E> node);
    public QueueNode<E> pop ();
    public QueueNode<E> peek ();
    public boolean isEmpty ();
    public int getLength ();

}
