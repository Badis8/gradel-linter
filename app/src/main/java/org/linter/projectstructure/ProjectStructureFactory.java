package org.linter.projectstructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ProjectStructureFactory {

    public ProjectStructure makeProjectStructureFromPath(ProjectStructure projectStructure) throws IOException {

        populateChildFiles(Files.list(projectStructure.getPath()), projectStructure);
        List<Path> directories = getDirectChildDirectories(Files.list(projectStructure.getPath()));

        if (directories.isEmpty()){
            return projectStructure;
        }
        for (Path directory : directories) {
            projectStructure.addChild(makeProjectStructureFromPath(new ProjectStructure(directory)));
        }
        return projectStructure;

    }

    private  void  populateChildFiles(Stream<Path> currentDirectory,ProjectStructure curenntDirectoryPathToEnrich) {
        curenntDirectoryPathToEnrich.setChildFiles(currentDirectory
                .filter(Files::isRegularFile)
                .toList());

    }

    private List<Path> getDirectChildDirectories(Stream<Path> currentDirectory) {
        return currentDirectory
                .filter(Files::isDirectory)
                .toList();
    }



}