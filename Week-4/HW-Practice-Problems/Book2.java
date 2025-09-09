public class Book2 {
    String title;
    String author;
    String isbn;
    boolean isAvailable;

    // Default constructor with empty Book2
    public Book2() {
        title = "";
        author = "";
        isbn = "";
        isAvailable = true;
    }

    // Constructor with title and author
    public Book2(String title, String author) {
        this.title = title;
        this.author = author;
        isbn = "";
        isAvailable = true;
    }

    // Constructor with all details
    public Book2(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    // Borrow Book2 method
    public void borrowBook2() {
        if (isAvailable) isAvailable = false;
        else System.out.println("Book2 already borrowed");
    }

    // Return Book2 method
    public void returnBook2() {
        isAvailable = true;
    }

    // Display Book2 information
    public void displayBook2Info() {
        System.out.println("Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + isAvailable);
    }

    public static void main(String[] args) {
        Book2 b1 = new Book2();
        Book2 b2 = new Book2("1984", "George Orwell");
        Book2 b3 = new Book2("The Hobbit", "J.R.R. Tolkien", "978-0261103344", true);

        b2.borrowBook2();
        b3.returnBook2();

        b1.displayBook2Info();
        b2.displayBook2Info();
        b3.displayBook2Info();
    }
}
