class Queue {
    private QueueNode front;
    private int length;

    public Queue () {
        front = QueueNode.getDummyNode();

        length = 0;
    }

    public void enqueue (QueueNode node) {
        QueueNode curr = front;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        };

        curr.setNext(node);

        length ++;
    }

    public QueueNode dequeue () {
        if (isEmpty()) {
            return null;
        };
        QueueNode node = front.getNext();
        front.setNext(node.getNext());

        length --;

        return node;
    }

    public void enqueue (Position pos) {
        QueueNode node = new QueueNode(pos, null);
        enqueue(node);
    } 

    public Position dequeuePos () {
        QueueNode node = dequeue();
        if(node != null) {
            return node.getPos();
        } else {
            return null;
        }
    }

    public Position frontPos () {
        QueueNode node = front();
        if (node == null) {
            return null;
        } else {
            return node.getPos();
        }
    }

    public QueueNode front () {
        return front.getNext();
    }

    public boolean isEmpty () {
        return front.getNext() == null || length == 0;
    }

    public int getLength () {
        return this.length;
    }

}
