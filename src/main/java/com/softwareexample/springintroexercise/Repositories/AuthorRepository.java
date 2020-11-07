package com.softwareexample.springintroexercise.Repositories;

import com.softwareexample.springintroexercise.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query( "SELECT a from Author a order by a.books.size desc ")
    Set<Author> findAllAuthors();

    @Query(value = "SELECT a from Author a join a.books b " +
            "where b.releaseDate < '1990-01-01'")
    Set<Author> findAllWithBookAfter1990();
}
