package test;

import Dao.BookDao;
import Dao.impl.BookDaoImpl;
import pojo.Book;

/**
 * Created by MrLi on 2022/01/09/14:47
 */
public class BookDaoTest {

    private final BookDao bookDao = new BookDaoImpl();
    @org.junit.Test
    public void addBook() {
        System.out.println(bookDao.addBook(new Book(null, "三体", "刘慈欣", 100)));
    }

    @org.junit.Test
    public void deleteBookById() {
        System.out.println(bookDao.deleteBookById(0));
    }

    @org.junit.Test
    public void updateBook() {
        System.out.println(bookDao.updateBook(new Book(0, "朝闻道", "大刘", 30)));
    }

    @org.junit.Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(0));
    }

    @org.junit.Test
    public void queryBooks() {
        for (Book book :
                bookDao.queryBooks()) {
            System.out.println(book);
        }
    }
}