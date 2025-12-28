package org.linter.rules;

import org.linter.projectstructure.ProjectStructure;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SingleFileRulesEnforcer extends AbstractProjectStructureRulesEnforcer{

    List<SingleFileRuleEnfocrer> singleFileRuleEnfocrers;



    SingleFileRulesEnforcer(Path sourceProjectPath){
        this.projectStructure = new ProjectStructure(sourceProjectPath);
    }


    @Override
    public List<RuleViolation> reviewAndReportViolations() {
        Iterator<ProjectStructure> projectStructureIterator = this.projectStructure.getProjectStructureAsAnIterator();
        List<RuleViolation> ruleViolation = new ArrayList<>();

        //TO-DO check a better solution , a for, inside a while, inside a for is simply bad

        for(SingleFileRuleEnfocrer singleFileRuleEnfocrer : singleFileRuleEnfocrers ){
            while(projectStructureIterator.hasNext()){
                for(Path file : projectStructure.getDirectChildFiles())
                    singleFileRuleEnfocrer.checkAndReportPossibleViolation(file)
                            .ifPresent(ruleViolation::add);
            }

        }
        return ruleViolation;
    }


}
