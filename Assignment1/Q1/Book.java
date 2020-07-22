public class Book { 
    public static final boolean BOOK_RETURNED = true;
    public static final boolean BOOK_LOANED = false;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private boolean isReturned;
    public Book (String title, String authorFirstName, String authorLastName, boolean isReturned) { 
        this.title = title;
        if (authorFirstName == "") {
            this.authorFirstName = "unknown";
        } else {
            this.authorFirstName = authorFirstName;
        };
        if (authorLastName == "") {
            this.authorLastName = "unknown";
        } else {
            this.authorLastName = authorLastName;
        };
        this.isReturned = isReturned;
    }

    public String getBookInformation(){
        StringBuilder infoBuilder = new StringBuilder();
        infoBuilder.append(authorFirstName);
        infoBuilder.append(", ");
        infoBuilder.append(authorLastName);
        infoBuilder.append(", ");
        infoBuilder.append(title);
        infoBuilder.append("\n");
        return infoBuilder.toString();
    }

    public String getAuthorFirstName() {
        return this.authorFirstName;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean getIsReturned() {
        return this.isReturned;
    }

    public void setIsReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    public String getAuthorLastName() {
        return this.authorLastName;
    }
}  
