import java.util.ArrayList;

public class Location {
    private int locationID;
    private int floor;
    private String section;
    private String shelf;
    private float row;
    private ArrayList<Book> book ;
    
    public Location(int locationID) {
        this.locationID = locationID;
    }

    public Location(int floor, String section, String shelf, float row, ArrayList<Book> book) {
        this.floor = floor;
        this.section = section;
        this.shelf = shelf;
        this.row = row;
        this.book = book;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSection() {
        return section;
    }
    
    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getShelf() {
        return shelf;
    }

    public void setRow(float row) {
        this.row = row;
    }

    public float getRow() {
        return row;
    }

    public void setBook(ArrayList<Book> book) {
        this.book = book;
    }

    public ArrayList<Book> getBook() {
        return book;
    }
}
