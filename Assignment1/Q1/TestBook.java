import org.junit.*; 
import static org.junit.Assert.assertEquals;

public class TestBook {
    private Book book1 = new Book("Title","FirstName","LastName",true);
    @Test
    public void testBookConstructor(){
    };
    
    @Test
    public void testGetBookInformation(){
        assertEquals(book1.getBookInformation(), "FirstName, LastName, Title\n");
    };

}
