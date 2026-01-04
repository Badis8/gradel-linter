package org.linter.rules;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class RuleViolation {


    private final  FileSnapshots fileSnapshots = new FileSnapshots();
    private String description;

    public void addViolation(FileSnapshots fileSnapshots ) {

    }


    public void setDescription(String description) {
        this.description = description;
    }


    public FileSnapshots  getConcereteViolationsSnapshots() {
        return fileSnapshots;
    }

    public String getDescription() {
        return description;
    }
}