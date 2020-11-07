package com.softwareexample.springintroexercise.util;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FilesUtil {

    String[] readFileContent(String filePath) throws IOException;

}
