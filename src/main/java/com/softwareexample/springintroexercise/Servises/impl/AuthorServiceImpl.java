package com.softwareexample.springintroexercise.Servises.impl;

import antlr.collections.List;
import com.softwareexample.springintroexercise.Constants.GlobalConstants;
import com.softwareexample.springintroexercise.Entities.Author;
import com.softwareexample.springintroexercise.Repositories.AuthorRepository;
import com.softwareexample.springintroexercise.Servises.AuthorService;
import com.softwareexample.springintroexercise.util.FilesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final FilesUtil filesUtil;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(FilesUtil filesUtil, AuthorRepository authorRepository) {
        this.filesUtil = filesUtil;
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0){
            return;
        }
        String [] fileContent = filesUtil
                .readFileContent(GlobalConstants.AUTHORS_FILE_PATH);
        Arrays.stream(fileContent)
                .forEach(r -> {
                    String[] params = r.split("\\s+");
                    Author author = new Author(params[0],params[1]);
                    this.authorRepository.saveAndFlush(author);
                });
    }

    @Override
    public int getAllAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Author> listOfAuthorByCountOfBooks() {
        return this.authorRepository.findAllAuthors();
    }

    @Override
    public Set<Author> authorsWithBookBefore1990() {
        return this.authorRepository.findAllWithBookAfter1990();
    }
}
