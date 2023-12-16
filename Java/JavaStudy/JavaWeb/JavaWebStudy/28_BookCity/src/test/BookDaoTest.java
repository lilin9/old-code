package test;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import org.junit.Test;
import pojo.Book;

/**
 * Created by MrLi on 2022/01/03/16:40
 */
public class BookDaoTest {

    private final BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "相对论", "爱因斯坦", 100, 10000.0, 10.0, null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(18);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(18, "相对论", "爱因斯坦", 100, 10000.0, 10.0, "static/img/相对论.jpg"));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(18));
    }

    @Test
    public void queryBooks() {
        for (Book book : bookDao.queryBooks()) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount(){
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems(){
        for (Book book :
                bookDao.queryForPageItems(0, 4)) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice(){
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));
    }

    @Test
    public void queryForPageItemsByPrice(){
        for (Book book :
                bookDao.queryForPageItemsByPrice(0, 4, 10, 50)) {
            System.out.println(book);
        }
    }
}