
package org.example;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.linter.projectstructure.ProjectStructure;
import org.linter.projectstructure.ProjectStructureFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class ProjectStructureFactoryTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testMakeProjectStructureFromPath_RecursiveStructure() throws IOException {

        tempFolder.newFile("config.txt");
        tempFolder.newFolder("src");
        Path srcPath = tempFolder.getRoot().toPath().resolve("src");
        java.nio.file.Files.createFile(srcPath.resolve("Main.java"));

        ProjectStructureFactory factory = new ProjectStructureFactory();
        ProjectStructure rootInput = new ProjectStructure(tempFolder.getRoot().toPath());


        ProjectStructure result = factory.makeProjectStructureFromPath(rootInput);

        assertNotNull("Result should not be null", result);


        List<ProjectStructure> subDirectories = result.getChildren();
        assertEquals("Should have 1 child directory (src)", 1, subDirectories.size());
        assertEquals("src", subDirectories.get(0).getPath().getFileName().toString());


    }

    @Test
    public void testEmptyDirectory() throws IOException {
        ProjectStructureFactory factory = new ProjectStructureFactory();
        ProjectStructure emptyRoot = new ProjectStructure(tempFolder.getRoot().toPath());

        ProjectStructure result = factory.makeProjectStructureFromPath(emptyRoot);

        assertTrue("Children list should be empty", result.getChildren().isEmpty());
    }
    @Test
    public void shouldReturnCorrectFilesWithIterator() throws IOException {

        ProjectStructure projectStructure = makeDefaultProjectStructure();
        Path root = projectStructure.getPath();

        List<Path> expectedFiles = List.of(
                root.resolve("config.txt"),
                root.resolve("configThree.txt"),
                root.resolve("configTwo.txt"),
                root.resolve("src").resolve("Main.java")
        );
        Iterator<ProjectStructure> projectStructureIterator = projectStructure.getProjectStructureAsAnIterator();

        List<Path> actualFiles = new ArrayList<>();
        while(projectStructureIterator.hasNext()){
            projectStructureIterator.forEachRemaining(project -> {
                actualFiles.addAll(project.getDirectChildFiles());
            });
        }

    assertEquals(expectedFiles,actualFiles);

    }


    private ProjectStructure makeDefaultProjectStructure() throws IOException {
        tempFolder.newFile("config.txt");
        tempFolder.newFile("configTwo.txt");
        tempFolder.newFile("configThree.txt");
        tempFolder.newFolder("src");
        Path srcPath = tempFolder.getRoot().toPath().resolve("src");
        java.nio.file.Files.createFile(srcPath.resolve("Main.java"));

        ProjectStructureFactory factory = new ProjectStructureFactory();
        ProjectStructure rootInput = new ProjectStructure(tempFolder.getRoot().toPath());


        return factory.makeProjectStructureFromPath(rootInput);
    }
}