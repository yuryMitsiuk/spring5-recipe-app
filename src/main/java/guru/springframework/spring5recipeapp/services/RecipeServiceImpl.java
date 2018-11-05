package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.converters.recipe.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.converters.recipe.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToCommand;
    private final RecipeCommandToRecipe commandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeToRecipeCommand recipeToCommand, RecipeCommandToRecipe commandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToCommand = recipeToCommand;
        this.commandToRecipe = commandToRecipe;
    }

    @Override
    public Set<Recipe> getAll() {
        log.debug("Get all recipes.");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }
        return recipeOptional.get();
    }

    @Transactional
    @Override
    public RecipeCommand save(RecipeCommand recipeCommand) {
        Recipe recipe = commandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(recipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToCommand.convert(savedRecipe);
    }
}
