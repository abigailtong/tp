package task;

import app.CommandRunner;
import parser.Parser;
import parser.ParsedCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleterTest {

    private ArrayList<Application> applications;
    private CommandRunner runner;

    @BeforeEach
    public void setUp() {
        applications = new ArrayList<>();
        runner = new CommandRunner(applications);
    }

    @Test
    public void deleteApplication_deleteLastItem_listBecomesEmpty() {
        Application onlyApplication = new Application("Microsoft", "UX Designer", "2026-03-03");
        applications.add(onlyApplication);

        ParsedCommand cmd = Parser.parse("delete 1");
        runner.run(cmd);

        assertEquals(0, applications.size());
    }

    @Test
    public void deleteApplication_validIndex_sizeDecreasesByOne() {
        applications.add(new Application("Google", "Software Engineer", "2026-03-01"));
        applications.add(new Application("Apple", "Data Analyst", "2026-03-02"));

        ParsedCommand cmd = Parser.parse("delete 1");
        runner.run(cmd);

        assertEquals(1, applications.size());
    }

    @Test
    public void deleteApplication_deleteSecondApplication_correctItemRemoved() {
        Application first = new Application("Google", "Software Engineer", "2026-03-01");
        Application second = new Application("Apple", "Data Analyst", "2026-03-02");

        applications.add(first);
        applications.add(second);

        ParsedCommand cmd = Parser.parse("delete 2");
        runner.run(cmd);

        assertEquals(1, applications.size());
        assertEquals(first, applications.get(0));
    }

    @Test
    public void deleteApplication_deleteFirstOfThree_remainingApplicationsShiftCorrectly() {
        Application first = new Application("Google", "Software Engineer", "2026-03-01");
        Application second = new Application("Apple", "Data Analyst", "2026-03-02");
        Application third = new Application("Microsoft", "UX Designer", "2026-03-03");

        applications.add(first);
        applications.add(second);
        applications.add(third);

        ParsedCommand cmd = Parser.parse("delete 1");
        runner.run(cmd);

        assertEquals(2, applications.size());
        assertEquals(second, applications.get(0));
        assertEquals(third, applications.get(1));
    }

    @Test
    public void deleteApplication_deleteMiddleOfThree_correctRemainingApplications() {
        Application first = new Application("Google", "Software Engineer", "2026-03-01");
        Application second = new Application("Apple", "Data Analyst", "2026-03-02");
        Application third = new Application("Microsoft", "UX Designer", "2026-03-03");

        applications.add(first);
        applications.add(second);
        applications.add(third);

        ParsedCommand cmd = Parser.parse("delete 2");
        runner.run(cmd);

        assertEquals(2, applications.size());
        assertEquals(first, applications.get(0));
        assertEquals(third, applications.get(1));
    }

    @Test
    public void deleteApplication_deleteLastOfThree_correctRemainingApplications() {
        Application first = new Application("Google", "Software Engineer", "2026-03-01");
        Application second = new Application("Apple", "Data Analyst", "2026-03-02");
        Application third = new Application("Microsoft", "UX Designer", "2026-03-03");

        applications.add(first);
        applications.add(second);
        applications.add(third);

        ParsedCommand cmd = Parser.parse("delete 3");
        runner.run(cmd);

        assertEquals(2, applications.size());
        assertEquals(first, applications.get(0));
        assertEquals(second, applications.get(1));
    }

    @Test
    public void deleteApplication_fromEmptyList_invalidIndexHandled() {
        ParsedCommand cmd = Parser.parse("delete 1");
        boolean continueRunning = runner.run(cmd);

        assertEquals(0, applications.size());
        assertEquals(true, continueRunning);
    }

    @Test
    public void deleteApplication_missingIndex_shouldNotModifyList() {
        applications.add(new Application("Google", "SE", "2026-03-01"));

        ParsedCommand cmd = Parser.parse("delete");
        boolean continueRunning = runner.run(cmd);

        assertEquals(1, applications.size());
        assertEquals(true, continueRunning);
    }

    @Test
    public void deleteApplication_extraArguments_shouldNotModifyList() {
        Application app = new Application("Google", "SE", "2026-03-01");
        applications.add(app);

        ParsedCommand cmd = Parser.parse("delete 1 extra");
        runner.run(cmd);

        assertEquals(1, applications.size());
        assertEquals(app, applications.get(0));
    }

    @Test
    public void deleteApplication_invalidIndex_doesNotCrash() {
        applications.add(new Application("Google", "Software Engineer", "2026-03-01"));

        ParsedCommand cmd = Parser.parse("delete 10");
        boolean continueRunning = runner.run(cmd);

        assertEquals(1, applications.size());
        assertEquals(true, continueRunning);
    }

    @Test
    public void deleteApplication_negativeIndex_doesNotModifyList() {
        applications.add(new Application("Google", "Software Engineer", "2026-03-01"));

        ParsedCommand cmd = Parser.parse("delete -1");
        boolean continueRunning = runner.run(cmd);

        assertEquals(1, applications.size());
        assertEquals(true, continueRunning);
    }

    @Test
    public void deleteApplication_nonNumericIndex_shouldNotModifyList() {
        applications.add(new Application("Google", "Software Engineer", "2026-03-01"));

        ParsedCommand cmd = Parser.parse("delete abc");
        boolean continueRunning = runner.run(cmd);

        assertEquals(1, applications.size());
        assertEquals(true, continueRunning);
    }
}

