package org.linter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RuleViolation {

    private final HashMap<String, List<Integer>> concereteViolationsSnapshots = new HashMap<>();
    private String description;

    public void addViolation(String fileName, Integer lineNumber) {

        this.concereteViolationsSnapshots
                .computeIfAbsent(fileName, k -> new ArrayList<>())
                .add(lineNumber);
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public HashMap<String, List<Integer>> getConcereteViolationsSnapshots() {
        return concereteViolationsSnapshots;
    }

    public String getDescription() {
        return description;
    }
}