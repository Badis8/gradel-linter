package org.linter.projectstructure;

import java.nio.file.Path;
import java.util.*;

public class ProjectStructure {


    private final Path curentDirectory;
    private List<Path> directChildFiles =  new ArrayList<>();;
    private final List<ProjectStructure> children = new ArrayList<>();

    public ProjectStructure(Path path) {

        this.curentDirectory = path;
    }

    public void addChild(ProjectStructure child) {
        children.add(child);
    }



    public Path getPath() {
        return curentDirectory;
    }

    public List<ProjectStructure> getChildren() {
        return children;
    }

    public void addChildFile(Path ChildFilepath){
        this.directChildFiles.add(ChildFilepath);
    }

    public void setChildFiles(List<Path> childFilesPaths){
        this.directChildFiles = childFilesPaths;
    }

    public List<Path> getDirectChildFiles() {
        return directChildFiles;
    }

    public Iterator<ProjectStructure> getProjectStructureAsAnIterator(){
        return makeCurentProjectStructureIterator();
    }

    private Iterator<ProjectStructure> makeCurentProjectStructureIterator() {
        return new Iterator<ProjectStructure>() {

            private final Stack<ProjectStructure> stack = new Stack<>();

            {

                stack.push(ProjectStructure.this);
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public ProjectStructure next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }


                ProjectStructure current = stack.pop();

                List<ProjectStructure> children = current.getChildren();
                for (ProjectStructure child : children ){
                    stack.push(child);
                }
                return current;
            }
        };
    }
}

