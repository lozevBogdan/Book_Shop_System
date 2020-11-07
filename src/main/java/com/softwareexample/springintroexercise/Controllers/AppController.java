package com.softwareexample.springintroexercise.Controllers;

import com.softwareexample.springintroexercise.Entities.Book;
import com.softwareexample.springintroexercise.Servises.AuthorService;
import com.softwareexample.springintroexercise.Servises.BookService;
import com.softwareexample.springintroexercise.Servises.CategoryService;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.w3c.dom.stylesheets.LinkStyle;

import java.awt.*;
import java.util.List;
import java.util.Scanner;


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

        Scanner scanner = new Scanner(System.in);

        System.out.printf("Welcome to My Homework: 'Spring data intro',%n" +
                "please enter which query from Book System you want to execute or 'STOP' for exit:%n");
        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase("STOP")){
            int n = Integer.parseInt(input);

            switch (n){
                case 1:
                    List<Book> bookList = this.bookService.getAllBooksAfter2000();
                     bookList.forEach(e-> {
                         System.out.println(e.getTitle());
                     });
                    break;

                case 2:
                    this.authorService.authorsWithBookBefore1990()
                .forEach(a-> System.out.printf("%s %s%n",
                        a.getFirstName(),a.getLastName()));
                    break;
                case 3:
        this.authorService.listOfAuthorByCountOfBooks()
                .forEach(e->{
                    System.out.printf("%s %s %d%n",
                            e.getFirstName(),
                            e.getLastName(),
                            e.getBooks().size());
                });
                    break;
                case 4:
        this.bookService.getAllBookByGeorgePowell()
                .forEach(b-> System.out.printf("%s %s %d%n",b.getTitle(),
                        b.getReleaseDate().toString(), b.getCopies()));
                    break;
            }
            System.out.println("please enter which query from Book System you want to execute or 'STOP' for exit:");
            input =scanner.nextLine();
        }
    }
}
