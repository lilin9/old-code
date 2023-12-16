package pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by MrLi on 2022/02/10/15:17
 */
public class Student {
    //数组类型属性
    private String[] courses;
    //List 集合类型属性
    private List<String> list;
    //Map 集合类型属性
    private Map<String, Object> map;

    public Student() {
    }

    public Student(String[] courses, List<String> list, Map<String, Object> map) {
        this.courses = courses;
        this.list = list;
        this.map = map;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Student{" +
                "courses=" + Arrays.toString(courses) +
                ", list=" + list +
                ", map=" + map +
                '}';
    }
}
