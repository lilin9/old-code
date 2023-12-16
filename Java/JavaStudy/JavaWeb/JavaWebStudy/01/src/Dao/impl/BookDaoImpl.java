package Dao.impl;

import Dao.BaseDao;
import pojo.Book;
import Dao.BookDao;
import java.util.List;

/**
 * Created by MrLi on 2022/01/09/13:53
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into book(`name`, `author`, `price`) values(?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice());
    }

    @Override
    public int deleteBookById(int id) {
        String sql = "delete from book where id=?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update book set `name`=?,`author`=?,`price`=? where id=?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getId());
    }

    @Override
    public Book queryBookById(int id) {
        String sql = "select `name`, `author`, `price` from book where `id`=?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `name`, `author`, `price` from book";
        return queryForList(Book.class, sql);
    }
}
