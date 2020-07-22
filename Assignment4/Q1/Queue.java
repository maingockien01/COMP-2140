interface Queue {
    boolean isEmpty ();
    boolean queue (TreeNode<?> toAdd);
    TreeNode<?> dequeue ();
    TreeNode<?> peek ();
}

interface QueueNode {
    void setNext (QueueNode next);
    void setPrev (QueueNode prev);
    void setTreeNode (TreeNode<?> treeNode);

    QueueNode getNext ();
    QueueNode getPrev ();
    TreeNode<?> getTreeNode ();
}

class QueueImp implements Queue {
    QueueNode front;
    QueueNode end;

    public QueueImp () {
        front = null;
        end = null;
    }

    @Override
    public boolean isEmpty () {

        return front == null;
    }

    @Override
    public boolean queue (TreeNode<?> toAdd) {
        if (toAdd == null) {
            return false;
        };

        QueueNode node = new QueueNodeImp(toAdd);

        if (isEmpty()) {
            front = node;
            end = node;
            return true;
        };

        node.setNext(front);
        front.setPrev(node);
        
        front = node;
            
        return true;
    }

    @Override
    public TreeNode<?> dequeue () {
        if (isEmpty()) {
            return null;
        };

        if (front == end) {
            QueueNode returnNode = front;
            front = null;
            end = null;
            return returnNode.getTreeNode();
        };

        QueueNode returnNode = end;
        end = returnNode.getPrev();
        end.setNext(null);

        return returnNode.getTreeNode();
    }

    @Override
    public TreeNode<?> peek () {

        return end.getTreeNode();
    }


}

class QueueNodeImp implements QueueNode {
    private QueueNode next;
    private QueueNode prev;

    private TreeNode<?> treeNode;

    public QueueNodeImp () {
    }

    public QueueNodeImp (TreeNode<?> treeNode) {
        this.treeNode = treeNode;
    }

    @Override
    public void setNext (QueueNode next) {
        this.next = next;
    }

    @Override
    public void setPrev (QueueNode prev) {
        this.prev = prev;
    }

    @Override
    public void setTreeNode(TreeNode<?> treeNode) {
        this.treeNode = treeNode;
    }

    @Override
    public QueueNode getNext () {

        return this.next;
    }

    @Override
    public QueueNode getPrev () {

        return this.prev;
    }

    @Override
    public TreeNode<?> getTreeNode () {

        return this.treeNode;
    }
}
