package barcus.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import barcus.command.AddDeadlineCommand;
import barcus.command.AddEventCommand;
import barcus.command.AddTodoCommand;
import barcus.command.Command;
import barcus.command.DeleteCommand;
import barcus.command.ExitCommand;
import barcus.command.FindCommand;
import barcus.command.ListCommand;
import barcus.command.MarkCommand;
import barcus.command.TagCommand;
import barcus.command.UnknownCommand;
import barcus.command.UnmarkCommand;
import barcus.command.UntagCommand;
import barcus.exception.BarcusException;


public class ParserTest {

    @Test
    public void testParseUnknownCommand() {
        try {
            Command c = Parser.parse("huhhh");
            assertEquals(c.getClass(), UnknownCommand.class);
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseEmptyCommand() {
        try {
            Command c = Parser.parse("");
            assertEquals(c.getClass(), UnknownCommand.class);
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseExitCommand() {
        try {
            Command c = Parser.parse("bye");
            assertEquals(c.getClass(), ExitCommand.class);
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseListCommand() {
        try {
            Command c = Parser.parse("list");
            assertEquals(c.getClass(), ListCommand.class);
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseMarkCommand() {
        try {
            Command c = Parser.parse("mark 2");
            assertEquals(c.getClass(), MarkCommand.class);
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseMarkCommandWrongLength() {
        try {
            Command c = Parser.parse("mark");
            fail();
        } catch (BarcusException e) {
            assertEquals("please have an integer after 'mark'", e.getMessage());
        }
    }

    @Test
    public void testParseWrongMarkCommandNoInteger() {
        try {
            Command c = Parser.parse("mark hehe");
            fail();
        } catch (BarcusException e) {
            assertEquals("please have an integer after 'mark'", e.getMessage());
        }
    }

    @Test
    public void testParseUnmarkCommand() {
        try {
            Command c = Parser.parse("unmark 2");
            assertEquals(c.getClass(), UnmarkCommand.class);
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseWrongUnmarkCommandWrongLength() {
        try {
            Command c = Parser.parse("unmark");
            fail();
        } catch (BarcusException e) {
            assertEquals("please have an integer after 'unmark'", e.getMessage());
        }
    }

    @Test
    public void testParseWrongUnmarkCommandNoInteger() {
        try {
            Command c = Parser.parse("unmark hehe");
            fail();
        } catch (BarcusException e) {
            assertEquals("please have an integer after 'unmark'", e.getMessage());
        }
    }

    @Test
    public void testParseTodoCommand() {
        try {
            Command c = Parser.parse("todo homework");
            assertEquals(c.getClass(), AddTodoCommand.class);
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseWrongTodoCommandWrongLength() {
        try {
            Command c = Parser.parse("todo");
            fail();
        } catch (BarcusException e) {
            assertEquals("please include a description of the todo", e.getMessage());
        }
    }

    @Test
    public void testParseDeadlineCommand() {
        try {
            Command c = Parser.parse("deadline homework /by 22/08/2024 16:00");
            assertEquals(c.getClass(), AddDeadlineCommand.class);
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseWrongDeadlineCommandWrongLength() {
        try {
            Command c = Parser.parse("deadline homework");
            fail();
        } catch (BarcusException e) {
            assertEquals("please include '/by' and deadline after it", e.getMessage());
        }
    }

    @Test
    public void testParseEventCommand() {
        try {
            Command c = Parser.parse("event make friends /from 22/08/2024 16:00 /to 03/09/2024 16:00");
            assertEquals(c.getClass(), AddEventCommand.class);
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseWrongEventCommandWrongLength() {
        try {
            Command c = Parser.parse("event cry");
            fail();
        } catch (BarcusException e) {
            assertEquals("please include '/from' and '/to' as well as dates after each of those words",
                    e.getMessage());
        }
    }

    @Test
    public void testParseDeleteCommand() {
        try {
            Command c = Parser.parse("delete 2");
            assertEquals(c.getClass(), DeleteCommand.class);
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseDeleteCommandWrongLength() {
        try {
            Command c = Parser.parse("delete");
            fail();
        } catch (BarcusException e) {
            assertEquals("please have an integer after 'delete'", e.getMessage());
        }
    }

    @Test
    public void testParseWrongDeleteCommandNoInteger() {
        try {
            Command c = Parser.parse("delete hehe");
            fail();
        } catch (BarcusException e) {
            assertEquals("please have an integer after 'delete'", e.getMessage());
        }
    }

    @Test
    public void testParseFindCommand() {
        try {
            Command c = Parser.parse("find book");
            assertEquals(c.getClass(), FindCommand.class);
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseWrongFindCommandWrongLength() {
        try {
            Command c = Parser.parse("find");
            fail();
        } catch (BarcusException e) {
            assertEquals("please include what word(s) you want to find after 'find'", e.getMessage());
        }
    }

    @Test
    public void testParseTagCommand() {
        try {
            Command c = Parser.parse("tag 2 #fun");
            assertEquals(c.getClass(), TagCommand.class);
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseWrongTagCommandWrongLength() {
        try {
            Command c = Parser.parse("tag 5");
            fail();
        } catch (BarcusException e) {
            assertEquals("please have an integer and a single word tag after 'tag'", e.getMessage());
        }
    }

    @Test
    public void testParseWrongTagCommandNoInteger() {
        try {
            Command c = Parser.parse("tag hehe haha");
            fail();
        } catch (BarcusException e) {
            assertEquals("please have an integer and a single word tag after 'tag'", e.getMessage());
        }
    }

    @Test
    public void testParseUntagCommand() {
        try {
            Command c = Parser.parse("untag 2 #fun");
            assertEquals(c.getClass(), UntagCommand.class);
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseWrongUntagCommandWrongLength() {
        try {
            Command c = Parser.parse("untag 5");
            fail();
        } catch (BarcusException e) {
            assertEquals("please have an integer and a single word tag after 'untag'", e.getMessage());
        }
    }

    @Test
    public void testParseWrongUntagCommandNoInteger() {
        try {
            Command c = Parser.parse("untag hehe haha");
            fail();
        } catch (BarcusException e) {
            assertEquals("please have an integer and a single word tag after 'untag'", e.getMessage());
        }
    }
}
