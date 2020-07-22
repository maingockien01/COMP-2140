public interface Tree {
    void newTree (Stack stack); //From postfix
    void newTree (Queue queue); //From prefix
    String printPrefix ();
    String printInfix ();
    String printPostfix ();
    void simplify ();
}

class ExpressionTree implements Tree { 
    private TreeNode<?> root;

    public ExpressionTree () {
        root = null;
    }

    public ExpressionTree (TreeNode<?> root) {
        this.root = root;
    }

    private boolean isEmpty () {
        return root == null;
    }

    @Override
    public void newTree (Stack stack) {
        if (stack.isEmpty()) {
            return;
        };
        root = stack.pop();
        root = newTree(stack, root);
    }

    private TreeNode<?> newTree (Stack stack, TreeNode<?> root) {
        if (stack.isEmpty()) {
            return root;
        };

        TreeNode<?> rightNode = stack.pop();

        if (rightNode.getNodeType() == NodeType.OPERATOR) {
            rightNode = newTree(stack, rightNode);
            root.setRightChild(rightNode);
        } else {
            root.setRightChild(rightNode);
        };

        TreeNode<?> leftNode = stack.pop();

        if (leftNode.getNodeType() == NodeType.OPERATOR) {
            leftNode = newTree (stack, leftNode);
            root.setLeftChild(leftNode);
        } else {
            root.setLeftChild(leftNode);
        };

        return root;
    }

    @Override
    public void newTree (Queue queue) {
        if (queue.isEmpty()) {
            return;
        };
        root = queue.dequeue();
        root = newTree(queue, root);
    }

    private TreeNode<?> newTree (Queue queue, TreeNode<?> root) {
        if (queue.isEmpty()) {
            return root;
        };

        TreeNode<?> leftNode = queue.dequeue();

        if (leftNode.getNodeType() == NodeType.OPERATOR) {
            leftNode = newTree (queue, leftNode);
            root.setLeftChild(leftNode);
        } else {
            root.setLeftChild(leftNode);
        };

        TreeNode<?> rightNode = queue.dequeue();

        if (rightNode.getNodeType() == NodeType.OPERATOR) {
            rightNode = newTree(queue, rightNode);
            root.setRightChild(rightNode);
        } else {
            root.setRightChild(rightNode);
        };

        return root;
    }
 

    @Override
    public String printPrefix () {
        if (isEmpty()) {
            return null;
        };
        return prefixTraversal(root);
    }

    private String prefixTraversal (TreeNode<?> node) {
        String leftSubtree;
        String current;
        String rightSubtree;

        StringBuilder builder = new StringBuilder();

        current = node.toString();
        builder.append(current);

        if (node.getLeftChild() != null) {
            leftSubtree = prefixTraversal(node.getLeftChild());

            builder.append(" ");
            builder.append(leftSubtree);
        };


        if (node.getRightChild() != null) {
            rightSubtree = prefixTraversal(node.getRightChild());
            builder.append(" ");
            builder.append(rightSubtree);

        }

        return builder.toString();
    }

    @Override
    public String printInfix () {
        if (isEmpty()) {
            return null;
        };
        return inorderTraversal(root);
    }

    private String inorderTraversal (TreeNode<?> node) {
        String leftSubtree;
        String current;
        String rightSubtree;

        StringBuilder builder = new StringBuilder();

        if (node.getLeftChild() != null) {
            leftSubtree = inorderTraversal(node.getLeftChild());

            builder.append("(");
            builder.append(leftSubtree);
            builder.append(" ");
        };

        current = node.toString();
        builder.append(current);

        if (node.getRightChild() != null) {
            rightSubtree = inorderTraversal(node.getRightChild());

            builder.append(" ");
            builder.append(rightSubtree);
            builder.append(")");
        }

        return builder.toString();
 
    }

    @Override
    public String printPostfix () {
        if(isEmpty()) {
            return null;
        };

        return postfixTraversal(root);
    }

    private String postfixTraversal (TreeNode<?> node) {
        String leftSubtree;
        String current;
        String rightSubtree;

        StringBuilder builder = new StringBuilder();

        if (node.getLeftChild() != null) {
            leftSubtree = postfixTraversal(node.getLeftChild());

            builder.append(leftSubtree);
            builder.append(" ");
        };

        if (node.getRightChild() != null) {
            rightSubtree = postfixTraversal(node.getRightChild());

            builder.append(rightSubtree);
            builder.append(" ");
        }

        current = node.toString();
        builder.append(current);

        return builder.toString();
 
    }

    @Override
    public void simplify () {
        this.root = simplify(this.root);
    }

    private TreeNode simplify (TreeNode root) {
        if (!(root instanceof OperatorNode)) {
            return root;
        };

        TreeNode leftChild = root.getLeftChild();
        TreeNode rightChild = root.getRightChild();

        if (leftChild != null) {
            leftChild = simplify(leftChild);
            root.setLeftChild(leftChild);
        };

        if (rightChild != null) {
            rightChild = simplify(rightChild);
            root.setRightChild(rightChild);
        };

        if (leftChild instanceof NumberNode && rightChild instanceof NumberNode) {
            NumberNode leftNumNode = (NumberNode) leftChild;
            NumberNode rightNumNode = (NumberNode) rightChild;
            OperatorNode operatorNode = (OperatorNode) root;

            TreeNode newRoot = calculateNodes(operatorNode, leftNumNode, rightNumNode);

            return newRoot;
        }

        return root;

    }

    private TreeNode calculateNodes (OperatorNode operatorNode, NumberNode leftNumberNode, NumberNode rightNumberNode) {
        int leftNum = leftNumberNode.getValue();
        int rightNum = rightNumberNode.getValue();
        char operator = operatorNode.getValue();

        int newNum = 0;

        switch (operator) {
            case '+':
                newNum = leftNum + rightNum;
                break;
            case '-':
                newNum = leftNum - rightNum;
                break;
            case '*':
                newNum = leftNum * rightNum;
                break;
            case '/':
                newNum = leftNum / rightNum;
                break;
            default:
                System.out.println("Invalid operator!");
                return null;
        }
        TreeNode newNode = TreeNode.createNode(NodeType.NUMBER);
        newNode.setValue(newNum);

        return newNode;
    }
}
