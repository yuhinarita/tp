package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.coursemate.ContainsKeywordPredicate;

/**
 * Finds and lists all course mates in contact list whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find-mate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all courseMates whose names or skills "
            + "contain the specified keyphrase (case-insensitive) and displays them as a list with index numbers.\n"
            + "KEYPHRASE can contain any string.\n"
            + "Parameters: KEYPHRASE\n"
            + "Example: " + COMMAND_WORD + " alex yeoh";

    private final ContainsKeywordPredicate predicate;

    public FindCommand(ContainsKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCourseMateList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_COURSE_MATES_LISTED_OVERVIEW, model.getFilteredCourseMateList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
