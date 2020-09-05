package guru.springframework;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@Slf4j
public class DataLoaderAppListener implements ApplicationListener<ContextRefreshedEvent> {


    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientRepository ingredientRepository;

    public DataLoaderAppListener(RecipeRepository recipeRepository,
                      CategoryRepository categoryRepository,
                      UnitOfMeasureRepository unitOfMeasureRepository,
                      IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("let's do the job");
        doTheJob();
    }

    public void doTheJob() {

        Notes note1 = new Notes();
        note1.setRecipeNotes("This a marvelous recipe");

        Ingredient ing1 = new Ingredient();
        ing1.setDescription("avocadoKenia");
        ing1.setAmount(BigDecimal.valueOf(2));
        ing1.setUom(unitOfMeasureRepository.findByDescription("Piece").get());

        Ingredient ing2a = new Ingredient();
        ing2a.setDescription("lime from CapeVerde");
        ing2a.setAmount(BigDecimal.valueOf(3));
        ing2a.setUom(unitOfMeasureRepository.findByDescription("Ounce").get());


        Recipe r1 = new Recipe();
        r1.setDescription("Guacamole");
        r1.setPrepTime( Integer.valueOf(100) );
        r1.setCookTime( Integer.valueOf(100) );
        r1.setServings( Integer.valueOf(3) );
        r1.setSource("source1");
        r1.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        r1.setDirections("directions");
        r1.setDifficulty(Difficulty.EASY);
        r1.setNotes(note1);
        Optional<Category> optionalCateg1 = categoryRepository.findByDescription("American");
        Set<Category> c =  new HashSet<>() ;
        if (r1.getCategories() != null) {
            c=r1.getCategories();
        }
        c.add(optionalCateg1.orElse(null));
        r1.setCategories(c);
        ing1.setRecipe(r1);
        ing2a.setRecipe(r1);
        r1.getIngredient().add(ing1);
        r1.getIngredient().add(ing2a);

        recipeRepository.save(r1);
        //ingredientRepository.save(ing1);


        Notes note2 = new Notes();
        note2.setRecipeNotes("This a marvelous recipe no. 2");

        Ingredient ing1b = new Ingredient();
        ing1b.setDescription("Chicken");
        ing1b.setAmount(BigDecimal.valueOf(1));
        ing1b.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());

        Ingredient ing2b = new Ingredient();
        ing2b.setDescription("Orange sauce");
        ing2b.setAmount(BigDecimal.valueOf(2));
        ing2b.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());


        Recipe r2 = new Recipe();
        r2.setDescription("Spicy Grilled Chicken");
        r2.setPrepTime( Integer.valueOf(200) );
        r2.setCookTime( Integer.valueOf(200) );
        r2.setServings( Integer.valueOf(4) );
        r2.setSource("source2");
        r2.setUrl("https://www.simplyrecipes.com/recipes/grilled_chicken_under_a_brick/");
        r2.setDirections("directions2");
        r2.setDifficulty(Difficulty.MODERATE);
        r2.setNotes(note2);
        Optional<Category> categoryOptional2 =
                categoryRepository.findByDescription("Italian");
        Set<Category> c2 =  new HashSet<>();
        //System.out.println(categoryRepository.findByDescription("Italian").get());
        c2.add(categoryOptional2.orElse(null));
        r2.setCategories(c2);
        ing1b.setRecipe(r2);
        ing2b.setRecipe(r2);
        r2.getIngredient().add(ing1b);
        r2.getIngredient().add(ing2b);

        recipeRepository.save(r2);
        //ingredientRepository.save(ing2);


    }



}
