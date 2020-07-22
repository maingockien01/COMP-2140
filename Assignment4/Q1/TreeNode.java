public abstract class TreeNode<E> {
    public static TreeNode<?> createNode(NodeType nodeType) {
        if (nodeType == NodeType.NUMBER) {
            return new NumberNode ();
        } else if (nodeType == NodeType.VARIABLE) {
            return new VariableNode ();
        } else {
            return new OperatorNode ();
        }
    }

    public static TreeNode<?> createNode(String variable) {
        TreeNode variableNode = createNode(NodeType.VARIABLE);
        variableNode.setValue(variable);

        return variableNode;
    }

    public static TreeNode<?> createNode (char operator) {
        TreeNode operatorNode = createNode(NodeType.OPERATOR);
        operatorNode.setValue(operator);

        return operatorNode;
    } 

    public static TreeNode<?> createNode (int number) {
        TreeNode numberNode = createNode(NodeType.NUMBER);
        numberNode.setValue(number);

        return numberNode;
    }

    private TreeNode<?> parent;
    private TreeNode<?> leftChild;
    private TreeNode<?> rightChild;
    private E value;
    private NodeType nodeType;

    protected TreeNode (NodeType nodeType) {
        this.nodeType = nodeType;
        this.value = null;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public TreeNode<?> getParent() {
        return this.parent;
    }

    public TreeNode<?> getLeftChild() {
        return this.leftChild;
    }

    public TreeNode<?> getRightChild() {
        return this.rightChild;
    }

    public void setParent(TreeNode<?> parent) {
        this.parent = parent;
    } 

    public void setLeftChild (TreeNode<?> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild (TreeNode<?> rightChild) {
        this.rightChild = rightChild;
    }

    E getValue () {
        return this.value;
    }

    void setValue (E value) {
        this.value = value;
    }

    public NodeType getNodeType () {
        return this.nodeType;
    }

    public String toString () {
        return value.toString();
    }

}

enum NodeType {
    OPERATOR,
    VARIABLE,
    NUMBER;
}

class OperatorNode extends TreeNode<Character> {

    public OperatorNode () {
        super(NodeType.OPERATOR);
    }

}

class VariableNode extends TreeNode<String> {

    public VariableNode () {
        super(NodeType.VARIABLE);
    }
}

class NumberNode extends TreeNode<Integer> {

    public NumberNode () {
        super(NodeType.NUMBER);
    }
}
