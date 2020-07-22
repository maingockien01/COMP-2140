import org.junit.*; 
import static org.junit.Assert.assertEquals;

public class TestLibrary {
    private Book book1 = new Book("Whatever Title","Kevin","Mai",true);
    private Book book2 = new Book("This title","Kien","Mai",false);
    private Book book3 = new Book("That title", "Kevin", "Maii", true);
    private Book book4 = new Book("That title", "Kien", "Mai", false);

    @Test
    public void testAddBook(){
        Library library = new Library();
        library.addBook(book1);
        assertEquals(1,library.getBookNumber());
        assertEquals(book1, library.getBookList()[0]);
    };

    @Test
    public void testListByAuthor(){
        Library library = new Library();
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        assertEquals("Kevin, Mai, Whatever Title\nKevin, Maii, That title\n",library.listByAuthor("Kevin"));
        assertEquals("Kien, Mai, This title\n", library.listByAuthor("Kien"));
    };

    @Test
    public void testListByTitle(){
        Library library = new Library();
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        assertEquals("Kevin, Maii, That title\nKien, Mai, That title\n", library.listByTitle("That title"));
        assertEquals("Kien, Mai, This title\n", library.listByTitle("This title"));
    };

    @Test
    public void testReturnLoanBook(){
        Library library = new Library();
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        System.out.println(library.getBook("Kevin", "Mai", "Whatever Title"));

        System.out.println("Test Loan book:");
        assertEquals(true, library.loanBook("Kevin", "Mai", "Whatever Title"));
        System.out.println("Test Return book");
        assertEquals(true, library.returnBook("Kien", "Mai", "This title"));

        System.out.println("Test if the falg is changed:");
        assertEquals(false, book1.getIsReturned());
        assertEquals(true, book2.getIsReturned());
    };
}
