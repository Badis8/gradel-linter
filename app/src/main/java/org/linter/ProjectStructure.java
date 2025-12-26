package org.linter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProjectStructure {

    private final String name;
    private final Path path;
    private final List<ProjectStructure> children = new ArrayList<>();

    public ProjectStructure(String name, Path path) {
        this.name = name;
        this.path = path;
    }

    public void addChild(ProjectStructure child) {
        children.add(child);
    }

    public String getName() {
        return name;
    }

    public Path getPath() {
        return path;
    }

    public List<ProjectStructure> getChildren() {
        return children;
    }
}
