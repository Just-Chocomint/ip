package fern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandsTest {
    TaskList tasks = new TaskList();
    Commands commands = new Commands(tasks);

    @Test
    public void handle_todoCommand_addsTask() throws FernException {
        commands.handle("todo test success");
        System.out.println("test here");
        assertEquals("test success", tasks.get(tasks.size() - 1).getDescription());
    }

    @Test
    public void handle_todoNoDescription_exceptionThrown() {
        assertThrows(
                IncompleteCommandException.class,
                () -> commands.handle("todo")
        );
    }

    @Test
    public void handle_unknownCommand_exceptionThrown() {
        assertThrows(
                UnknownCommandException.class,
                () -> commands.handle("oaidoiahsdiaj")
        );
    }

    @Test
    public void handle_markWithoutIndex_exceptionThrown() {
        assertThrows(
                IncompleteCommandException.class,
                () -> commands.handle("mark")
        );
    }

    @Test
    public void handle_deadlineCommand_addsDeadline() throws FernException {
        commands.handle("deadline test success /by 1 Jan");
        assertInstanceOf(Deadline.class, tasks.get(tasks.size() - 1));
        assertEquals("test success", tasks.get(tasks.size() - 1).getDescription());
    }

    @Test
    public void handle_deadlineWrongDateFormat_exceptionThrown() {
        assertThrows(
                FernException.class,
                () -> commands.handle("deadline try wrong date /by tomorrow")
        );
    }

    @Test
    public void handle_deadlineMissingBy_exceptionThrown() {
        assertThrows(
                IncompleteCommandException.class,
                () -> commands.handle("deadline no date")
        );
    }

    @Test
    public void handle_eventCommand_addsEvent() throws FernException {
        commands.handle("event test success /from 2 jan /to 3 jan");
        assertInstanceOf(Event.class, tasks.get(tasks.size() - 1));
        assertEquals("test success", tasks.get(tasks.size() - 1).getDescription());
    }

    @Test
    public void handle_eventMissingFromOrTo_exceptionThrown() {
        assertThrows(
                IncompleteCommandException.class,
                () -> commands.handle("event no to /from 12 feb")
        );
    }
}

