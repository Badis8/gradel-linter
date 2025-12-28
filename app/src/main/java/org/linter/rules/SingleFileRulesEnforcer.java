package org.linter.rules;

import java.util.ArrayList;
import java.util.List;

public class SingleFileRulesEnforcer implements LinterRulesEnforcer {

    List<SingleFileRuleEnfocrer> singleFileRuleEnfocrers;



    @Override
    public List<RuleViolation> reviewAndReportViolations() {
        List<RuleViolation> ruleViolation = new ArrayList<>();
        for(SingleFileRuleEnfocrer singleFileRuleEnfocrer : singleFileRuleEnfocrers ){
            
        }
        return List.of();
    }
}
