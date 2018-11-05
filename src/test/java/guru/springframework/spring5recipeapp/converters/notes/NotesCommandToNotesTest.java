package guru.springframework.spring5recipeapp.converters.notes;

import guru.springframework.spring5recipeapp.commands.NotesCommand;
import guru.springframework.spring5recipeapp.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private static final Long ID_VALUE = 1L;
    private static final String RECIPE_NOTES = "Notes";
    private NotesCommandToNotes commandToNotes;

    @Before
    public void setUp() throws Exception {
        commandToNotes = new NotesCommandToNotes();
    }

    @Test
    public void testNullObject() {
        assertNull(commandToNotes.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(commandToNotes.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        NotesCommand command = new NotesCommand();
        command.setId(ID_VALUE);
        command.setRecipeNotes(RECIPE_NOTES);

        Notes notes = commandToNotes.convert(command);

        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}