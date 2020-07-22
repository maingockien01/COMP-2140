public class DequeImp<E> implements Deque<E> {
    private DequeNode<E> head;
    private DequeNode<E> tail;

    private int length;

    public DequeImp () {
        head = null;
        tail = head;

        length = 0;
    }

    public void pushFront (DequeNode<E> node) {
       if (isEmpty()) {
          head = node;
          tail = node;
       } else {
           node.setNext(head);
           head.setPrev(node);
           head = node;
       }

       length ++;
    }

    public void pushBack (DequeNode<E> node) {
        if (isEmpty()) {
            tail = node;
            head = tail;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        };
        length ++;
    }

    public DequeNode<E> popFront () {
        if (isEmpty()){
            return null;
        } else {
            DequeNode<E> node = head;
            if (getLength() == 1) {
                head = null;
                tail = head;
            } else {
                head.getNext().setPrev(null);
                head = head.getNext();
            };

            length --;

            return node;
        }
    }

    public DequeNode<E> popBack () {
        if (isEmpty()) {
            return null;
        } else {
            DequeNode<E> node = tail;
            if (getLength() == 1) {
                tail = null;
                head = tail;
            } else {
                tail.getPrev().setNext(null);
                tail = tail.getPrev();
            };

            length --;

            return node;
        }
    }

    public DequeNode<E> peekFront () {
        return head;
    }

    public DequeNode<E> peekBack () {
        return tail;
    }

    public boolean isEmpty () {
        return head == null;
    }

    public int getLength () {
        return this.length;
    }

}
