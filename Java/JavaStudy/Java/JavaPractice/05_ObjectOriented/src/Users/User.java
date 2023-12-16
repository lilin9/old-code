package NO5_ObjectOriented_BigWork.Users;

//用户主类
public class User {
    private String userName;
    private String password;
    private String email;
    private int phoneNumber;

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    //设置验证手机号的UI界面
    public void cellphone_ui(){}

    //设置注册账号信息的UI界面
    public void account_ui(){}
}
