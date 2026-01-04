package org.linter.rules;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSnapshots {



    private final Map<Path, List<Integer> > concreteViolationsSnapshots = new HashMap<>();


    public FileSnapshots() {
    }

    public void addLineToFileSnapshot(Path path,Integer line) {

        concreteViolationsSnapshots.computeIfAbsent(path, k -> new ArrayList<>())
                .add(line);
    }

    public void addLinesToFileSnapshot(Path path,List<Integer> lines) {

        concreteViolationsSnapshots.computeIfAbsent(path, k -> new ArrayList<>())
                .addAll(lines);
    }

}