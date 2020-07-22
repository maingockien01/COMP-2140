public class QueueImp<E> implements Queue<E> {
    private QueueNode<E> head;
    private QueueNode<E> tail;

    private int length;

    public QueueImp () {
        head = null;
        tail = head;

        length = 0;
    }

    public void push (QueueNode<E> node) {
        if (isEmpty()) {
            head = node;
            tail = head;
        } else {
            tail.setNext(node);
            tail = node;
        };

        length ++;
    }

    public QueueNode<E> pop () {
        if (isEmpty()) {
            return null;
        } else {
            QueueNode<E> node = head;
            if (getLength() == 1) {
                head = null;
                tail = head;
            } else {
                head = head.getNext();
            };

            length --;

            return node;
        }
    }

    public QueueNode<E> peek () {
        return head;
    }

    public boolean isEmpty () {
        return head == null;
    }

    public int getLength () {
        return this.length;
    }

}
