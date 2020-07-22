public class Library {
    public static final int BOOK_CAPACITY = 20;
    private Book[] bookList;
    private int bookNumber;
    public Library() {
        this.bookList = new Book[BOOK_CAPACITY];
        this.bookNumber = 0;
    }

    public int getBookNumber () {
        return this.bookNumber;
    }

    public Book[] getBookList () {
        return this.bookList;
    }

    public void addBook (Book book) {
        if (bookNumber >= BOOK_CAPACITY) {
            return;
        };
        bookList[bookNumber++] = book;

    }

    public String listByAuthor (String authorName) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bookNumber; i ++) {
            Book book = bookList[i];
            boolean isAuthorNameMatch = authorName.equals(book.getAuthorFirstName());
            if (isAuthorNameMatch) {
                builder.append(book.getBookInformation());
            }
        }
        return builder.toString();
    }

    public String listByTitle (String title) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bookNumber; i ++) {
            Book book = bookList[i];
            boolean isTitleMatch = title.equals(book.getTitle());
            if (isTitleMatch) {
                builder.append(book.getBookInformation());
            }
        }
        return builder.toString();
    }

    public String getBook(String authorFirstName, String authorLastName, String title) {
        Book book = searchBook(authorFirstName, authorLastName, title);
        
        return book.getBookInformation();
    }


    /* (String, String) -> boolean
     * Searching books and loaning books
     *
     * @param authorName:   First Name of Author
     *
     * @param title:        Title of Books 
     *
     * @return:             If the book is available for loan
     */
    public boolean loanBook (String authorFirstName, String authorLastName, String title) {
        Book book = searchBook(authorFirstName, authorLastName, title);
        boolean isLoanable = false;
        if (book != null) {
            boolean isBookReturned = book.getIsReturned();
            if (isBookReturned) {
                book.setIsReturned(Book.BOOK_LOANED);
                isLoanable = true;
            };
        } else {
            isLoanable = false;
        };

        return isLoanable;
    }

    public boolean returnBook (String authorFirstName, String authorLastName, String title) {
        //TODO: return if the book is returned
        //      change isReturned to true
        Book book = searchBook(authorFirstName, authorLastName, title);
        
        boolean isBookReturned = book.getIsReturned();
        if (!isBookReturned) {
            book.setIsReturned(Book.BOOK_RETURNED);
        };
        return !isBookReturned;
    }

    private Book searchBook (String authorFirstName, String authorLastName, String title) {
        Book foundBook = null;
        for (int i = 0; i < bookNumber; i ++) {
            Book book = bookList[i];
            boolean isBookMatch = authorFirstName.equals(book.getAuthorFirstName()) && authorLastName.equals(book.getAuthorLastName()) && title.equals(book.getTitle());
            if (isBookMatch) {
                foundBook = book;
            };
        }

        return foundBook;
    }

}

