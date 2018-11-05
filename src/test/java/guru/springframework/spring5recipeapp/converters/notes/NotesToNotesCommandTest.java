package guru.springframework.spring5recipeapp.converters.notes;

import guru.springframework.spring5recipeapp.commands.NotesCommand;
import guru.springframework.spring5recipeapp.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private static final Long ID_VALUE = 1L;
    private static final String RESIPE_NOTES = "Notes";
    private NotesToNotesCommand notesToCommand;

    @Before
    public void setUp() throws Exception {
        notesToCommand = new NotesToNotesCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(notesToCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(notesToCommand.convert(new Notes()));
    }

    @Test
    public void convert() {
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RESIPE_NOTES);

        NotesCommand command = notesToCommand.convert(notes);

        assertNotNull(command);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(RESIPE_NOTES, command.getRecipeNotes());
    }
}