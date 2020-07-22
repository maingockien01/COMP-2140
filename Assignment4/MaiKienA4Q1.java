import java.util.*;
import java.io.*;

//==========================================================
// MaiKienA4Q1
//
// COMP 2140 SECTION D01
// ASSIGNMENT    Assignment 4, question 1
// @author       Kien Mai - 7876083
// @version      July 15
// PURPOSE: Applying expresstion tree to represent equations.
//          Construct expression trees from prefix and 
//          postfix equation. Then, simplify and print the
//          tree in prefix, postfix or infix ways.
//==========================================================

/* 
* My work used generic as the way to create three type of tree nodes 
* while they have same parent class. 
* The generic complier would notify some unchecked warnings
* but the code would definitely be working fine in run time.
**/

public class MaiKienA4Q1 {
    public static final String COMMAND_COMMENT = "COMMENT";
    public static final String COMMAND_NEW = "NEW";
    public static final String COMMAND_PRINTINFIX = "PRINTINFIX";
    public static final String COMMAND_SIMPLIFY = "SIMPLIFY";
    public static final String COMMAND_PRINTPOSTFIX = "PRINTPOSTFIX";
    public static final String COMMAND_PRINTPREFIX = "PRINTPREFIX";

    @SuppressWarnings("unchecked")
    public static void main (String[] args) {

        //Starting
        Tree tree = new ExpressionTree();
        String fileName;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please provide file input name: ...");
        fileName = keyboard.nextLine();

        try {
            //Load file
            Scanner fileScanner = loadFileScanner(fileName);
            //Load command
            String line = fileScanner.nextLine();
            while (line!= null) {
                loadCommand (line, tree);
                
                if (fileScanner.hasNextLine()) {
                    line = fileScanner.nextLine();
                } else {
                    break;
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file input!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static Scanner loadFileScanner (String fileName) throws FileNotFoundException, IOException {
            File file = new File(fileName);

            Scanner fileScanner = new Scanner(file);

            return fileScanner;
    } 

    private static void loadCommand (String line, Tree tree) {
        String command = scanCommand (line);
        switch (command) {
            case COMMAND_COMMENT:
                loadCommandComment(line);
                break;
            case COMMAND_NEW:
                System.out.println("New tree constructed");
                loadCommandNew(line, tree);
                break;
            case COMMAND_PRINTPREFIX:
                loadCommandPrintprefix(tree);
                break;
            case COMMAND_PRINTINFIX:
                loadCommandPrintinfix(tree);
                break;
            case COMMAND_PRINTPOSTFIX:
                loadCommandPrintpostfix(tree);
                break;
            case COMMAND_SIMPLIFY:
                System.out.println("Tree simplified");
                loadCommandSimplify(tree);
                break;
            default:
                System.out.println(command + " - Unvalid command!");
                break;
        };
    }

    private static String scanCommand (String line) {
        Scanner lineScanner = new Scanner (line);

        String command = lineScanner.next();

        lineScanner.close();

        return command;
    }

    private static void loadCommandNew (String line, Tree tree) {
        String[] nodeStrings = line.substring(COMMAND_NEW.length() + 1).split(" ");
        String firstNode = nodeStrings[0];

        boolean isPrefix = isOperator(firstNode);

        if (isPrefix) {
            //CreateQueue
            Queue queue = createQueue(nodeStrings);
            //CreateTree
            tree.newTree(queue);
        } else {
            //CreateStack
            Stack stack = createStack(nodeStrings);
            //CreateTree
            tree.newTree(stack);
        }
    }

    private static Stack createStack (String[] nodeStrings) {
        Stack stack = new StackImp();
        for (int i = 0; i < nodeStrings.length; i ++) {
            String nodeString = nodeStrings[i];
            TreeNode treeNode = createTreeNode(nodeString);
            stack.push(treeNode);
        }

        return stack;
    }

    private static Queue createQueue (String[] nodeStrings) {
        Queue queue = new QueueImp();
        for (int i = 0; i < nodeStrings.length; i ++) {
            String nodeString = nodeStrings[i];
            TreeNode treeNode = createTreeNode(nodeString);
            queue.queue(treeNode);
        }

        return queue;
 
    }

    private static TreeNode createTreeNode (String nodeString) {
        if (isOperator(nodeString)) {
            char operatorChar = nodeString.charAt(0);
            return TreeNode.createNode(operatorChar);
        };

        try {
            int number = Integer.parseInt(nodeString);

            return TreeNode.createNode(number);
        } catch (NumberFormatException e) {
            return TreeNode.createNode(nodeString);
        }
    }

    private static boolean isOperator (String nodeString) {
        return nodeString.equals("+") || nodeString.equals("-") || nodeString.equals("*") || nodeString.equals("/");
    }

    private static void loadCommandComment (String line) {
        System.out.println(line.substring(COMMAND_COMMENT.length() + 1));

    }

    private static void loadCommandPrintprefix (Tree tree) {
        System.out.println(tree.printPrefix());
    }

    private static void loadCommandPrintinfix (Tree tree) {
        System.out.println(tree.printInfix());

    }

    private static void loadCommandPrintpostfix (Tree tree) {
        System.out.println(tree.printPostfix());
    }

    private static void loadCommandSimplify (Tree tree) {
        tree.simplify();
    }


} //End class MaiKienA4Q1

interface Tree {
    void newTree (Stack stack); //From postfix
    void newTree (Queue queue); //From prefix
    String printPrefix ();
    String printInfix ();
    String printPostfix ();
    void simplify ();
} //End interface Tree

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
} //End class ExpressionTree

abstract class TreeNode<E> {
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

} //End abstract class TreeNode

enum NodeType {
    OPERATOR,
    VARIABLE,
    NUMBER;
} //End enum NodeType

class OperatorNode extends TreeNode<Character> {

    public OperatorNode () {
        super(NodeType.OPERATOR);
    }

} //End class OperatorNode

class VariableNode extends TreeNode<String> {

    public VariableNode () {
        super(NodeType.VARIABLE);
    }
} //End class VariableNode

class NumberNode extends TreeNode<Integer> {

    public NumberNode () {
        super(NodeType.NUMBER);
    }
} //End class NumberNode

interface Queue {
    boolean isEmpty ();
    boolean queue (TreeNode<?> toAdd);
    TreeNode<?> dequeue ();
    TreeNode<?> peek ();
} //End interface Queue

interface QueueNode {
    void setNext (QueueNode next);
    void setPrev (QueueNode prev);
    void setTreeNode (TreeNode<?> treeNode);

    QueueNode getNext ();
    QueueNode getPrev ();
    TreeNode<?> getTreeNode ();
} //End interface QueueNode

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


} //End class QueueImp

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
} //End class QueueNodeImp

interface Stack { 
    boolean isEmpty ();
    boolean push (TreeNode<?> toAdd);
    TreeNode<?> pop ();
    TreeNode<?> peek ();
} //End interface Stack

interface StackNode {
    void setNext (StackNode next);
    StackNode getNext ();
    void setTreeNode (TreeNode<?> treeNode);
    TreeNode<?> getTreeNode ();
} //End interface StackNode

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

} //End class StackImp

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

} //End class StackNodeImp
