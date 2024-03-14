package com.p1.services;

import com.p1.Model.Book;
import java.util.List;

public interface IBookStoreService {

    List<Book> getBooks();

    Book createBook(Book book);

    Book updateBook(int bookId, Book book);

    Book getBook(int bookId);

    boolean deteleBook(int bookId);

}
