import java.util.*;
import java.io.*;

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


}
