package guru.springframework.spring5recipeapp.converters.category;

import guru.springframework.spring5recipeapp.commands.CategoryCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    CategoryCommandToCategory commandToCategory;

    @Before
    public void setup() throws Exception {
        commandToCategory = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() {
        assertNull(commandToCategory.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(commandToCategory.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        Category category = commandToCategory.convert(categoryCommand);

        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}