package vn.edu.lethanhtung.messengappproject;

public class Users {
    //Khai báo các thuộc tính của User --> Lớp csdl và được lưu trên Firebase


    String profilepic;//Ảnh đại diện
    String mail; // Email
    String userName; // Tên người dùng
    String password; // Mật Khẩu
    String userId;// Id người dùng
    String lastMessage; // Tin nhắn cuối cùng
    String status;// Trạng thái
    public Users (){ }

    public Users(String userId, String userName, String maill, String password, String profilepic,String status){
        this.userId = userId;
        this.userName = userName;
        this.mail = maill;
        this.password = password;
        this.profilepic = profilepic;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
