package bakingapp.android.com.bakingapp.Arch_Comp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import bakingapp.android.com.bakingapp.Models.Recipe;

public class RecipeRepository {

    private RecipeDao recipeDao;
    private LiveData<List<Recipe>> getAllRecpies;


    public RecipeRepository(Application application) {
        RecipeRoom recipeRoom = RecipeRoom.getInstance(application);
        recipeDao = recipeRoom.recipeDao();
        getAllRecpies = recipeDao.loadAllIRecipes();
    }

    public LiveData<List<Recipe>> loadAllRecpies(){
        return getAllRecpies;
    }

    public void addToRecipes(Recipe recipe){
        new AddToRecipesAsyncTask(recipeDao).execute(recipe);
    }


    private static class AddToRecipesAsyncTask extends AsyncTask<Recipe,Void,Void>{

        private RecipeDao recipeDao;

        public AddToRecipesAsyncTask(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDao.addToRecipe(recipes[0]);
            return null;
        }
    }


}
