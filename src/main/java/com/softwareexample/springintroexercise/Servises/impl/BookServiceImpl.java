package com.softwareexample.springintroexercise.Servises.impl;

import com.softwareexample.springintroexercise.Constants.GlobalConstants;
import com.softwareexample.springintroexercise.Entities.*;
import com.softwareexample.springintroexercise.Repositories.BookRepository;
import com.softwareexample.springintroexercise.Servises.AuthorService;
import com.softwareexample.springintroexercise.Servises.BookService;
import com.softwareexample.springintroexercise.Servises.CategoryService;
import com.softwareexample.springintroexercise.util.FilesUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final FilesUtil filesUtil;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, FilesUtil filesUtil, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.filesUtil = filesUtil;
        this.categoryService = categoryService;
    }
    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0){
            return;
        }
        String [] fileContent = this.filesUtil
                .readFileContent(GlobalConstants.BOOKS_FILE_PATH);

        Arrays.stream(fileContent)
                .forEach(r->{
                    String [] params = r.split("\\s+");

                    Author author = this.grtRandomAuthor();
                  EditionType editionType = EditionType
                          .values()[Integer.parseInt(params[0])];

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    LocalDate releaseDate = LocalDate.parse(params[1],formatter);
                    int copies = Integer.parseInt(params[2]);
                    BigDecimal price = new BigDecimal(params[3]);
                    AgeRestriction ageRestriction = AgeRestriction
                            .values()[Integer.parseInt(params[4])];

                    String title = this.grtTitle(params);


                    Set<Category> categories =
                            this.getRandomCategories();

                    Book book = new Book();
                    book.setAuthor(author);
                    book.setEditionType(editionType);
                    book.setReleaseDate(releaseDate);
                    book.setCopies(copies);
                    book.setPrice(price);
                    book.setAgeRestriction(ageRestriction);
                    book.setTitle(title);
                    book.setCategories(categories);

                    this.bookRepository.saveAndFlush(book);

                });

    }

    @Override
    public List<Book> getAllBooksAfter2000() {
        LocalDate localDate = LocalDate
                .of(2000,12,31);
        return this.bookRepository
                .findAllByReleaseDateAfter(localDate);
    }

    @Override
    public List<Book> getAllBookByGeorgePowell() {
        return this.bookRepository.getAllBookByGeorgePowell();
    }

    private Set<Category> getRandomCategories() {
        Set<Category> result = new HashSet<>();
        Random random = new Random();
        int bound = random.nextInt(3)+1;


        for (int i = 1; i <= bound; i++) {
            int  categoryId = random.nextInt(8)+1;
            result.add(this.categoryService
                    .getCategoryByID(categoryId));
        }
        return result;
    }

    private String grtTitle(String[] params) {

        StringBuilder builder = new StringBuilder();

        for (int i = 5; i <params.length ; i++) {

            builder.append(params[i]);
            builder.append(" ");

        }

        return builder.toString().trim();
    }

    private Author grtRandomAuthor() {

        Random rnd = new Random();
        int randomID = rnd.nextInt(this.authorService.getAllAuthorsCount()) + 1;
        return this.authorService.findAuthorById((long) randomID);

    }
}
