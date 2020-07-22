import org.junit.*; 
import static org.junit.Assert.assertEquals;

public class TestDictionaryOrdered {

    String[] sampleList = {"absolve","accord","accross","address","addresz", "be", "belong", "bet", "bike", "board", "border"};

    @Test
    public void testSearch(){
        DictionaryOrdered dictionary = new DictionaryOrdered(sampleList);
        assertEquals(true, dictionary.search("absolve"));
        assertEquals(true, dictionary.search("accord"));
        assertEquals(false, dictionary.search("absolves"));
        assertEquals(true, dictionary.search("addresz"));
        assertEquals(true, dictionary.search("border"));
        assertEquals(false, dictionary.search("cccord"));
    };
    
    @Test
    public void testInsert(){
        DictionaryOrdered dictionary = new DictionaryOrdered(100);

        dictionary.insert("absolve");

        dictionary.insert("accord");
        dictionary.insert("accross");
        dictionary.insert("address");
        dictionary.insert("addresz");
        dictionary.insert("be");
        dictionary.insert("belong");
        dictionary.insert("border");
        dictionary.insert("chose");

        System.out.println(dictionary);
    };
}
