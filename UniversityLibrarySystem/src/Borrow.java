import java.util.Date;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.ResultSet;

public class Borrow {
    private int borrowID;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;
    private BorrowStatus status;
    private int renewalCount;
    private double fineAmount;
    private Student student;
    private Book book;

    public Borrow(int borrowID) {
        this.borrowID = borrowID;
    }

    public Borrow(Date borrowDate, Date dueDate, Date returnDate, BorrowStatus status, int renewalCount, double fineAmount, Book book, Student student) {
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
        this.renewalCount = renewalCount;
        this.fineAmount = fineAmount;
        this.book = book;
        this.student = student;
    }
    
    public void setBorrowID(int borrowID) {
        this.borrowID = borrowID;
    }

    public int getBorrowID() {
        return borrowID;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setStatus(BorrowStatus status) {
        this.status = status;
    }

    public BorrowStatus getStatus() {
        return status;
    }

    public void setRenewalCount(int renewalCount) {
        this.renewalCount = renewalCount;
    }

    public int getRenewalCount() {
        return renewalCount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void renewBorrow(Date newDueDate) {
        this.dueDate = newDueDate;
        
    }

    public boolean isOverdue() {
        Date currentDate = new Date();
        return currentDate.after(dueDate);
    }
    
    /**
     * Creates a new borrowing record in the database
     * 
     * @param studentID The ID of the student borrowing the book
     * @param bookISBN The ISBN of the book being borrowed
     * @return true if the borrowing was successful, false otherwise
     */
    public static boolean borrowBook(int studentID, String bookISBN) {
        Connection conn = DBManager.openCon();
        if (conn == null) {
            return false;
        }
        
        try {
            // Get the last borrow ID and increment it
            int nextBorrowId = 1; // Default starting ID if no records exist
            String getLastIdQuery = "SELECT MAX(BORROW_ID) AS LAST_ID FROM BORROW";
            ResultSet lastIdResult = DBManager.query(conn, getLastIdQuery);
            if (lastIdResult != null && lastIdResult.next()) {
                int lastId = lastIdResult.getInt("LAST_ID");
                if (!lastIdResult.wasNull()) {
                    nextBorrowId = lastId + 1;
                }
            }
            
            // Calculate dates
            Date borrowDate = new Date(); // Current date
            
            // Set due date (14 days from today)
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(borrowDate);
            calendar.add(Calendar.DAY_OF_MONTH, 14);
            Date dueDate = calendar.getTime();
            
            // Insert borrow record with the new ID
            java.sql.Date sqlBorrowDate = new java.sql.Date(borrowDate.getTime());
            java.sql.Date sqlDueDate = new java.sql.Date(dueDate.getTime());
            String insertQuery = "INSERT INTO BORROW (BORROW_ID, BORROW_DATE, DUE_DATE, RENEWAL_COUNT, FINE_AMOUNT, STUDENT_ID, BOOK_ID) " +
                                 "VALUES (" + nextBorrowId + ", '" + sqlBorrowDate + "', '" + sqlDueDate + "', 0, 0.0, " + 
                                 studentID + ", '" + bookISBN + "')";
            
            int result = DBManager.updateQuery(conn, insertQuery);
            
            if (result > 0) {
                // Update book status to unavailable
                String updateBookQuery = "UPDATE BOOK SET STATUS = 'DISABLED' WHERE ISBN = '" + bookISBN + "'";
                DBManager.updateQuery(conn, updateBookQuery);
                return true;
            }
            
            return false;
        } catch (Exception ex) {
            System.out.println("Error borrowing book: " + ex.getMessage());
            return false;
        } finally {
            DBManager.closeCon(conn);
        }
    }

    enum BorrowStatus {
        ACTIVE,
        RETURNED,
        OVERDUE,
        LOST
    }
}
