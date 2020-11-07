package com.softwareexample.springintroexercise.Controllers;

import com.softwareexample.springintroexercise.Entities.Book;
import com.softwareexample.springintroexercise.Servises.AuthorService;
import com.softwareexample.springintroexercise.Servises.BookService;
import com.softwareexample.springintroexercise.Servises.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.w3c.dom.stylesheets.LinkStyle;

import java.awt.*;
import java.util.List;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AppController(CategoryService categoryService,
                         AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();



        //Ex1
//        List<Book> bookList = this.bookService.getAllBooksAfter2000();
//        bookList.forEach(e->{
//            System.out.println(e.getTitle());
//        });

        //Ex2
//        this.authorService.authorsWithBookBefore1990()
//                .forEach(a-> System.out.printf("%s %s%n",
//                        a.getFirstName(),a.getLastName()));


        //Ex3
//        this.authorService.listOfAuthorByCountOfBooks()
//                .forEach(e->{
//                    System.out.printf("%s %s %d%n",
//                            e.getFirstName(),
//                            e.getLastName(),
//                            e.getBooks().size());
//                });

                // EX4
//        this.bookService.getAllBookByGeorgePowell()
//                .forEach(b-> System.out.printf("%s %s %d%n",b.getTitle(),
//                        b.getReleaseDate().toString(), b.getCopies()));

    }
}
