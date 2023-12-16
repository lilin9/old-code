package test;

import org.junit.Test;
import pojo.Book;
import service.BookService;
import service.impl.BookServiceImpl;

/**
 * Created by MrLi on 2022/01/03/18:46
 */
public class BookServiceTest {
    private final BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "百年孤独", "马尔克斯", 40, 10000.0, 3.0, "static/img/百年孤独.jpg"));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(19);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(19, "百年孤独", "马尔克斯", 30, 10000.0, 3.0, "static/img/百年孤独.jpg"));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(19));
    }

    @Test
    public void queryBooks() {
        for (Book book : bookService.queryBooks()) {
            System.out.println(book);
        }
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1, 4));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(0, 4, 10, 50));
    }
}