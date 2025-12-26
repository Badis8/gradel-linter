package org.linter;

import java.util.Optional;

public interface SingleFileRuleEnfocrer {

    Optional<RuleViolation> checkAndReportPossibleViolation(String relativefilePath);
}
