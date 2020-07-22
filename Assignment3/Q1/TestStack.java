import org.junit.*; 
import static org.junit.Assert.assertEquals;

public class TestStack {
    private Stack stack = new Stack();

    private Position pos1 = new Position(1,1,PositionType.PATH);
    private Position pos2 = new Position(1,2,PositionType.WALL);
    private Position pos3 = new Position(1,3,PositionType.START);
    private Position pos4 = new Position(1,4,PositionType.FINISH);

    @Test
    public void testPush(){
        stack.push(pos1);

        assertEquals(stack.top(), pos1);
        assertEquals(stack.getLength(), 1);

        stack.push(pos2);

        assertEquals(stack.top(), pos2);
        assertEquals(stack.getLength(), 2);

        stack.push(pos3);

        assertEquals(stack.getLength(), 3);
        assertEquals(stack.top(), pos3);

        stack.push(pos4);

        assertEquals(stack.top(), pos4);
        assertEquals(stack.getLength(), 4);
    };

    @Test
    public void testPop(){
        stack.push(pos1);
        stack.push(pos2);
        stack.push(pos3);
        stack.push(pos4);

        Position pos;

        System.out.println("Pop pos4");
        assertEquals(stack.getLength(), 4);
        pos = stack.pop();

        assertEquals(pos, pos4);
        assertEquals(stack.getLength(), 3);

        System.out.println("Pop pos3");
        pos = stack.pop();

        assertEquals(pos, pos3);
        assertEquals(stack.getLength(), 2);

        System.out.println("Pop pos2");
        pos = stack.pop();

        assertEquals(pos, pos2);
        assertEquals(stack.getLength(), 1);

        System.out.println("Pop pos1");
        pos = stack.pop();

        assertEquals(pos, pos1);
        assertEquals(stack.getLength(), 0);
    };
}
