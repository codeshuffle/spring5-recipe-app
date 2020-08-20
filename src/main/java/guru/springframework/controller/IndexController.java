package guru.springframework.controller;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.service.RecipeService;
import guru.springframework.service.RecipeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

//    private final CategoryRepository categoryRepository;
//    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeServiceImpl recipeService;

    public IndexController( //CategoryRepository categoryRepository,
//                           UnitOfMeasureRepository unitOfMeasureRepository,
                           RecipeServiceImpl recipeService) {
//        this.categoryRepository = categoryRepository;
//        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;

    }

//    @RequestMapping({"","/","index"})
//    public  String getIndexPage() {
//        Optional<Category> categoryOptional =
//                categoryRepository.findByDescription("American");
//        Optional<UnitOfMeasure> unitOfMeasureOptional =
//                unitOfMeasureRepository.findByDescription("Teaspoon");
//        System.out.println("Categ. id is: " + categoryOptional.get().getId());
//        System.out.println("UnitOfM id is: " + unitOfMeasureOptional.get().getId());
//        return "index";
//    }


    @RequestMapping({"/recipes","recipes"})
    public String getRecipeList(Model model) {
//            model.addAttribute("recipes",  recipeService.findAll());
           model.addAttribute("recipes",  recipeService.getRecipes());
            return "index";
            }

}
