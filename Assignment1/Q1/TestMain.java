import org.junit.*; 
import static org.junit.Assert.assertEquals;

public class TestMain {
    MaiKienA1Q1 main = new MaiKienA1Q1();

    Library library = new Library();

    Book book1 = new Book("This title 1", "FirstName1", "LastName1", true);
    Book book2 = new Book("This title 2", "FirstName2", "LastName2", false);
    Book book3 = new Book("This title 3", "FirstName3", "LastName3", true);
    Book book4 = new Book("This title 4", "FirstName4", "LastName4", false);

    String line1 = "ADD Kevin, Mai, Whatever Title";
    String line2 = "SEARCHA Kevin";
    String line3 = "GETBOOK Kevin, Mai, Whatever Title";
    String line4 = "SEARCHT Whatever Title";
    String line5 = "RETURNBOOK Kevin, Mai, Whatever Title";

    @Test
    public void testInterpretLine(){

        System.out.println("Test line 1: ");
        assertEquals("ADD", main.readKeyword(line1));
        assertEquals("Kevin, Mai, Whatever Title", main.readContent(line1, "ADD"));
        System.out.println("Test line 2: ");
        assertEquals("SEARCHA", main.readKeyword(line2));
        assertEquals("Kevin", main.readContent(line2, "SEARCHA"));
        System.out.println("Test line 3: ");
        assertEquals("GETBOOK", main.readKeyword(line3));
        assertEquals("Kevin, Mai, Whatever Title", main.readContent(line3, "GETBOOK"));
        System.out.println("Test line 4: ");
        assertEquals("SEARCHT", main.readKeyword(line4));
        assertEquals("Whatever Title", main.readContent(line4, "SEARCHT"));
        System.out.println("Test line 5: ");
        assertEquals("RETURNBOOK", main.readKeyword(line5));
        assertEquals("Kevin, Mai, Whatever Title", main.readContent(line5, "RETURNBOOK"));
    };

    @Test
    public void testRunCommand(){
        System.out.println("Test Line 1");
        main.runCommand("ADD", "Kevin, Mai, Whatever Title", library);
        assertEquals(1, library.getBookNumber());
        System.out.println("Test Line 2");
        String searchAuthor = main.runCommand("SEARCHA", "Kevin", library);
        System.out.println(searchAuthor);
        assertEquals("Books by Kevin:\nKevin, Mai, Whatever Title\n", searchAuthor);
        System.out.println("Test Line 3");
        String getBook = main.runCommand("GETBOOK", "Kevin, Mai, Whatever Title", library);
        assertEquals("Book loaned:\nKevin, Mai, Whatever Title\n", getBook);
        System.out.println("Test Line 4");
        String searchTitle = main.runCommand("SEARCHT", "Whatever Title", library);
        assertEquals("Books named Whatever Title:\nKevin, Mai, Whatever Title\n", searchTitle);
        System.out.println("Test Line 5");
        String returnBook = main.runCommand("RETURNBOOK", "Kevin, Mai, Whatever Title", library);
        assertEquals("Book returned:\nKevin, Mai, Whatever Title\n", returnBook);
    };
}
