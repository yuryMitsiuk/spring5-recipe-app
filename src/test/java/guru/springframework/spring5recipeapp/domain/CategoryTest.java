package guru.springframework.spring5recipeapp.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    private Category category;

    @Test
    public void getId() {
        Long idValue = 4L;
        category = new Category();
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
        String desc = "Test description";
        category = new Category();
        category.setDescription(desc);
        assertEquals(desc, category.getDescription());
    }
}