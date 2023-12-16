package Dao;

/**
 * Created by MrLi on 2022/02/12/15:09
 *
 * 第二步：创建接口实现类，实现方法
 */
public class Templmpl implements Temp {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public String update(String id) {
        return id;
    }
}
