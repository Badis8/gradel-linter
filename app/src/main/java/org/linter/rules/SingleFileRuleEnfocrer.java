package org.linter.rules;

import java.util.Optional;

public interface SingleFileRuleEnfocrer {

    Optional<RuleViolation> checkAndReportPossibleViolation(String relativefilePath);
}
