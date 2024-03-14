package com.p1.DAO;

import com.p1.Model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Transactional
@Repository
public class BookStoreDAO  implements IBookStoreDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> getBooks() {
        String hql="FROM Book order by id";
        return (List<Book>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Book createBook(Book book) {
        entityManager.persist(book);
        Book b=getLastInsertedBook();
        System.out.println("Book Inserted Successfully");
        return book;
    }


    @Override
    public Book updateBook(int bookId, Book book) {
        Book bookFromDB=getBook(bookId);
        bookFromDB.setName(book.getName());
        bookFromDB.setAuthor(book.getAuthor());
        bookFromDB.setCategory(book.getCategory());
        bookFromDB.setPublication(book.getPublication());
        bookFromDB.setPages(book.getPages());
        bookFromDB.setPrice(book.getPrice());
        entityManager.flush();
        Book updatedBook=getBook(bookId);
        return updatedBook;
    }

    @Override
    public Book getBook(int bookId) {
        return entityManager.find(Book.class,bookId);
    }

    @Override
    public boolean deleteBook(int bookId) {
        Book book=getBook(bookId);
        entityManager.remove(book);
        boolean status= entityManager.contains(book);
        if(status){
            return false;
        }
        return true;
    }
    private Book getLastInsertedBook() {
        String hql = "FROM Book b ORDER BY b.id DESC";
        Query query = entityManager.createQuery(hql);
        query.setMaxResults(1);

        List<Book> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        } else {
            System.out.println("No Books Found");
            return null;
        }
    }
}