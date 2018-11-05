package guru.springframework.spring5recipeapp.converters.unitOfMeasure;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private static final Long ID_VALUE = 1l;
    private static final String DESCRIPTION = "Description";
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToCommand;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureToCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(unitOfMeasureToCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(unitOfMeasureToCommand.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(ID_VALUE);
        unitOfMeasure.setDescription(DESCRIPTION);

        UnitOfMeasureCommand command = unitOfMeasureToCommand.convert(unitOfMeasure);

        assertNotNull(command);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}