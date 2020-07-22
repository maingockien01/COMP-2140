public interface StackNode<E> {
    public void setData(E data);
    public E getData();
    
    public void setNext(StackNode<E> node);
    public StackNode<E> getNext();

    public void setPrev(StackNode<E> node);
    public StackNode<E> getPrev();
}
