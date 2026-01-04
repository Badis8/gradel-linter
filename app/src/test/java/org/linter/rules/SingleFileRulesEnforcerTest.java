package org.linter.rules;

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
import static org.junit.Assert.assertEquals;


public class SingleFileRulesEnforcerTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    SingleFileRulesEnforcer singleFileRulesEnforcer;



    @Test
    public void shouldDetectSingleFileViolations() throws IOException {

        Path projectRootPath = makeDefaultProjectStructure();
        singleFileRulesEnforcer = new SingleFileRulesEnforcer(projectRootPath);
        singleFileRulesEnforcer.reviewAndReportViolations( );


    }





    private Path makeDefaultProjectStructure() throws IOException {
        String mockCode = """
    package org.linter.mock;
    
    public class MockService {
        public void process(String input, String status) {
            // Bad practice: variable on the left
            if (input.equals("START")) { 
                System.out.println("Started");
            }
            
            // Random code logic
            String state = "ACTIVE";
            if (status.equals(state)) {
                System.out.println("Status matches");
            }
            
            // Another violation
            if (input.equals(System.getProperty("user.name"))) {
                execute();
            }
        }
        
        private void execute() {
            String temp = "someValue";
            if (temp.equals(getDynamicValue())) {
                // do nothing
            }
        }
        
        private String getDynamicValue() { return "data"; }
    }
    """;


        tempFolder.newFile("config.txt");
        tempFolder.newFile("configTwo.txt");
        tempFolder.newFile("configThree.txt");
        tempFolder.newFile("mockJava.java");
        tempFolder.newFolder("src");

        Path srcPath = tempFolder.getRoot().toPath().resolve("src");
        java.nio.file.Files.createFile(srcPath.resolve("Main.java"));

        Path mainFile = tempFolder.getRoot().toPath().resolve("src/Main.java");
        Path serviceFile = tempFolder.getRoot().toPath().resolve("mockJava.java");
        java.nio.file.Files.writeString(mainFile, mockCode);
        java.nio.file.Files.writeString(serviceFile, mockCode);





        return tempFolder.getRoot().toPath();
    }
}