package com.github.rtwnt.language_data.row;

import com.github.rtwnt.language_data.DuplicateRowIdException;
import com.opencsv.bean.CsvBindByName;

import java.io.FileNotFoundException;
import java.util.Map;

public class LanguageName extends DataRow {

    @CsvBindByName(column="Language_ID")
    private String languageId;
    @CsvBindByName(column="Name")
    private String name;
    @CsvBindByName(column="Provider")
    private String provider;

    public String getLanguageId() {
        return languageId;
    }

    public String getName() {
        return name;
    }

    public String getProvider() {
        return provider;
    }

    public static Map<String, LanguageName> readAllFromFile(String path) throws FileNotFoundException, DuplicateRowIdException {
        return readAllFromFile(path, LanguageName.class);
    }
}