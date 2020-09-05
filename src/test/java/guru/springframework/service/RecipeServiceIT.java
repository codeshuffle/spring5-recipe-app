package guru.springframework.service;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;


/**
 * Created by jt on 6/21/17.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        log.info("before iterator");

        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        if (recipes ==null ) {
            log.info("no elements");
            return;
        }

//        Recipe testRecipe = recipes.iterator().next();
//        log.debug("iterator working");
//        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);
//
//        //when
//        testRecipeCommand.setDescription(NEW_DESCRIPTION);
//        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);
//
//        //then
//        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
//        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
//        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
//        assertEquals(testRecipe.getIngredient().size(), savedRecipeCommand.getIngredients().size());
    }
}
