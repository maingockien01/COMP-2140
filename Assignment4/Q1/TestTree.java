import org.junit.*; 
import static org.junit.Assert.assertEquals;

public class TestTree { 
    
    @Test
    public void testPrint(){
        System.out.println("Create nodes:");
        TreeNode root = TreeNode.createNode('*');
        TreeNode node1 = TreeNode.createNode(NodeType.OPERATOR);
        node1.setValue('+');
        TreeNode node2 = TreeNode.createNode(NodeType.NUMBER);
        node2.setValue(2);
        TreeNode node3 = TreeNode.createNode(NodeType.NUMBER);
        node3.setValue(3);
        TreeNode node4 = TreeNode.createNode(NodeType.VARIABLE);
        node4.setValue("C");

        Queue queue = new QueueImp();
        queue.queue(root);
        queue.queue(node1);
        queue.queue(node2);
        queue.queue(node3);
        queue.queue(node4);
        
        Tree tree = new ExpressionTree();
        tree.newTree(queue);

        System.out.println("Print");
        System.out.println(tree.printInfix());

        System.out.println("Simplify");
        tree.simplify();

        System.out.println("Print:");
        System.out.println(tree.printInfix());

    };
}
