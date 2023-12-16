package pojo;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by MrLi on 2022/02/10/16:28
 */
public class FactoryBean1 implements FactoryBean<Student> {
    @Override
    public Student getObject() throws Exception {
        Student student = new Student(null, null, null);
        System.out.println(student);
        return student;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
