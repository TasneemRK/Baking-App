package bakingapp.android.com.bakingapp.Arch_Comp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import bakingapp.android.com.bakingapp.Models.Recipe;

public class RecipeModelView extends AndroidViewModel {

    private RecipeRepository recipeRepository ;
    private LiveData<List<Recipe>> recipes;

    public RecipeModelView(@NonNull Application application) {
        super(application);
        recipeRepository = new RecipeRepository(application);
        recipes = recipeRepository.loadAllRecpies();
    }

    public void insertRecipe(Recipe recipe){
        recipeRepository.addToRecipes(recipe);
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }
}
