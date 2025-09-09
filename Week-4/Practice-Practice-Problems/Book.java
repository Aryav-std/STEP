public class Book {
    String title;
    String author;
    double price;

    public Book() {
        title = "Generic Title";
        author = "Generic Author";
        price = 0.0;
    }

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: " + price);
    }

    public static void main(String[] args) {
        Book book1 = new Book();
        Book book2 = new Book("Gone with the Wind", "Margaret Mitchell", 599.99);
        book1.display();
        book2.display();
    }
}
