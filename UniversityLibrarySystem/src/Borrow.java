import java.util.Date;

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
    
//    public void confirmBorrowingProcess(){
//        return;
//    }

    enum BorrowStatus {
        ACTIVE,
        RETURNED,
        OVERDUE,
        LOST
    }
}
