package com.softwareexample.springintroexercise.Repositories;

import com.softwareexample.springintroexercise.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    
        List<Book> findAllByReleaseDateAfter(LocalDate localDate);

        @Query("SELECT b from Book b where b.author.firstName = 'George' " +
                "and b.author.lastName = 'Powell' " +
                "order by b.releaseDate desc , b.title")
        List<Book> getAllBookByGeorgePowell();
}
