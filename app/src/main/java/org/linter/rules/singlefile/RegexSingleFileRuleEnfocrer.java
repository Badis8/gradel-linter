package org.linter.rules.singlefile;

import org.linter.rules.RuleViolation;
import org.linter.rules.SingleFileRuleEnfocrer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexSingleFileRuleEnfocrer implements SingleFileRuleEnfocrer {

    List<Pattern> patternsToCheck ;


    @Override
    public Optional<RuleViolation> checkAndReportPossibleViolation(Path filePath) {
        try {

            AtomicInteger lineNumber = new AtomicInteger(1);

            return Files.lines(filePath)
                    .map(line -> checkLine(line, lineNumber.getAndIncrement(), filePath))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .findFirst();
        } catch (IOException e) {

            return Optional.empty();

        }
    }
        private Optional<RuleViolation> checkLine(String lineContent, int lineNumber, Path filePath) {
            for (Pattern pattern : patternsToCheck) {
                Matcher matcher = pattern.matcher(lineContent);
                if (matcher.find()) {
                    return Optional.of(new RuleViolation(
                            "Pattern match found: " + pattern.pattern(),
                            filePath,
                            lineNumber
                    ));
                }
            }
            return Optional.empty();
        }


}
