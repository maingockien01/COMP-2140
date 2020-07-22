import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MaiKienA1Q1 {
    public static final String KEYWORD_ADD = "ADD";
    public static final String KEYWORD_SEARCH_AUTHOR = "SEARCHA";
    public static final String KEYWORD_SEARCH_TITLE = "SEARCHT";
    public static final String KEYWORD_LOAN_BOOK = "GETBOOK";
    public static final String KEYWORD_RETURN_BOOK = "RETURNBOOK";

    public static void main(String[] args) {
        Library library = new Library();
        MaiKienA1Q1 main = new MaiKienA1Q1();
        Scanner keyboard = new Scanner(System.in);
	System.out.println("Please type the name of input file:");
	String fileName = keyboard.nextLine();
        main.readFile(fileName, library);
    };

    /* 
     * (String) -> void
     * Receive file name to read and run command regard the file
     */
    private void readFile(String fileName, Library library) {
        System.out.println("Processing file " + fileName + " ...\n");
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String output = readLine(line, library);
                if(output!=null) {
                    System.out.println(output);
                };
            }; 
            scanner.close();
            System.out.println("Program terminated normally.");
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file " + fileName + " ...");
        };

    }

    /*
     * (String, Library) -> void
     * Receive a line in file and an instance of libray to interpret the line and run command
     *
     * @param line      The line in file
     * 
     * @param libray    The instance of library
     *
     */
    private String readLine(String line, Library library) {
        //Indentify the command regardto first word of the line
        //Run the command based on that
        String commandKeyword = readKeyword(line);
        String commandContent = readContent(line, commandKeyword);
        String output = runCommand(commandKeyword, commandContent, library);

        return output;
    }

    public String readKeyword(String line) {
        Scanner scanner = new Scanner(line);
        String commandKeyword = scanner.next(); 
        return commandKeyword;
    }

    public String readContent(String line, String commandKeyword) {
        String commandContent = line.substring(commandKeyword.length()+1);

        return commandContent;
    }

    /*
     * Receive the keyword and content and an instance of library
     * to do action regarding to the line 
     *
     */
    public String runCommand(String keyword, String content, Library library) {
        //TODO: Write the test X
        //      Write the constants for keyword
        //      Write action for different keyword
        String output = null;
        if (keyword.equals(KEYWORD_ADD)){
            output = addBook(content, library);
        } else if (keyword.equals(KEYWORD_SEARCH_AUTHOR)) {
            output = searchByAuthor(content, library);
        } else if (keyword.equals(KEYWORD_SEARCH_TITLE)) {
            output = searchByTitle(content, library);
        } else if (keyword.equals(KEYWORD_LOAN_BOOK)) {
            output = loanBook(content, library);
        } else if (keyword.equals(KEYWORD_RETURN_BOOK)) {
            output = returnBook(content, library);
        } else {
            output = "Invalid command!";
        };
        return output;

    }

    private String addBook(String bookInfo, Library library) {
        Book book = createBook(bookInfo);
        library.addBook(book);

        return null;
    }

    private String searchByAuthor(String content, Library library) {
        String authorName = content;
        
        String booksInfo = library.listByAuthor(authorName);
        
        return "Books by " + authorName + ":\n" + booksInfo;
    }

    private String searchByTitle(String content, Library library) {
        String title = content;

        String booksInfo = library.listByTitle(title);

        return "Books named " + content + ":\n" + booksInfo; 
    }

    private String loanBook(String content, Library library) {
        String[] bookInfo = content.split(",");
        
        String authorFirstName = bookInfo[0].trim();
        String authorLastName = bookInfo[1].trim();
        String title = bookInfo[2].trim();
        
        boolean isBookavalable = library.loanBook(authorFirstName, authorLastName, title);
        if (isBookavalable) {
            return "Book loaned:\n" + library.getBook(authorFirstName, authorLastName, title);
        } else {
            return "Book is not available for loaning";
        }
    };

    private String returnBook(String content, Library library) {
        String[] bookInfo = content.split(",");
        
        String authorFirstName = bookInfo[0].trim();
        String authorLastName = bookInfo[1].trim();
        String title = bookInfo[2].trim();

        boolean isBookReturnable = library.returnBook(authorFirstName, authorLastName, title);

        if (isBookReturnable) {
            return "Book returned:\n" + library.getBook(authorFirstName, authorLastName, title);
        } else {
            return "Book is already returned\n";
        }
    }

    /* 
     * (String) -> Book
     * Receive the string of book information then create an instance of book
     * 
     * @param bookInformation   The string contains information of book
     *                          It has the format of firstAuthorName, lastAuthorName, title
     *
     * @return                  An instance of book contains given information
     */
    private Book createBook(String bookInformation) {
        Book newBook = null;
        String[] bookInfo = bookInformation.split(",");
        
        String authorFirstName = bookInfo[0].trim();
        String authorLastName = bookInfo[1].trim();
        String title = bookInfo[2].trim();
        Boolean isReturned = true;

        if (authorFirstName.length() == 0 ) {
            authorFirstName = "unknown";
        }; 

        if (authorLastName.length() == 0) {
            authorLastName = "unknown";
        };

        newBook = new Book(title, authorFirstName, authorLastName, isReturned);

        return newBook;
    }

}
