package com.MrLi.bean;

/**
 * Created by MrLi on 2022/02/26/12:13
 */
public class User {
    private Integer id;
    private String username;
    private String sex;
    private Integer age;

    public User() {
    }

    public User(Integer id, String username, String sex, Integer age) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
