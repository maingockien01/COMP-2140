import org.junit.*; 
import static org.junit.Assert.assertEquals;

public class TestBST {

    
    @Test
    public void testDeleteInternalNodeWith2Children(){
        BST tree = new BST();
        tree.insert(42);
        tree.insert(5);
        tree.insert(18);
        tree.insert(72);
        tree.insert(3);
        tree.insert(16);
        tree.insert(89);
        tree.insert(61);
        tree.insert(67);
        tree.insert(32);
        tree.insert(69);

        System.out.println("After insert: " + tree.inorderTraversal());

        tree.delete(42);

        System.out.println("After delete : " + tree.inorderTraversal());
    };

}
