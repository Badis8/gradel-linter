package org.linter.rules;

import java.util.List;

public interface LinterRulesEnforcer {
    List<RuleViolation> reviewAndReportViolations();
}
