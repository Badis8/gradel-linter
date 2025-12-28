package org.linter.rules;

import java.nio.file.Path;
import java.util.Optional;

public interface SingleFileRuleEnfocrer {

    Optional<RuleViolation> checkAndReportPossibleViolation(Path filePath);
}
