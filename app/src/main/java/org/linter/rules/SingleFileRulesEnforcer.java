package org.linter.rules;

import org.linter.projectstructure.ProjectStructure;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SingleFileRulesEnforcer extends AbstractProjectStructureRulesEnforcer{

    List<SingleFileRuleEnfocrer> singleFileRuleEnfocrers;



    SingleFileRulesEnforcer(Path sourceProjectPath,List<SingleFileRuleEnfocrer> singleFileRuleEnfocrers){
        this.projectStructure = new ProjectStructure(sourceProjectPath);

        singleFileRuleEnfocrers = singleFileRuleEnfocrers;
    }


    @Override
    public List<RuleViolation> reviewAndReportViolations() {
        Iterator<ProjectStructure> projectStructureIterator = this.projectStructure.getProjectStructureAsAnIterator();
        List<RuleViolation> projectRuleViolations = new ArrayList<>();


        while(projectStructureIterator.hasNext()){
            for(Path file : projectStructure.getDirectChildFiles()) {
                projectRuleViolations.addAll(reviewAndReportFileViolations(file));
            }
            projectStructureIterator.next();
        }
        return projectRuleViolations;
    }


    private List<RuleViolation> reviewAndReportFileViolations(Path file){
        List<RuleViolation> ruleViolation = new ArrayList<>();
        for(SingleFileRuleEnfocrer singleFileRuleEnfocrer : singleFileRuleEnfocrers ) {

            singleFileRuleEnfocrer.checkAndReportPossibleViolation(file)
                    .ifPresent(ruleViolation::add);

        }
        return  ruleViolation;

    }
}
