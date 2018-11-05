package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getAll();
    Recipe findById(Long id);
    RecipeCommand save(RecipeCommand recipeCommand);
    RecipeCommand findCommandById(Long id);
}
