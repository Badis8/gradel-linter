package org.linter;

import java.util.List;

public interface LinterRulesEnforcer {
    List<RuleViolation> reviewAndReportViolations();
}
