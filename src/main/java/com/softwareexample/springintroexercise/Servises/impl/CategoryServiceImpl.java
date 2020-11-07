package com.softwareexample.springintroexercise.Servises.impl;

import com.softwareexample.springintroexercise.Entities.Category;
import com.softwareexample.springintroexercise.Repositories.CategoryRepository;
import com.softwareexample.springintroexercise.Servises.CategoryService;
import com.softwareexample.springintroexercise.util.FilesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

import static com.softwareexample.springintroexercise.Constants.GlobalConstants.CATEGORIES_FILE_PATH;

@Service
public class CategoryServiceImpl implements CategoryService {

private final CategoryRepository categoryRepository;
private final FilesUtil filesUtil;

@Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FilesUtil filesUtil) {
        this.categoryRepository = categoryRepository;
        this.filesUtil = filesUtil;
    }

    @Override
    public void seedCategories() throws IOException {

    if (this.categoryRepository.count() != 0){
        return;
    }

    String [] fileContent = this.filesUtil
            .readFileContent(CATEGORIES_FILE_PATH);

        Arrays.stream(fileContent)
                .forEach(r->{
                    Category category = new Category(r);
                    this.categoryRepository.saveAndFlush(category);

                });

    }

    @Override
    public Category getCategoryByID(int id) {
        return this.categoryRepository.getOne((long) id);
    }
}
