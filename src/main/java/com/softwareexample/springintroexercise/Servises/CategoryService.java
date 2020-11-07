package com.softwareexample.springintroexercise.Servises;

import com.softwareexample.springintroexercise.Entities.Category;

import java.io.IOException;

public interface CategoryService {

    void seedCategories() throws IOException;
    Category getCategoryByID(int id);
}
