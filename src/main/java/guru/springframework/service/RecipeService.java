package guru.springframework.service;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeService extends CrudRepository<Recipe, Long> {

}
