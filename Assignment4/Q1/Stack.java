interface Stack { 
    boolean isEmpty ();
    boolean push (TreeNode<?> toAdd);
    TreeNode<?> pop ();
    TreeNode<?> peek ();
}

interface StackNode {
    void setNext (StackNode next);
    StackNode getNext ();
    void setTreeNode (TreeNode<?> treeNode);
    TreeNode<?> getTreeNode ();
}

class StackImp implements Stack {
    private StackNode top;

    public StackImp () {
        top = StackNodeImp.getDummyNode();

    }

    @Override
    public boolean isEmpty () {
        
        return top.getNext() == null;
    }

    @Override
    public boolean push (TreeNode<?> toAdd) {
        StackNode node = new StackNodeImp();
        node.setTreeNode(toAdd);

        node.setNext(top.getNext());
        top.setNext(node);

        return true;
    }

    @Override
    public TreeNode<?> pop () {
        if (isEmpty()) {
            return null;
        }
        StackNode node = top.getNext();
        top.setNext(node.getNext());

        return node.getTreeNode();
    }

    @Override
    public TreeNode<?> peek () {
        if (isEmpty()) {
            return null;
        }
        return top.getNext().getTreeNode();
    }

}

class StackNodeImp implements StackNode {
    private static StackNode dummyNode;
    
    private StackNode next;
    private TreeNode<?> treeNode;

    public static StackNode getDummyNode () {
        if (dummyNode == null) {
            dummyNode = new StackNodeImp();
            dummyNode.setNext(null);
        };

        return dummyNode;
    }

    public StackNodeImp (TreeNode<?> treeNode) {
        this.treeNode = treeNode;
    }

    public StackNodeImp () {
    }

    public void setNext (StackNode next) {
        this.next = next;

    }

    public StackNode getNext () {

        return this.next;
    }

    public void setTreeNode (TreeNode<?> treeNode) {
        this.treeNode = treeNode;

    }

    public TreeNode<?> getTreeNode () {

        return this.treeNode;
    }

}
