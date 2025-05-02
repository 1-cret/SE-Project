public class User {
    private int userID;
    private String name;
    private String email;
    private String password;
    private String role;
    private UserStatus.Status status;

    public User(int userID) {
        this.userID = userID;
    }

    public User(String name, String email, String password, String role, UserStatus.Status status) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public int getUserID() {
        return userID;
    }    

    public void setName(String name) {
        this.name = name;
    }

     public String getName() {
        return name;
    }
   
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserStatus.Status getStatus() {
        return status;
    }

    public void setStatus(UserStatus.Status status) {
        this.status = status;
    }


    
    
}