package com.softwareexample.springintroexercise.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class FileUtilImpl implements FilesUtil {


    @Override
    public String[] readFileContent(String filePath) throws IOException {

        Set<String> result = new LinkedHashSet<>();
        String line;
        File file = new File(filePath);
        BufferedReader reader =
                new BufferedReader(new FileReader(file));

        while ((line = reader.readLine()) != null ){
            if (!"".equals(line)){
                result.add(line);
            }
        }
        return result.toArray(String[]::new);
    }
}
