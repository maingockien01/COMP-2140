import org.junit.*; 
import static org.junit.Assert.assertEquals;

public class TestMaze {
    public static final String FILENAME = "SampleInput.txt";

    Maze maze = new Maze(FILENAME);

    String initMaze = "#######\n#...#S#\n#F#...#\n#######\n";
    String finalMaze = "#######\n#***#S#\n#F#***#\n#######\n";
    String rightPath = "(1, 5) (2, 5) (2, 4) (2, 3) (1, 3) (1, 2) (1, 1) (2, 1)";

    @Test
    public void testQueueSearch(){
        maze.stackSearch();
        System.out.println("Test resetMaze");
        maze.resetMaze();

        assertEquals(initMaze, maze.mazeToString());
        System.out.println("End resetMaze");
        System.out.println(maze.mazeToString());

        System.out.println("Test QueueSearch");
        maze.queueSearch();

        System.out.println(maze.mazeToString());

        System.out.println("Assert maze map");
        assertEquals(finalMaze, maze.mazeToString());
        System.out.println("Assert path");
        assertEquals(rightPath, maze.pathToString());
        System.out.println("End queueSearch");
    };
    @Test
    public void testSearchStack(){
        System.out.println("Test stackSearch");
        maze.stackSearch();

        assertEquals(finalMaze, maze.mazeToString());
        assertEquals(rightPath, maze.pathToString());
        System.out.println("End StackSearch");
        
    };

    @Test
    public void testConstructor(){
        
        System.out.println("Test constructor - init maze:");
        assertEquals(initMaze, maze.mazeToString());

        System.out.println("End Constructor");
    };

}
