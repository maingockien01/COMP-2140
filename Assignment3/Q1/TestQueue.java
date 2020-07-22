import org.junit.*; 
import static org.junit.Assert.assertEquals;

public class TestQueue {
    Queue queue = new Queue();

    Position pos1 = new Position(1, 1, PositionType.WALL);
    Position pos2 = new Position(1, 1, PositionType.WALL);
    Position pos3 = new Position(1, 1, PositionType.WALL);
    Position pos4 = new Position(1, 1, PositionType.WALL);

    @Test
    public void testEnqueue(){
        System.out.println("testEnqueue");

        System.out.println("Enqueue pos1");
        queue.enqueue(pos1);
        assertEquals(1, queue.getLength());

        System.out.println("Enqueue pos2");
        queue.enqueue(pos2);
        assertEquals(2, queue.getLength());

        System.out.println("Enqueue pos3");
        queue.enqueue(pos3);
        assertEquals(3, queue.getLength());

        System.out.println("Enqueue pos4");
        queue.enqueue(pos4);
        assertEquals(4, queue.getLength());

    };

    @Test
    public void testDequeue(){
        System.out.println("testDequeue");
        queue.enqueue(pos1);
        queue.enqueue(pos2);
        queue.enqueue(pos3);
        queue.enqueue(pos4);

        System.out.println("Dequeue pos1");
        System.out.println(queue.getLength());
        Position dequeuPos1 = queue.dequeuePos();

        assertEquals(pos1, dequeuPos1);


        System.out.println("Dequeue pos2");
        Position dequeuPos2 = queue.dequeuePos();

        assertEquals(pos2, dequeuPos2);

        System.out.println("Dequeue pos3");
        Position dequeuPos3 = queue.dequeuePos();

        assertEquals(pos3, dequeuPos3);

        System.out.println("Dequeue pos4");
        Position dequeuPos4 = queue.dequeuePos();

        assertEquals(pos4, dequeuPos4);

    };

}
