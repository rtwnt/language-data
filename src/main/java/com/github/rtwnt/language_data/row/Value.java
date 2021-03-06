package com.github.rtwnt.language_data.row;

import com.github.rtwnt.language_data.DuplicateRowIdException;
import com.opencsv.bean.CsvBindByName;

import java.io.FileNotFoundException;
import java.util.Map;

public class Value extends DataRow {

    @CsvBindByName(column="Language_ID")
    private String languageId;
    @CsvBindByName(column="Parameter_ID")
    private String parameterId;
    @CsvBindByName(column="Example_ID")
    private String exampleId;
    @CsvBindByName(column="Code_ID")
    private String codeId;
    @CsvBindByName(column="Value")
    private String value;
    @CsvBindByName(column="Comment")
    private String comment;
    @CsvBindByName(column="Source")
    private String source;

    public Value(){}

    public Value(Value other) {
        super(other);
        this.languageId = other.languageId;
        this.parameterId = other.parameterId;
        this.exampleId = other.exampleId;
        this.codeId = other.codeId;
        this.value = other.value;
        this.comment = other.comment;
        this.source = other.source;
    }

    public String getLanguageId() {
        return languageId;
    }

    public String getParameterId() {
        return parameterId;
    }

    public String getExampleId() {
        return exampleId;
    }

    public String getCodeId() {
        return codeId;
    }

    public String getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }

    public String getSource() {
        return source;
    }

    public static Map<String, Value> readAllFromFile(String path) throws FileNotFoundException, DuplicateRowIdException {
        return readAllFromFile(path, Value.class);
    }
}
