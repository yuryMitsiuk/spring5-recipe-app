package guru.springframework.spring5recipeapp.converters.category;

import guru.springframework.spring5recipeapp.commands.CategoryCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    CategoryToCategoryCommand categoryToCommand;

    @Before
    public void setUp() {
        categoryToCommand = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(categoryToCommand.convert(null));
    }

    @Test
    public void testEmpptyObject() {
        assertNotNull(categoryToCommand.convert(new Category()));
    }

    @Test
    public void convert() {
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        CategoryCommand categoryCommand = categoryToCommand.convert(category);

        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}