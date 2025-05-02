import java.util.ArrayList;

public class Book {
    private int ISBN;
    private String title;
    private Status status;
    private String category;
    private ArrayList<Author> author;
    private Location location;
    private Borrow borrow;

    public Book(int ISBN) {
        this.ISBN = ISBN;
    }

    public Book(String title, Status status, String category, ArrayList<Author> author, Location location,
            Borrow borrow) {
        this.title = title;
        this.status = status;
        this.category = category;
        this.author = author;
        this.location = location;
        this.borrow = borrow;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getISBN() {
        return ISBN;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String gettitle() {
        return title;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setAuthor(ArrayList<Author> author) {
        this.author = author;
    }

    public ArrayList<Author> getAuthor() {
        return author;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    public Borrow getBorrow() {
        return borrow;
    }

    // public Book fetchBookData(String title){
    // return Book;
    // }

    // public boolean checkBookAvaialabilty(String title){
    // return;
    // }

    // public boolean recordBorrowingTransaction(int ISBN){
    // return;
    // }

    public void addBook() {

    }

    public enum Status {
        ACTIVE,
        DISABLED
    }
}
