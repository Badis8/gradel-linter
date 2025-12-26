package org.linter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ProjectStructureFactory {

    ProjectStructure makeProjectStructureFromRootPath(){
        Path root = Paths.get("").toAbsolutePath();
        String rootPath = root.toString();
        try (Stream<Path> paths = Files.walk(Paths.get(rootPath))) {
            paths
                    .forEach(path -> {


                    });
        }


    }
}
