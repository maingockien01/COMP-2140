import org.junit.*; 
import static org.junit.Assert.assertEquals;

public class TestStack {

    @Test
    @SuppressWarnings("unchecked")
    public void testPush(){
        Stack stack = new StackImp();
        TreeNode  node1 = TreeNode.createNode(NodeType.NUMBER);
        node1.setValue(2);
        
        TreeNode node2 = TreeNode.createNode(NodeType.VARIABLE);
        node2.setValue("C");

        TreeNode node3 = TreeNode.createNode(NodeType.OPERATOR);
        node3.setValue('*');

        System.out.println("Before push");
        System.out.println("isEmpty");
        assertEquals(true, stack.isEmpty());
        System.out.println("Push node1");
        stack.push(node1);
        System.out.println("isEmpty");
        assertEquals(false, stack.isEmpty());
        System.out.println("Test peek node1");
        assertEquals(node1, stack.peek());

        System.out.println("Push node2");
        stack.push(node2);
        System.out.println("Test peek node2");
        assertEquals(node2, stack.peek());

        System.out.println("Push node3");
        stack.push(node3);
        System.out.println("Test peek node3");
        assertEquals(node3, stack.peek());

        System.out.println("Test pop");
        System.out.println("Pop node3");
        assertEquals(node3, stack.pop());
        System.out.println("Peek node2 after pop");
        assertEquals(node2, stack.peek());
        System.out.println("Pop node2");
        stack.pop();
        System.out.println("Peek node1");
        assertEquals(node1, stack.peek());
        System.out.println("Pop node1");
        assertEquals(node1, stack.pop());
        System.out.println("Empty stack");
        System.out.println("isEmpty");
        assertEquals(true, stack.isEmpty());
        System.out.println("Peek null");
        assertEquals(null, stack.peek());
        System.out.println("Pop null");
        assertEquals(null, stack.pop());
    };

}
