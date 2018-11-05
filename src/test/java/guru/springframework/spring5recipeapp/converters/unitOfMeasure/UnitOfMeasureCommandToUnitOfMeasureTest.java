package guru.springframework.spring5recipeapp.converters.unitOfMeasure;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private static final Long ID_VALUE = 1l;
    private static final String DESCRIPTION = "Description";
    private UnitOfMeasureCommandToUnitOfMeasure commandToUnitOfMeasure;

    @Before
    public void setUp() throws Exception {
        commandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullObject() {
        assertNull(commandToUnitOfMeasure.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(commandToUnitOfMeasure.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() {
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);

        UnitOfMeasure unitOfMeasure = commandToUnitOfMeasure.convert(command);

        assertNotNull(unitOfMeasure);
        assertEquals(ID_VALUE, unitOfMeasure.getId());
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
    }
}