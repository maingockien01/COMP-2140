public interface Stack<E> {
    public void push(StackNode<E> node);
    public StackNode<E> pop();
    public StackNode<E> peek();
    public boolean isEmpty();
    public int getLength();

}
