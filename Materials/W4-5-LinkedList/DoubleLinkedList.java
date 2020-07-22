public class DoubleLinkedList<E> {
    
    private DoubleLinkedNode<E> head;
    private DoubleLinkedNode<E> tail;

    public DoubleLinkedList () {
        head = null;
        tail = head;
    }

    public void append (DoubleLinkedNode<E> node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }
    }

    public void prepend (DoubleLinkedNode<E> node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head.setPrev(node);

            head = node;
        }
    }

    public DoubleLinkedNode<E> search (DoubleLinkedList<E> node) {
        DoubleLinkedNode<E> curNode = head;
        DoubleLinkedNode<E> foundNode = null;
        while (curNode != null) {
            if (curNode.equals(node)) {
                foundNode = curNode;
                break;
            };
            curNode = (DoubleLinkedNode<E>) curNode.getNext();
        }

        return foundNode;
    }

    public void remove (DoubleLinkedNode<E> node) {
        DoubleLinkedNode<E> curNode = head;
        while (curNode != null) {
            if (curNode.equals(node)) {
                DoubleLinkedNode<E> prev = node.getPrev();
                DoubleLinkedNode<E> next = (DoubleLinkedNode<E>) node.getNext();
                prev.setNext(next);
                next.setPrev(prev);
                break;
            };
            curNode = (DoubleLinkedNode<E>) curNode.getNext();
        };
   }
}
