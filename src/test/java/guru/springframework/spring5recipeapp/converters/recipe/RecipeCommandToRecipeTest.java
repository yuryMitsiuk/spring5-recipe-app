package guru.springframework.spring5recipeapp.converters.recipe;

import guru.springframework.spring5recipeapp.commands.CategoryCommand;
import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.NotesCommand;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.converters.category.CategoryCommandToCategory;
import guru.springframework.spring5recipeapp.converters.ingredient.IngredientCommandToIngredient;
import guru.springframework.spring5recipeapp.converters.notes.NotesCommandToNotes;
import guru.springframework.spring5recipeapp.converters.unitOfMeasure.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.spring5recipeapp.domain.Difficulty;
import guru.springframework.spring5recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
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


    private RecipeCommandToRecipe commandToRecipe;

    @Before
    public void setUp() throws Exception {
        commandToRecipe = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new NotesCommandToNotes(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
    }

    @Test
    public void testNullObject() {
        assertNull(commandToRecipe.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(commandToRecipe.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {
        RecipeCommand command = new RecipeCommand();
        command.setId(RECIPE_ID);
        command.setDescription(DESCRIPTION);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);
        command.setDirections(DIRECTIONS);
        command.setDifficulty(DIFFICULTY);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        notesCommand.setRecipeNotes(RECIPE_NOTES);
        command.setNotes(notesCommand);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CATEGORY_ID_1);
        categoryCommand1.setDescription(CATEGORY_DESC_1);
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CATEGORY_ID_2);
        categoryCommand2.setDescription(CATEGORY_DESC_2);
        command.getCategories().add(categoryCommand1);
        command.getCategories().add(categoryCommand2);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGREDIENT_ID_1);
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGREDIENT_ID_2);
        command.getIngredients().add(ingredientCommand1);
        command.getIngredients().add(ingredientCommand2);

        Recipe recipe = commandToRecipe.convert(command);

        assertNotNull(recipe);
        assertNotNull(recipe.getCategories());
        assertNotNull(recipe.getIngredients());
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(RECIPE_NOTES, recipe.getNotes().getRecipeNotes());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(2, recipe.getCategories().size());
    }
}