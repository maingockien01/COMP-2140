public class StackImp<E> implements Stack<E> {
    private StackNode<E> head;
    private StackNode<E> tail;

    private int length;
    
    public StackImp () {
        head = null;
        tail = head;
        length = 0;
    }

    public void push(StackNode<E> node) {
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }

        length ++;
    }

    public StackNode<E> pop () {
        if (isEmpty()) {
            return null;
        } else {
            StackNode<E> poppedNode = tail;
            StackNode<E> prevTail = tail.getPrev();
            prevTail.setNext(null);
            tail = prevTail;

            length --;

            return poppedNode;
        }
    }

    public StackNode<E> peek () {
        return tail;
    }

    public boolean isEmpty () {
        return head == null;
    }

    public int getLength () {
        return this.length;
    }
}
    
