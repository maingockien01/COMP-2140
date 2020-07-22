public class LinkedList<E> {

    private Node<E> head;

    public LinkedList () {
        head = null;
    }

    public void append (Node<E> node) {
        if (head == null) {
            head = node;
        } else {
            head.setNext(node);
        }
    }

    public void prepend (Node<E> node) {
        if (head == null) {
            head = node;
        } else {
            node.setNext(head);
            head = node;
        }
    }

    public Node<E> search (Node<E> node) {
        Node<E> curNode = this.head;
        Node<E> foundNode = null;
        while (curNode != null) {
            if (node.equals(curNode)) {
                foundNode = curNode;
            }; 
            curNode = curNode.getNext();
        };
        return foundNode;
    }

    public void remove (Node<E> node) {
        Node<E> prevNode = null;
        Node<E> curNode = head;

        while (curNode != null) {
            if (curNode.equals(node)){
                remove(prevNode, curNode);
                return;
            };
            prevNode = curNode;
            curNode = curNode.getNext();

        };

    }

    private void remove (Node<E> prevNode, Node<E> node) {
        if (prevNode == null) { 
            //Node is head
            head = node.getNext();
        } else {
            prevNode.setNext(node.getNext());
        };
    }

}
