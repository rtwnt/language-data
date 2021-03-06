package com.github.rtwnt.language_data;

import com.github.rtwnt.language_data.row.DataRow;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataReader<T extends DataRow> {

    private Class<T> rowClass;

    public DataReader(Class<T> rowClass) {
        this.rowClass = rowClass;
    }

    public List<T> readData(Reader reader) {
        RFC4180Parser rfc4180Parser = new RFC4180ParserBuilder()
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withCSVParser(rfc4180Parser)
                .build();
        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(csvReader)
                .withType(rowClass)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        return csvToBean.parse();
    }

    public Map<String, T> readDataMappedById(Reader reader) throws DuplicateRowIdException {
        Map<String, T> map = new HashMap<>();
        for (T row : this.readData(reader)) {
            if (map.containsKey(row.getId())) {
                throw new DuplicateRowIdException(String.format("Duplicate ID value detected for %s and %s", map.get(row.getId()), row));
            }
            map.put(row.getId(), row);
        }

        return map;
    }
}
