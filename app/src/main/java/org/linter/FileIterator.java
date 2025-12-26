package org.linter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

 class FileIterator implements Iterator<ProjectStructure> {

    private final Deque<ProjectStructure> stack = new ArrayDeque<>();
    private ProjectStructure nextFile;

    FileIterator(ProjectStructure root) {
        stack.push(root);
        advance();
    }

    private void advance() {
        nextFile = null;

        while (!stack.isEmpty()) {
            ProjectStructure current = stack.pop();

            if (current.getChildren().isEmpty()) {
                nextFile = current;
                return;
            }


            for (ProjectStructure child : current.getChildren()) {
                stack.push(child);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return nextFile != null;
    }

    @Override
    public ProjectStructure next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        ProjectStructure result = nextFile;
        advance();
        return result;
    }
}
