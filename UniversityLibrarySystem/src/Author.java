import java.util.ArrayList;

public class Author {
    private String name;
    private String bio;
    private int authorId;
    private ArrayList<Book> books;

    public Author(String name, String bio, ArrayList<Book> books) {
        this.name = name;
        this.bio = bio;
        this.books = books;
    }

    public Author(int authorId) {
        this.authorId = authorId;
    }    

    public void setName(String name) {
        this.name = name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setId(int authorId) {
        this.authorId = authorId;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public int getId() {
        return authorId;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addAuthor() {
     
    }

    public void updateAuthor() {

    }

    public void deleteAuthor() {

    }
}
