package Dao;

import pojo.Book;

import java.util.List;

/**
 * Created by MrLi on 2022/01/09/13:50
 */
public interface BookDao {
    int addBook (Book book);

    int deleteBookById(int id);

    int updateBook(Book book) ;

    Book queryBookById(int id);

    List<Book> queryBooks();
}
