import org.junit.*; 
import static org.junit.Assert.assertEquals;

public class TestDictionaryOpen {

    @Test
    public void testInsert(){
        DictionaryOpen dictionary = new DictionaryOpen(7);
        dictionary.insert("kien");
        
        System.out.println("List1: " + dictionary);

        dictionary.insert("asddasf");

        System.out.println("List2: " + dictionary);

        dictionary.insert("asdasdqwe");

        System.out.println("List3: " + dictionary);

        dictionary.insert("asdnli");

        System.out.println("List4: " + dictionary);
        
        dictionary.insert("asdjnkj");
        dictionary.insert("wqrsf");
        dictionary.insert("asdqwr");

        System.out.println("List5: " + dictionary);
        System.out.println("Test search");
        assertEquals(true, dictionary.search("kien"));
    };

}
