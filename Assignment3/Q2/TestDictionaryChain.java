import org.junit.*; 
import static org.junit.Assert.assertEquals;

public class TestDictionaryChain {


    /*
    @Test
    public void testSearch(){
        Dictionary dictionary = new DictionaryChain(sampleList);
        
        assertEquals(true, dictionary.search("absolve"));
        assertEquals(true, dictionary.search("accord"));
        assertEquals(false, dictionary.search("absolves"));
        assertEquals(true, dictionary.search("addresz"));
        assertEquals(true, dictionary.search("border"));
        assertEquals(false, dictionary.search("cccord"));
    };
    **/
    
    @Test
    public void testInsert(){
        Dictionary dictionary = new DictionaryChain(2);

        dictionary.insert("absolve");

        assertEquals(1, dictionary.getSize());
        assertEquals(true, dictionary.search("absolve"));

        dictionary.insert("true");
        dictionary.insert("accord");
        System.out.println("List1: ");
        System.out.println(dictionary);
        dictionary.insert("discord");
        System.out.println("List2:");
        System.out.println(dictionary);
        dictionary.insert("super");
        dictionary.insert("asdsafaf");
        System.out.println("List3: ");
        System.out.println(dictionary);
        System.out.println("---End inserting test ---");
        assertEquals(true, dictionary.search("accord"));

    };
}
