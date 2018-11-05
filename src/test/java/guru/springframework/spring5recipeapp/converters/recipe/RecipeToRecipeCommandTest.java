package guru.springframework.spring5recipeapp.converters.recipe;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.converters.category.CategoryToCategoryCommand;
import guru.springframework.spring5recipeapp.converters.ingredient.IngredientToIngredientCommand;
import guru.springframework.spring5recipeapp.converters.notes.NotesToNotesCommand;
import guru.springframework.spring5recipeapp.converters.unitOfMeasure.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    private static final Long RECIPE_ID = 1L;
    private static final String DESCRIPTION = "Description of recipe";
    private static final Integer PREP_TIME = 15;
    private static final Integer COOK_TIME = 5;
    private static final Integer SERVINGS = 2;
    private static final String SOURCE = "Source";
    private static final String URL = "url";
    private static final String DIRECTIONS = "Directions";
    private static final Difficulty DIFFICULTY = Difficulty.MODERATE;
    private static final Long INGREDIENT_ID_1 = 1L;
    private static final Long INGREDIENT_ID_2 = 2L;
    private static final Long NOTES_ID = 10L;
    private static final String RECIPE_NOTES = "Recipe notes";
    private static final Long CATEGORY_ID_1 = 1L;
    private static final String CATEGORY_DESC_1 = "Category 1 description";
    private static final Long CATEGORY_ID_2 = 2L;
    private static final String CATEGORY_DESC_2 = "Category 2 description";

    private RecipeToRecipeCommand recipeToCommand;

    @Before
    public void setUp() throws Exception {
        recipeToCommand = new RecipeToRecipeCommand(new NotesToNotesCommand(), new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Test
    public void testNullObject() {
        assertNull(recipeToCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(recipeToCommand.convert(new Recipe()));
    }

    @Test
    public void convert() {
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        notes.setRecipeNotes(RECIPE_NOTES);
        recipe.setNotes(notes);

        Category category1 = new Category();
        category1.setId(CATEGORY_ID_1);
        category1.setDescription(CATEGORY_DESC_1);
        Category category2 = new Category();
        category2.setId(CATEGORY_ID_2);
        category2.setDescription(CATEGORY_DESC_2);
        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGREDIENT_ID_1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_ID_2);
        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        RecipeCommand command = recipeToCommand.convert(recipe);

        assertNotNull(command);
        assertNotNull(command.getCategories());
        assertNotNull(command.getIngredients());
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(RECIPE_NOTES, command.getNotes().getRecipeNotes());
        assertEquals(2, command.getIngredients().size());
        assertEquals(2, command.getCategories().size());
    }
}