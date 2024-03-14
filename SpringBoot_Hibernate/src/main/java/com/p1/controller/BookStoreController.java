package com.p1.controller;

import com.p1.services.IBookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.p1.Model.Book;
import java.util.List;

@Controller
@RequestMapping("bookservice")
public class BookStoreController {
    @Autowired
    private IBookStoreService service;

    @GetMapping("books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> books=service.getBooks();
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }
    @GetMapping("books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id")Integer id){
        Book book = service.getBook(id);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PostMapping("createbook")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book b = service.createBook(book);
        System.out.println("Book Inserted Successfully");
        return new ResponseEntity<Book>(b, HttpStatus.OK);
    }

    @PutMapping("books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id")Integer id,@RequestBody Book book){
        Book b = service.updateBook(id, book);
        return new ResponseEntity<Book>(b, HttpStatus.OK);
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id")Integer id){
        boolean isDeleted =service.deteleBook(id);
        if(isDeleted) {
            String responseContent = "Book has been Deleted Successfully";
            return new ResponseEntity<String>(responseContent, HttpStatus.OK);
        }
        String error="Error while Deleting book from Database";
        return new ResponseEntity<String>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    }
