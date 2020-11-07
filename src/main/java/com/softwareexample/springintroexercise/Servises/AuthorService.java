package com.softwareexample.springintroexercise.Servises;

import antlr.collections.List;
import com.softwareexample.springintroexercise.Entities.Author;

import java.io.IOException;
import java.util.Set;

public interface AuthorService {

    void seedAuthors() throws IOException;
    int getAllAuthorsCount();
    Author findAuthorById(Long id);

    Set<Author> listOfAuthorByCountOfBooks();
    Set<Author> authorsWithBookBefore1990();

}
