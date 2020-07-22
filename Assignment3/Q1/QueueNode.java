class QueueNode {
    private static QueueNode DUMMY_NODE = new QueueNode (Position.getDummyPos(), null);

    public static QueueNode getDummyNode () {
        return DUMMY_NODE;
    }

    private Position pos;

    private QueueNode next;

    public QueueNode (Position pos, QueueNode next) {
        this.pos = pos;
        this.next = next;
    }

    public void setPos (Position pos) {
        this.pos = pos;
    } 

    public Position getPos () {
        return this.pos;
    }

    public void setNext (QueueNode next) {
        this.next = next;
    }

    public QueueNode getNext () {
        return this.next;
    }

}
