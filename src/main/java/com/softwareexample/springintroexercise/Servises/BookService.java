package com.softwareexample.springintroexercise.Servises;

import com.softwareexample.springintroexercise.Entities.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {

    void seedBooks() throws IOException;
    List<Book> getAllBooksAfter2000();
    List<Book> getAllBookByGeorgePowell();


}
