package NO1_UserJDBC;

import java.util.Date;

public class Beauty {
    private int id;
    private String name;
    private String sex;
    private Date borndate;
    private String phone;
    private int boyfriend_id;

    public Beauty() {
    }

    public Beauty(int id, String name, String sex, Date borndate, String phone, int boyfriend) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.borndate = borndate;
        this.phone = phone;
        this.boyfriend_id = boyfriend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBorndate() {
        return borndate;
    }

    public void setBorndate(Date borndate) {
        this.borndate = borndate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBoyfriend() {
        return boyfriend_id;
    }

    public void setBoyfriend(int boyfriend) {
        this.boyfriend_id = boyfriend;
    }

    @Override
    public String toString() {
        return "Beauty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", borndate=" + borndate +
                ", phone='" + phone + '\'' +
                ", boyfriend=" + boyfriend_id +
                '}';
    }
}