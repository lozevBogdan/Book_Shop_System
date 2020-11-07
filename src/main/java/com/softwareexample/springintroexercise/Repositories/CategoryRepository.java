package com.softwareexample.springintroexercise.Repositories;

import com.softwareexample.springintroexercise.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {



}
