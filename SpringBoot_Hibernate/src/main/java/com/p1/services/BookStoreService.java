package com.p1.services;

import com.p1.DAO.IBookStoreDAO;
import com.p1.Model.Book;
import com.p1.DAO.BookStoreDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookStoreService implements IBookStoreService{
    @Autowired
    private IBookStoreDAO dao;


    @Override
    public List<Book> getBooks() {
        return dao.getBooks();
    }

    @Override
    public Book getBook(int bookId) {
        return dao.getBook(bookId);
    }

    @Override
    public boolean deteleBook(int bookId) {
        return dao.deleteBook(bookId);
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        return dao.updateBook(bookId,book);
    }

    @Override
    public Book createBook(Book book) {
        return dao.createBook(book);
    }
}

