package com.softwareexample.springintroexercise.util;

import java.io.FileNotFoundException;

public interface FilesUtil {

    String[] radFileContent(String filePath) throws FileNotFoundException;

}
